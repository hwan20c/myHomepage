package com.bh.tb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		String search = "?";
		search += "searchKey=" + boardSearchRequest.getSearchKey();
		search += "&searchValue=" + boardSearchRequest.getSearchValue();
		search += "&page=" + boardSearchRequest.getPage();  
		Page<Board> boards = boardRestService.listwithPageable(search);
		model.addAttribute("boards", boards);
		model.addAttribute("search", boardSearchRequest);
		return "myBoard";
	}

	@GetMapping("/{id}")
  public String detail(HttpServletRequest request, @PathVariable int id, Model model) {
    Board board = boardRestService.detailPage(id);
    board.increaseViewCount();
    model.addAttribute("board", board);
    return "myBoard/detail";
  }
    
}
