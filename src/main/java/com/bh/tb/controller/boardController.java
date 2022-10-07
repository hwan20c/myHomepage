package com.bh.tb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bh.tb.model.Board;
import com.bh.tb.model.BoardSearchRequest;
import com.bh.tb.service.BoardRestService;

@Controller
@RequestMapping("/myBoard")
public class BoardController {

	private final BoardRestService boardRestService;

	@Autowired
	public BoardController(BoardRestService boardRestService) {
		this.boardRestService = boardRestService;
	}

  @GetMapping
	public String mainpage(HttpServletRequest request, Model model, BoardSearchRequest boardSearchRequest) {
		Page<Board> boards = boardRestService.listwithPageable(boardSearchRequest);
		model.addAttribute("borads", boards);
		model.addAttribute("search", boardSearchRequest);
		return "myBoard";
	}
    
}
