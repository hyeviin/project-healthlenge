package khv.healthlenge.domain.entity.items;

import lombok.Getter;

@Getter
public enum Category {
	VITAMIN("비타민"),
	LACTOBACILLUS("유산균"),
	OMEGA3("오메가3"),
	LUTEIN("루테인"),
	COLLAGEN("콜라겐"),
	ETC("기타");
	
	final String name;
	
	private Category(String name) {
		this.name= name;
	}
	
	public String getDisplayValue( ) {
		return name;
	}
	
}
