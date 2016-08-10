/**
 * 
 */
package com.packtpub.springsecurity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mularien
 *
 */
@Controller
public class DummytController extends BaseController {
	
	@RequestMapping("/listPets.do")
	public String listPets() {
		return dummy(); 
	}
	

	@RequestMapping("/category.do")
	public String category() {
		return dummy(); 
	}
	
	
	public String dummy() {
		log.info("Not Yet!!!!!");
		
		return "dummy"; 
	}
	
}
