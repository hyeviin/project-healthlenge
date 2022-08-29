package khv.healthlenge.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/qAa/*")
@Controller
public class QaAController {
	
	//문의사항 기본 페이지 이동
	@GetMapping("index")
	public String index() {
		return "sign/signup";
	}
}
