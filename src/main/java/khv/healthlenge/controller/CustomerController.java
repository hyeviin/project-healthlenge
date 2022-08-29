package khv.healthlenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
//	@GetMapping("/customer/{divName}")
//	public String customerIndexPage(Model model, @PathVariable String divName) {
//
//		customerService.list(model, divName);
//		return "my/customer/index";
//	}
	
	@GetMapping("/customer")
	public ModelAndView customerIndexPage(ModelAndView mv) {
		mv.setViewName("my/customer/index");
		return mv;
	}
	
	@GetMapping("/customer/{divNo}")
	public ModelAndView customerIndexPage(ModelAndView mv, @PathVariable int divNo) {
		return customerService.list(mv, divNo);
	}
	
}
