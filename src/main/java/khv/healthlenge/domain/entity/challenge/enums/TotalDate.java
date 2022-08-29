package khv.healthlenge.domain.entity.challenge.enums;

import lombok.Getter;

@Getter
public enum TotalDate {
	
	TWO("2주"),
	FOUR("4주");
	
	public final String name;
	
	private TotalDate(String name) {
		this.name= name;
	}
	
	public String getDisplayValue( ) {
		return name;
	}
	
}
