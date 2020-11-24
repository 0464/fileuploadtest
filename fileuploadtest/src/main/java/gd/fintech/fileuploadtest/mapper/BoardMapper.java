package gd.fintech.fileuploadtest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.fileuploadtest.vo.Board;
import gd.fintech.fileuploadtest.vo.Boardfile;
@Mapper
public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> selectBoardListByPage(Map<String, Integer> map);
	int selectBoardcount();
	int deleteBoard(int boardId);
	Board selectBoardOneList(int boardId);
	int updateBoard(Board board);
	Board selectBoardOne(Map<String, Integer> map);
	int selectCommentcount();
	Board selectBoardfileOne(int boardId);
}