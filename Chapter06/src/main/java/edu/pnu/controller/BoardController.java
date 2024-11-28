package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;



@SessionAttributes("member")
@Controller
public class BoardController {

	@Autowired 
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		
		if (member.getId() ==null) {
			return  "redirect:login";
		}
		// =============== 방법 1 ======================
//		List<Board> boardList = new ArrayList<>();
//		for(long i=1; i<=10L; i++) {
//			boardList.add(Board.builder()
//								.seq(i)
//								.title("게시판 프로그램 테스트")
//								.writer("도우너")
//								.content("게시판 프로그램 테스트입니다")								
//								.build()
//					);
//			model.addAttribute("boardList", boardList);
//			
//		}
//		return "getBoardList";
		
		// ================ 방법 2 ================
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		return "getBoardList";		
	}
	
	@GetMapping("/insertBoardView")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
//	리턴이 void면 url명.html을 호출
//	리턴이 String이면 return명.html을 호출
	@GetMapping("/hello")
//	public void hello(Model model){
	public String hello(Model model) {
	model.addAttribute("greeting", "Hello 타임리프.^^");
	return "hello";
	}
	
//	 ModelAndView 객체를 생성해서 Model과 View를 설정해서 리턴
//	@GetMapping("/hello")
//	public ModelAndView hello() {
//	ModelAndView mv = new ModelAndView();
//	mv.addObject("greeting", "Hello 타임리프.^^");
//	mv.setViewName("hello");
//	return mv;
//	}

}
