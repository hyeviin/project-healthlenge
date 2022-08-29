package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.order.OrderInsertDTO;
import khv.healthlenge.domain.dto.point.PointInsertDTO;

public interface OrderService {

	boolean save(OrderInsertDTO dto);

	String info(OrderInsertDTO dto, Model model);

	String list(String name, Model model);


	int givePoint(PointInsertDTO dto);

}
