package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	public List<Member> getMembers() {
		
		return memberRepo.findAll();
	}
	
	public Member getMemberById(Integer id) {
		return memberRepo.findById(id).orElse(null);
	}
	
	public Member postMember(Member member) {
		return memberRepo.save(member);
	}
	
	public Member putMember(Member member) {
		Member m = memberRepo.findById(member.getId()).get();
		if (member.getName() != null) {
			m.setName(member.getName());
		}
		if (member.getPass() != null) {
			m.setPass(member.getPass());
		}
		return memberRepo.save(m);
	}
	
	
	@DeleteMapping("/board/{seq}")
	public Member deleteMember(@PathVariable Integer id) {
		Member dd = memberRepo.findById(id).get();
		memberRepo.deleteById(id);
		return dd;
	}
}








