package khv.healthlenge.domain.dto.itemLike;

import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntity;
import khv.healthlenge.domain.entity.items.Item;
import lombok.Data;

@Data
public class ItemLikeEntityListDTO {
	private long ilNo;
	private int ea;
	private long itemNo;
	private String name;
	private int sale;
	private int salePrice;
	private int oPrice;
	private int nPrice;
	private int totalPrice;
	private String fileUrl;
	
	public ItemLikeEntityListDTO(ItemLikeEntity ile) {
		this.ilNo= ile.getIlNo();
		this.ea= ile.getCount();
		
		Item item= ile.getItems();
		this.itemNo= item.getIno();
		this.name= item.getName();
		this.sale= item.getSale();
		//this.price
		if(item.isRate()) {
			double temp= (item.getPrice()*(this.sale)/100);
			this.salePrice= (int)(Math.round(temp/100))*100;
			this.oPrice= item.getPrice() - this.salePrice;
		}
		if(!item.isRate()) {
			this.oPrice= item.getPrice() - this.sale;
		}
		this.totalPrice= oPrice * ea;
		
		//this.fileUrl
		item.getFiles().forEach(fileEntity->{
			if(fileEntity.isDefImg())this.fileUrl= fileEntity.getUrl()+fileEntity.getOrgName();
		});
	}
}
