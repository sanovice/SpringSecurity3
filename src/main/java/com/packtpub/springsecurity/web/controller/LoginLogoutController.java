package com.packtpub.springsecurity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginLogoutController extends BaseController {

	@RequestMapping(method=RequestMethod.GET, value="/login.do")
	public void login() {
		log.info("login method start....");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/home.do")
	public void home() {
		log.info("home method start....");
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/")
	public void root() {
		log.info("root method start....");
	}
}
