package com.bh.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String mainpage(Model model) {
		
		System.out.println("@ChatController, chat GET()");
		
//		return "/index";
		return "/chat";
	}

}
