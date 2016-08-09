package com.packtpub.springsecurity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WishListController extends BaseController {

	@RequestMapping(method=RequestMethod.GET, value="/wishlist/home.do")
	public void home() {
		log.info("wish list home method start....");
		log.warn("not work jsp file");
	}
	

}
