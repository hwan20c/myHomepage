package com.bh.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class boardController {

    @GetMapping("/myBoard")
	public String mainpage(Model model) {
		return "myBoard";
	}
    
}
