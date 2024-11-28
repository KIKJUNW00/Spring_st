package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {

	List<Board> getBoardList();

	Board getBoard(Board board);
	
	void insertBoard(Board board);

	void deleteBoard(Board board);
	
	void updateBoard(Board board);
}
