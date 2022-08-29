package khv.healthlenge.domain.dto.point;

import khv.healthlenge.domain.entity.order.Orders;
import khv.healthlenge.domain.entity.point.PointEntity;
import lombok.Data;

@Data
public class PointInsertDTO {
	private long pno;
	private int price;
	private Orders order;
	private int oldPoint;
	
	public PointInsertDTO setOldPoint(int getPoint) {
		this.oldPoint= getPoint;
		return this;
	}
	
	public PointEntity toEntity() {
		int givePoint= (int)(price*0.05);
		int totalPoint= oldPoint + givePoint;
		return PointEntity.builder()
				.givePoint(givePoint).totalPoint(totalPoint).build();
	}
}
