package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class queryMethodTest2 {
	
	@Autowired
	private BoardRepository boardRepo;
	
//	1. 테스트 데이터를 100건 입력
//	• cnt는 random으로 0~100까지 입력
//	@BeforeEach
	public void dataPrepare() {
		Random random = new Random();
		for (int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(random.nextLong(101L));
			boardRepo.save(board);

		}
	}
	
//	2. title에 “1” 이 포함되는 데이터 출력
//	• Where title like “%1%”
	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("====> " + board.toString());
		}
	}
	
	
}
