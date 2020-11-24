package gd.fintech.fileuploadtest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.fileuploadtest.service.BoardService;
import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.BoardForm;
import gd.fintech.fileuploadtest.vo.Boardfile;

@Controller
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService boardService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}

	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		logger.debug(boardForm.toString());
		//logger.debug("size : " + boardForm.getBoardfile().size());
		boardService.addBoard(boardForm);
		return "redirect:/boardList/1";
	}
	@GetMapping("/boardOne/{boardId}/{currentPage}")
	public String boardOne(Model model,
						@PathVariable(value = "boardId") int boardId,
						@PathVariable(value = "") int currentPage) {
		int rowPerPage = 3;
		Board board = boardService.getBoardOne(boardId, currentPage, rowPerPage);
		Board boardfile = boardService.getBoardfileOne(boardId);
		int totalCount = boardService.getCountComment();
		int lastPage = totalCount / rowPerPage; 
		if(totalCount % rowPerPage != 0) { 
			lastPage += 1;
		}
		if(lastPage == 0) { 
			currentPage = 0;
		}
		model.addAttribute("board", board);
		model.addAttribute("boardfile", boardfile);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		return "boardOne";
	}
	
	@GetMapping("/boardList/{currentPage}")
	public String boardList(Model model, @PathVariable(value = "") int currentPage) {	
		int rowPerPage = 10;
		List<Board> boardList = boardService.getBoardListByPage(currentPage, rowPerPage);
		int totalCount = boardService.getCountBoard(); 
		int lastPage = totalCount / rowPerPage; 
		if(totalCount % rowPerPage != 0) { 
			lastPage += 1;
		}
		if(lastPage == 0) { 
			currentPage = 0;
		}
		model.addAttribute("boardList", boardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		return "boardList";
	}
	
	@GetMapping("/removeBoard/{boardId}")
	public String removeBoard(@PathVariable(value="boardId") int boardId) {
		boardService.removeBoard(boardId);
		return "redirect:/boardList/1";
	}
	
	@GetMapping("/modifyBoard/{boardId}")
	public String modifyBoard(Model model, @PathVariable(value="boardId") int boardId) {
		Board boardOne = new Board();
		boardOne = boardService.getBoardOneList(boardId);
		model.addAttribute("boardOne", boardOne);
		return "/modifyBoard";
	}
	
	@PostMapping("/modifyBoard")
	public String modifyBoard(BoardForm boardForm) {
		boardService.updateBoard(boardForm);
		return "redirect:/boardList/1";
	}
	
	@GetMapping("/removeBoardfile/{boardId}/{boardfileId}")
	public String removeBoardfile(Model model,
								@PathVariable(value = "boardId") int boardId,
								@PathVariable(value = "boardfileId") int boardfileId) {
		boardService.removeBoardfileName(boardfileId);
		return "redirect:/modifyBoard/"+boardId;
	}
}