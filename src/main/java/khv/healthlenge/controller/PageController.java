package khv.healthlenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/login")
	public String login() {
		return "/sign/signin";
	}
	
	//인증 받지 않고 넘어가면 시큐리티는 모름.
	//그래서 인증을 받아야하는 페이지라고 알려주는 역할을 합니다.
	@GetMapping("/loginPage")
	public String loginPage() {
		return "index";
	}
	
	@GetMapping("/common/signup")
	public String signup() {
		return "/sign/signup";
	}
}
