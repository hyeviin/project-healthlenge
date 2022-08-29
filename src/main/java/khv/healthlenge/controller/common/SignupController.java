package khv.healthlenge.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.service.MailService;
import khv.healthlenge.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SignupController {

	private final MemberService meService;
	
	private final MailService maService;
	
	//회원가입
	@PostMapping("/common/signup")
	public String signup(MemberInsertDTO dto, HttpServletRequest request) {
		return meService.save(dto, request);
	}
	
	
	////////////////////////비밀번호 재설정////////////////////////
	//페이지 이동
	@GetMapping("/common/pass-find")
	public String passFindPage(){
		return "/sign/findPass";
	}
	
	//이메일 찾기
	@PostMapping("/common/pass-find")
	public String passFind(int check, String email, Model model) {
		if(check==0) {
			model.addAttribute("not-email", "이메일 인증을 하지 않았습니다!");
			return "redirect:/member/pass-find";
		}
		return meService.findPass(email, model);
	}
	
	//비밀번호 재설정
	@PostMapping("/common/change-pass")
	public String changePass(long mno, String pass, Model model) {
		return meService.changePass(mno, pass, model);
	}
	
	
	////////////////////////이메일 인증////////////////////////
	//받은 사용자 이메일로 메일 보내기
	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String email) {
		System.out.println(email);
		return maService.mailSend(email);
	}
	
	//인증에 필요한 시간생성
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestGetMailKey(HttpSession session) {	
		System.out.println("마지막 접속시간 :"+session.getLastAccessedTime());
		System.out.println("생성시간 :"+session.getCreationTime());
		System.out.println("유지시간 :" +session.getMaxInactiveInterval());
		//System.out.println(email);
		return (String)session.getAttribute("mailKey");
	}

	//인증완료/시간만료시
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.removeAttribute("mailKey");
	}
}
