package khv.healthlenge.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/common/*")
@Controller
public class PageController {
	
	//회원가입 페이지 이동
	@GetMapping("signup")
	public String signup() {
		return "sign/signup";
	}
	
	//로그인 페이지 이동
	@GetMapping("signin")
	public String signin() {
		return "sign/signin";
	}
	
	//챌린지 페이지 이동
	@GetMapping("challenge")
	public String challenge() {
		return "challenge/index";
	}
	
	//인증 페이지 이동
	@GetMapping("asdf")
	public String asdf() {
		return "/";
	}
	
	//헬스토어 페이지 이동
	@GetMapping("store")
	public String store() {
		return "store/index";
	}
	
	//검색 페이지 이동
	@GetMapping("search")
	public String search() {
		return "/";
	}
}
