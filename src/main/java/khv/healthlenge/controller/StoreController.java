package khv.healthlenge.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.itemLike.ItemLikeInsertDTO;
import khv.healthlenge.service.StoreService;


@Controller
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@GetMapping("/common/store")
	public String storePage(Model model) {
		return storeService.storeCategory(model);
	}
	
	@GetMapping("/common/store/item/user/{no}")
	public String adminBoardWritePage(Model model, @PathVariable long no) {
		return storeService.detail(model, no);
	}
	
	@GetMapping("/common/store/{divNo}")
	public ModelAndView boardIndexList(ModelAndView mv, @PathVariable int divNo, String email) {
		return storeService.storeIndexList(mv, divNo, email);
	}
	
	@ResponseBody
	@PostMapping("/store/itemLike/")
	public Boolean itemLike(ItemLikeInsertDTO dto) {
		//System.out.println("email: " + dto.getEmail() + ", ino: " + dto.getIno());
		return storeService.itemLike(dto);
	}

}
