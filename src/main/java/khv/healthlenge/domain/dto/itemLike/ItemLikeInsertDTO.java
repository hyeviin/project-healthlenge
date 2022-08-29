package khv.healthlenge.domain.dto.itemLike;

import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntity;
import lombok.Data;

@Data
public class ItemLikeInsertDTO {
	private String email;
	private long ino;
	private int count;
	private int price;
	
	public ItemLikeInsertDTO priceCal(int count, int price) {
		this.price= price * count;
		return this;
	}
	
	public ItemLikeEntity toEntity() {
		return ItemLikeEntity.builder()
				//.orderPrice(price)
				.build();
	}
}
