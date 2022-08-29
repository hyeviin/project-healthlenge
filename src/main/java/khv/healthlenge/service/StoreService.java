package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.itemLike.ItemLikeInsertDTO;
import khv.healthlenge.domain.dto.order.OrderInsertDTO;

public interface StoreService {

	String storeCategory(Model model);

	String detail(Model model, long no);

	ModelAndView storeIndexList(ModelAndView mv, int divNo, String email);

	Boolean itemLike(ItemLikeInsertDTO dto);
}
