package khv.healthlenge.domain.dto.admin.item;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInsertDTO extends FileData{
	
	private String name;
	private String category;
	private int price;
	private int sale;
	private boolean rate;//false: 원, true: % 
	private int stock;
	private String defImgName;
	private String addImgName;
	private String content;
	
	public Item toEntity() {
		return Item.builder().name(name).price(price).sale(sale).isRate(rate)
							.stock(stock).content(content).build();
	}
}
