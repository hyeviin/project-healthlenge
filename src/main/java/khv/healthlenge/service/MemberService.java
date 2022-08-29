package khv.healthlenge.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;

public interface MemberService {
	
	//회원가입
	String save(MemberInsertDTO dto, HttpServletRequest request);
	
	//이메일 찾기
	String findPass(String email, Model model);
	
	//비밀번호 재설정
	String changePass(long mno, String pass, Model model);

}
