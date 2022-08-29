package khv.healthlenge.domain.entity.challenge.enums;

import lombok.Getter;

@Getter
public enum Price {
	
	ONE("10,000원"),
	TWO("20,000원"),
	THREE("30,000원"),
	FOUR("40,000원"),
	FIVE("50,000원");
	
	public final String name;
	
	private Price(String name) {
		this.name= name;
	}
	
	public String getDisplayValue( ) {
		return name;
	}
	
}
