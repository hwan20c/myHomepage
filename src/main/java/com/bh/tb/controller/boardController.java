package com.bh.tb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "board/myBoard";
	}

	@GetMapping("/{id}")
  public String detail(HttpServletRequest request, @PathVariable int id, Model model) {
    Board board = boardRestService.get(id);
    model.addAttribute("board", board);
    return "board/detail";
  }

	@GetMapping("/create")
	public String create(HttpServletRequest request, Model model) {
		model.addAttribute("board", new Board());
		return "board/create";
	}

	@PostMapping
	public String createBoard(Board board, Model model) {
		Board response = boardRestService.create(board);
		if (response != null) {
			return "redirect:/myBoard";
		} else {
			model.addAttribute("board", board);
			return "board/create";
		}
	}

	@GetMapping("/{boardId}/edit")
	public String editBoard(@PathVariable int boardId, Model model) {
		Board board = boardRestService.get(boardId);
		model.addAttribute("board", board);
		return "board/edit";
	}

	@PutMapping
	public String updateBoard(Board board) {
		boardRestService.edit(board);
		return "redirect:board/myBoard";
	}

	@DeleteMapping
	@ResponseBody
	public String deleteBoard(Board board) {
		boardRestService.delete(board);
		return "success";
	}
    
}
