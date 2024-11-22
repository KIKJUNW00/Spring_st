package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
//	title에 “1” 이 포함되면서 cnt가 50보다 큰 데이터 출력
	List<Board> findByTitleContainingAndCntGreaterThan(String title, Long Cnt);
	
//	페이징**************************************************************************************************************************
//	List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
//	쿼리어노테이션************************************************************************************
	
//	위치기반 파라미터
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
//	– 이름 기반 파라미터
//	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
//	List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
	
//	– 특정 변수만 조회하기
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b"
			+ " WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(String searchKeyword);
	
	
}











