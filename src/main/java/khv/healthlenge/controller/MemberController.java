package khv.healthlenge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.service.MailService;
import khv.healthlenge.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	
	private final MemberService service;
	private final MailService mailService;
	
	@PostMapping("/common/signup")
	public String signup(MemberInsertDTO dto, HttpServletRequest request) {
		//System.out.println("controller-email: " + dto.getEmail());
		return service.save(dto, request);
	}
	
	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String email) {
		System.out.println(email);
		return mailService.mailSend(email);
	}
	
	@GetMapping("/member/pass-find")
	public String passFindPage(){
		return "/sign/findPass";
	}
	
	@PostMapping("/common/pass-find")
	public String passFind(int check, String email, Model model) {
		if(check==0) {
			model.addAttribute("not-email", "이메일 인증을 하지 않았습니다!");
			return "redirect:/member/pass-find";
		}
		return service.findPass(email, model);
	}
	
	@PostMapping("/common/change-pass")
	public String changePass(PassChangeDTO dto, Model model) {
		System.out.println("Controller: " + dto.getMemberNo());
		System.out.println("Controller: " + dto.getPass());
		return service.changePass(dto, model);
	}
	//*/
	
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestGetMailKey(HttpSession session) {	
		System.out.println("마지막 접속시간 :"+session.getLastAccessedTime());
		System.out.println("생성시간 :"+session.getCreationTime());
		System.out.println("유지시간 :" +session.getMaxInactiveInterval());
		//System.out.println(email);
		return (String) session.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.removeAttribute("mailKey");
	}
	
	@PostMapping("/common/member/{mno}/deleted")
	public String delMember(boolean isCheck, @PathVariable long mno) {
		return service.delMember(isCheck, mno);
	}
}
