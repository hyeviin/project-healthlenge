package khv.healthlenge.domain.dto.item;

import java.util.List;
import java.util.stream.Collectors;

import khv.healthlenge.domain.entity.items.Item;
import lombok.Data;

@Data
public class ItemDetailDTO {
	//아이템
	private long itemNo;
	private String name;
	private int price;
	private int sale;
	private String content;
	private List<ItemFileDTO> files;
	private String defImgUrl;
	private int totalPrice;
	private String unit;

	
	public ItemDetailDTO(Item e) {
		this.itemNo= e.getIno();
		this.name= e.getName();
		this.price= e.getPrice();
		this.sale= e.getSale();
		this.content= e.getContent();
		this.files= e.getFiles().stream().map(ItemFileDTO::new)
				.collect(Collectors.toList());
		if(e.isRate()) {
			double temp= (e.getPrice()*(e.getSale())/100);
			this.unit= "%";
			this.totalPrice= e.getPrice() - (int)(Math.round(temp/100))*100;
		}
		
		if(!e.isRate()){
			this.unit= "원";
			this.totalPrice= e.getPrice()-e.getSale();
		}
		e.getFiles().forEach(fileEntity->{
			if(fileEntity.isDefImg())defImgUrl= fileEntity.getUrl()+fileEntity.getOrgName();
		});
	}
}
