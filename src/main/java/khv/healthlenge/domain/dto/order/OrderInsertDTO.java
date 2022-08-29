package khv.healthlenge.domain.dto.order;

import khv.healthlenge.domain.entity.order.Orders;
import lombok.Data;

@Data
public class OrderInsertDTO {
	//결제 DTO
	private String uid;
	private String merchant;
	private long amount;
	private int applyNum;
	private int itemCount;
	private long ino;
	private String email;
	private int price;
	//주소 DTO
	private int zipcode;
	private String address;
	private String detail;
	
	
	public Orders toEntity() {
		return Orders.builder()
				.uid(uid).merchant(merchant).amount(amount)
				.applyNum(applyNum).itemCount(itemCount)
				.status("결제완료").build();
	}
	
	public OrderInsertDTO totalPrice(int price) {
		this.price= price * itemCount;
		return this;
	}
}
