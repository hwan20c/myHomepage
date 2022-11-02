package com.bh.tb.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String create(HttpServletRequest request, Model model, @RequestParam(defaultValue = "0") int id) {
		Board board = (id !=0 ) ? boardRestService.get(id) : new Board();
		model.addAttribute("board", board);
		return "board/create";
	}

	@PostMapping
	public String createBoard(Board board, Model model,
														@RequestPart(required = false, name = "file") List<MultipartFile> multipartFiles, 
														@RequestPart(required = false) MultipartFile imageFile,
	 													@RequestParam(required = false, name = "content_file_names")List<String> contentFileNames) throws IOException {
		System.out.println("@@@@@@@@ : " + contentFileNames);
		Board response = boardRestService.create(board, multipartFiles, imageFile, contentFileNames);
		if (response != null) {
			return "redirect:/myBoard";
		} else {
			model.addAttribute("board", board);
			return "board/create";
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteBoard(@PathVariable String id) {
		boardRestService.delete(Integer.parseInt(id));
		return "success";
	}
    
}
