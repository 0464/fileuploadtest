package gd.fintech.fileuploadtest.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.fileuploadtest.mapper.BoardMapper;
import gd.fintech.fileuploadtest.mapper.BoardfileMapper;
import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.BoardForm;
import gd.fintech.fileuploadtest.vo.Boardfile;

@Service
@Transactional
public class BoardService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String PATH ="D:\\user2_stswork\\fileuploadtest\\src\\main\\webapp\\upload\\";
	@Autowired BoardMapper boardMapper;
	@Autowired BoardfileMapper boardfileMapper;
	
	public Board getBoardOne(int boardId, int currentPage, int rowPerPage) {
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardId", boardId);
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		Board boardOne = boardMapper.selectBoardOne(map);
		return boardOne;
	}
	
	public Board getBoardfileOne(int boardId) {
		Board boardfile = boardMapper.selectBoardfileOne(boardId);
		return boardfile;
	}
	
	public List<Board> getBoardListByPage(int currentPage, int rowPerPage){
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		List<Board> boardList = boardMapper.selectBoardListByPage(map);
		return boardList;
	}
	
	public void addBoard(BoardForm boardForm) {
		Board board = new Board();
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		// 1. board db 입력 -> key값 받음
		boardMapper.insertBoard(board); // setBoardId(SELECT LAST_INSERT_ID())
		
		List<Boardfile> boardfile = null;
		if(boardForm.getBoardfile() != null) {
			boardfile = new ArrayList<Boardfile>();
			for(MultipartFile mf : boardForm.getBoardfile()) {
				Boardfile bf = new Boardfile();
				bf.setBoardId(board.getBoardId());
				int p = mf.getOriginalFilename().lastIndexOf(".");
				String ext = mf.getOriginalFilename().substring(p).toLowerCase();
				String filename = UUID.randomUUID().toString().replace("-", "");
				bf.setBoardfileName(filename+ext);
				bf.setBoardfileSize(mf.getSize());
				bf.setBoardfileType(mf.getContentType());
				boardfile.add(bf);
				logger.debug("for 문 : "+bf);
				try {
					mf.transferTo(new File(PATH+filename+ext));
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		if(boardfile != null) {
			for(Boardfile bf : boardfile) {
				boardfileMapper.insertBoardfile(bf);
			}
		}
	}
	
	public int getCountBoard() {
		return boardMapper.selectBoardcount();
	}
	
	public int getCountComment() {
		return boardMapper.selectCommentcount();
	}
	
	public void removeBoard(int boardId) {
		// 게시글을 참조하는 파일을 삭제
		List<String> boardfileNameList = boardfileMapper.selectBoardFileNameList(boardId);
		for(String s:boardfileNameList) {
			File file = new File(PATH+s);
			if(file.exists()) {
				file.delete();
			}
		}
		// 게시글을 참조하는 파일테이블 데이터 삭제
		boardfileMapper.deleteBoardfile(boardId);
		// 게시글 삭제
		boardMapper.deleteBoard(boardId);
	}
	
	public Board getBoardOneList(int boardId) {
		Board board = new Board();
		board = boardMapper.selectBoardOneList(boardId);
		return board;
	}
	
	public void updateBoard(BoardForm boardForm) {
		Board board = new Board();
		board.setBoardId(boardForm.getBoardId());
		board.setBoardTitle(boardForm.getBoardTitle());
		board.setBoardContent(boardForm.getBoardContent());
		boardMapper.updateBoard(board);
		
		List<Boardfile> boardfile = null;
		if(boardForm.getBoardfile()!=null) {
			boardfile = new ArrayList<Boardfile>();
			for(MultipartFile mf : boardForm.getBoardfile()) {
				Boardfile bf = new Boardfile();
				bf.setBoardId(board.getBoardId());
				String filename = UUID.randomUUID().toString().replace("-", "");
				int p = mf.getOriginalFilename().lastIndexOf(".");
				String ext = mf.getOriginalFilename().substring(p);
				bf.setBoardfileName(filename+ext);
				bf.setBoardfileSize(mf.getSize());
				bf.setBoardfileType(mf.getContentType());
				boardfile.add(bf);
				
				try {
					mf.transferTo(new File(PATH+filename+ext));
				} catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				
			}
		}
		if(boardfile !=null) {
			for(Boardfile bf: boardfile) {
				boardfileMapper.insertBoardfile(bf);
			}
		}
	}
	
	public void removeBoardfileName(int boardfileId) {
		boardfileMapper.deleteBoardfileName(boardfileId);
	}
}