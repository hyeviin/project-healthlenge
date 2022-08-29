package khv.healthlenge.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import khv.healthlenge.service.MemberService;
import khv.healthlenge.service.OrderService;

@Controller
public class MyPageController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/my/myIndex")
	public String myIndexPage() {
		return "/my/index";
	}
	
	@GetMapping("/my/info/pass")
	public String myPassPage() {
		return "my/info/pass";
	}
	
	@GetMapping("/my/info/chagne")
	public String myInfoPage(Principal principal, Model model) {
		//System.out.println("controller name: "+principal.getName());
		return memberService.myInfoPage(principal.getName(), model);
	}
	
	@GetMapping("/my/order/list")
	public String ordersList(Principal principal, Model model) {
		//return "/my/orders/orders";
		return orderService.list(principal.getName(), model);
	}
	
}
