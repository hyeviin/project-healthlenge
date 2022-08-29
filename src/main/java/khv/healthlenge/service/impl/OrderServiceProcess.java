package khv.healthlenge.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.item.ItemListDTO;
import khv.healthlenge.domain.dto.order.OrderInsertDTO;
import khv.healthlenge.domain.dto.order.OrderListDTO;
import khv.healthlenge.domain.dto.point.PointInsertDTO;
import khv.healthlenge.domain.entity.delivery.Delivery;
import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.items.ItemRepository;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.domain.entity.order.Orders;
import khv.healthlenge.domain.entity.order.OrdersRepository;
import khv.healthlenge.domain.entity.point.PointEntity;
import khv.healthlenge.domain.entity.point.PointEntityRepository;
import khv.healthlenge.service.OrderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceProcess implements OrderService{
	@Autowired
	private final OrdersRepository ordersRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private PointEntityRepository pointEntityRepository;

	@Override
	public boolean save(OrderInsertDTO dto) {
		
		Member member= memberRepository.findByEmail(dto.getEmail()).get();
		Item item= itemRepository.findById(dto.getIno()).get();
		

		ordersRepository.save(dto.toEntity()
				.addMember(member).addItems(item)
				.addDelivery(Delivery.builder()
						.zipcode(dto.getZipcode())
						.address(dto.getAddress())
						.detail(dto.getDetail())
						.status("결제완료").build())
				.setPoint(PointEntity.builder().totalPoint(0).givePoint(0).build())
				);
		return true;
	}

	@Override
	public String info(OrderInsertDTO dto, Model model) {
		ItemListDTO item= itemRepository.findById(dto.getIno()).map(ItemListDTO::new).orElse(null);
		
		model.addAttribute("buyItem", item);
		dto.totalPrice(item.getTotalPrice());
		System.out.println("total price: " + dto.getPrice());
		model.addAttribute("info", dto);
		return "/payment/oneby";
	}
	
	//결제 완료 후 결제내역 리스트
	@Override
	public String list(String name, Model model) {
		Member member= memberRepository.findByEmail(name).get();

		model.addAttribute("orderList", ordersRepository.findAllByMember(member)
				.stream().map(OrderListDTO::new)
				.collect(Collectors.toList()));
		return "/my/orders/orders";
	}
	
	//구매확정 후 포인트 부여!
	@Override
	public int givePoint(PointInsertDTO dto) {
//		Optional<Orders> order= ordersRepository.findByPoint(dto.getPno());
//		
//		if(order.isEmpty()) {
//			
//		}
//		
////		Optional<PointEntity> result= pointEntityRepository.findByOrder();
//		
////		if(result.isEmpty()) {
////		}
		return 0;
	}
}
