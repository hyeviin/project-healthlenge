package khv.healthlenge.domain.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import khv.healthlenge.domain.dto.item.ItemListDTO;
import khv.healthlenge.domain.entity.order.Orders;
import lombok.Data;

@Data
public class OrderListDTO {
	private long ono;
	private LocalDateTime createdDate;
	private String dStatus;
	private String iStatus;
	private String iName;
	private int iPrice;
	private String iFileUrl;
	private int iCount;
	private long pno;
	
	public OrderListDTO(Orders o) {
		this.ono= o.getOno();
		this.createdDate= o.getCreatedDate();
		this.dStatus= o.getDelivery().getStatus();
		this.iStatus= o.getStatus();
		this.pno= o.getPoint().getPno();
		o.getItems().stream().map(ItemListDTO::new).forEach(dto->{
			this.iName= dto.getName();
			this.iPrice= dto.getPrice();
			this.iFileUrl= dto.getFileUrl();
			this.iCount= o.getItemCount();
		});
	}
	
}
