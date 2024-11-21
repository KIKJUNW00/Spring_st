package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

//JpaRepository<T, ID>
//T : 엔티티의 클래스 타입
//• ID : 식별자 타입(@Id로 매핑한 식별자 변수의 타입)
public interface BoardRepository extends JpaRepository<Board, Long>{
//	쿼리 메소드 작성
	List<Board> findByTitle(String searchKeyword);
	
//	쿼리 메소드 작성 (Like 연산자 사용)
	List<Board> findByContentContaining(String serachKeyword);
	
//	쿼리 메소드 작성 (여러 조건 사용)
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
//	쿼리 메소드 작성 (데이터 정렬)
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
//	title에 “1” 이 포함되는 데이터 출력 //Where title like “%1%
	List<Board> findByTitleContaining(String searchKeyword);
}
