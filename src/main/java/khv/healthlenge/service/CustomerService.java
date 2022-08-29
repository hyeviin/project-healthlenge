package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {

	void list(Model model, String divName);

	void list(Model model, int divNo);

	ModelAndView list(ModelAndView mv, int divNo);

}
