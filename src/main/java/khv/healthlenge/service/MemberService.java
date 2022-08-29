package khv.healthlenge.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;


public interface MemberService {

	String save(MemberInsertDTO dto, HttpServletRequest request);
	
	//비밀번호를 찾기위해 아이디가 있는지 확인
	String findPass(String email, Model model);
	
	//새로운 비밀번호 입력받아 저장하기
	String changePass(PassChangeDTO dto, Model model);

	String myInfoPage(String string, Model model);

	String delMember(boolean isCheck, long mno);


}
