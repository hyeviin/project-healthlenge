package khv.healthlenge.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import khv.healthlenge.domain.dto.member.MemberUpdateDTO;
import khv.healthlenge.service.MyService;

@RequestMapping("/my/*")
@Controller
public class UsPageController {
	
	@Autowired
	private MyService service;
	
	//메인 페이지 이동
	@GetMapping("index")
	public String index(Model m, Principal principal) {
		return service.indexMove(m, principal.getName(), "index");
	}
	
	//정보변경 페이지 이동
	@GetMapping("infoChagne")
	public String infoChagneMv(Model m, Principal principal) {
		return service.indexMove(m, principal.getName(), "chagne");
	}
	
	//정보변경 service
	@PostMapping("infoChagne")
	public String infoChagne(Model m, MemberUpdateDTO dto) {
		System.out.println("????: " + dto.getInfo());
		return service.infoChagne(m, dto);
	}
	
}
