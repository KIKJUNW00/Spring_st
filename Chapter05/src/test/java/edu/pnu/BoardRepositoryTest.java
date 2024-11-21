package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;

//	@DisplayName("입력테스트")
	@Test
	@Order(3)
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("두 번째 게시글");
		board.setWriter("테스터");
		board.setContent("잘 등록되나요?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
//		boardRepo.save(Board.builder()
//						.title("제목"));
	}

//	@DisplayName("조회테스트")
	@Test
	@Order(1)
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}

//	@DisplayName("수정테스트")
	@Test
	@Order(2)
	public void testUpdateBoard() {
		System.out.println("==1번 게시글 조회 ==");
		Board board = boardRepo.findById(6L).get();
		System.out.println("==게시글 제목 수정==");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}

//	@DisplayName("삭제 테스트")
	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}

}
