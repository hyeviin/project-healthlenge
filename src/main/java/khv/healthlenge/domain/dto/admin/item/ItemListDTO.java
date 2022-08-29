package khv.healthlenge.domain.dto.admin.item;

import khv.healthlenge.domain.entity.item.Item;
import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntity;
import lombok.Data;

@Data
public class ItemListDTO {
	private long itmeNo;
	private String name;
	private int price;
	private String fileUrl;
	private String category;
	private int sale;
	private int stock;
	private int totalPrice;
	private String unit;

	public ItemListDTO(ItemLikeEntity ile){
		this(ile.getItems());
	}
	
	public ItemListDTO(Item e) {
		this.itmeNo= e.getIno();
		this.name= e.getName();
		this.price= e.getPrice();
		this.category= e.getCategorys().toString();
		this.sale= e.getSale();
		this.stock= e.getStock();
		
		if(e.isRate()) {
			double temp= (e.getPrice()*(e.getSale())/100);
			this.unit= "%";
			this.totalPrice= e.getPrice() - (int)(Math.round(temp/100))*100;
		}
		
		if(!e.isRate()){
			this.unit= "원";
			this.totalPrice= e.getPrice()-e.getSale();
		}
		
//		this.files= e.getFiles().stream().map(ItemFileDTO::new)
//										.collect(Collectors.toList());
		e.getItFiles().forEach(fileEntity->{
		if(fileEntity.isDefImg())fileUrl= fileEntity.getUrl()+fileEntity.getName();
		});
	}
}