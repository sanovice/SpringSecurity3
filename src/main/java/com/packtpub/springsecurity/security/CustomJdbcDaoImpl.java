package com.packtpub.springsecurity.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.packtpub.springsecurity.security.SaltedUser;

/**
 * Extends the baseline Spring Security JdbcDaoImpl and implements change password functionality.
 * 
 * Used in Chapter 4 example of customizing JdbcDaoImpl.
 * 
 * @author Mularien
 */
public class CustomJdbcDaoImpl extends JdbcDaoImpl implements IChangePassword {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	// Ch 4 Password Encoder and Salt Exercise
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	public void changePassword(String username, String password) {
		log.info("changePassword  username:{}, password:{}", username, password); 
		
//		getJdbcTemplate().update(
//				"UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
//				password, username);
		// Ch 4 After password encoder and salt exercise
		UserDetails user = loadUserByUsername(username);
		String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
		getJdbcTemplate().update(
			"UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
			encodedPassword, username);
	}

	// Ch 4 SaltedUser exercise
	@Override
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		

		log.info("createUserDetails  username:{}", username); 
		
        String returnUsername = userFromUserQuery.getUsername();

        if (!isUsernameBasedPrimaryKey()) {
            returnUsername = username;
        }

        return new SaltedUser(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
                true, true, true, combinedAuthorities, ((SaltedUser) userFromUserQuery).getSalt());
	}

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		log.info("loadUsersByUsername  username:{}", username); 
		
        return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] {username}, new RowMapper<UserDetails>() {
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString(1);
                String password = rs.getString(2);
                boolean enabled = rs.getBoolean(3);
                String salt = rs.getString(4);
                return new SaltedUser(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES, salt);
            }
        });
	}
}
