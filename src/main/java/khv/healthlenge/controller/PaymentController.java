package khv.healthlenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.order.OrderInsertDTO;
import khv.healthlenge.domain.dto.point.PointInsertDTO;
import khv.healthlenge.service.OrderService;
import khv.healthlenge.service.StoreService;

@Controller
public class PaymentController {
	
	@Autowired
	private OrderService orderService;
	
	//실제 결제
	@ResponseBody
	@PostMapping("/common/payment")
	public boolean payment(OrderInsertDTO dto) {
		return orderService.save(dto);
	}
	
	//결제창으로 이동!
	@GetMapping("/payment/go/page")
	public String page(OrderInsertDTO dto, Model model) {
		System.out.println(dto.getEmail());
		if(dto.getEmail().equals("false")) {
			return "redirect:/login";
		}
		return orderService.info(dto, model);
	}
	
	//구매확정 후 포인트 부여!
	@ResponseBody
	@PostMapping("/order/givePoint")
	public int givePoint(PointInsertDTO dto) {
		return orderService.givePoint(dto);
	}
	
}