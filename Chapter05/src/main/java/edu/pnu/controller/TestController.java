package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {
	private final BoardRepository boardRepo;

	@GetMapping("/board")
	public List<Board> getBoards() {
		return boardRepo.findAll();
	}

	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		return boardRepo.findById(seq).orElse(null);
	}

	@PostMapping("/board")
	public Board postBoard(Board board) {
		board.setCreateDate(new Date()); // 현재 시간 설정
	    board.setCnt(0L);                   // 조회수 0으로 초기화
		return boardRepo.save(board);
	}

	@PutMapping("/board")
	public Board putBoard(Board board) {
		Board p = boardRepo.findById(board.getSeq()).get();
		if (board.getTitle() != null) {
			p.setTitle(board.getTitle());
		}
		if (board.getContent() != null) {
			p.setContent(board.getContent());
		}
		return boardRepo.save(p);
	}

	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board dd = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return dd;
	}

}
