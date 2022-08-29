package khv.healthlenge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.item.ItemDetailDTO;
import khv.healthlenge.domain.dto.item.ItemListDTO;
import khv.healthlenge.domain.dto.itemLike.ItemLikeEntityListDTO;
import khv.healthlenge.domain.dto.itemLike.ItemLikeInsertDTO;
import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntity;
import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntityRepository;
import khv.healthlenge.domain.entity.items.Category;
import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.items.ItemRepository;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.service.StoreService;

@Service
public class StoreServiceProcess implements StoreService {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ItemLikeEntityRepository itemLikeEntityRepository;
	
	@Override
	public String storeCategory(Model model) {

		model.addAttribute("storeList", itemRepository.findAll().stream()
					.map(ItemListDTO::new).collect(Collectors.toList()));
		return "/store/index";
	}
	
	//자세히
	@Override
	public String detail(Model model, long no) {
		model.addAttribute("itemDetail", 
				itemRepository.findById(no).map(ItemDetailDTO::new).get()
				);
		return "store/itemDetial";
	}
	
	@Override
	public ModelAndView storeIndexList(ModelAndView mv, int divNo, String email) {
		
		Category categorise[]= Category.values();
		
		if(divNo == 6) {
			Member member= memberRepository.findByEmail(email).get();
			
			mv.addObject("itemlist", itemLikeEntityRepository.findAllByMember(member)
							.stream().map(ItemLikeEntityListDTO::new).toList());
			mv.setViewName("/layout/store/like");
			return mv;
			
		}else {
			Category category= categorise[divNo];
			
//		faqEntityRepository.findAllByDiv(div).forEach(System.out::println);
//		List<FaqListDTO> result= faqEntityRepository.findAllByDiv(div).stream()
//				.map(FaqListDTO::new).collect(Collectors.toList());
			
			mv.addObject("itemlist", itemRepository.findAllByCategorys(category).stream()
			.map(ItemListDTO::new).collect(Collectors.toList()));
		}
		
		mv.setViewName("/layout/store/list");
		return mv;
	}
	
	//좋아요! 누르면 작동하는데 DB 저장시 true, 삭제시 false! 
	@Override
	public Boolean itemLike(ItemLikeInsertDTO dto) {
		Member member= memberRepository.findByEmail(dto.getEmail()).get();
		//Item item= itemRepository.findById(dto.getIno()).get(); 내가 한 것
		Item item= Item.builder().ino(dto.getIno()).build(); //선생님이 하신 것
		Optional<ItemLikeEntity> result= itemLikeEntityRepository.findByMemberAndItems(member, item);
		
		if(result.isEmpty()) {
			dto.priceCal(dto.getCount(), dto.getPrice());
			//itemLikeEntityRepository.save(dto.);
			System.out.println("실행");
			return true;
		}
		
		itemLikeEntityRepository.deleteById(result.get().getIlNo());
		
		return false;
	}

}
