package com.packtpub.springsecurity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AccountController extends BaseController {

	@RequestMapping(method=RequestMethod.GET, value="/account/changePassword.do")
	public void changePassword() {
		log.info("account chagePassword method start....");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/account/home.do")
	public void home() {
		log.info("account home method start....");
	}
	

}
