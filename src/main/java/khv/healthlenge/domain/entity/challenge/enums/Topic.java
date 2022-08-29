package khv.healthlenge.domain.entity.challenge.enums;

import lombok.Getter;

//getter만들수도 있음 출력하고자 한다면!
@Getter
public enum Topic {
	CALENDAR("규칙적인생활", "CALENDAR"),
	EXERCISE("운동", "EXERCISE"),
	VEGETABLES("식습관", "VEGETABLES"),
	HEART("마음챙김", "HEART"),
	HOBBY("취미", "HOBBY"),
	COSMETICS("셀프케어", "COSMETICS"),
	ECO("에코/펫", "ECO"),
	START("오늘시작", "START"),
	NEW("신규", "NEW"),
	EVENT("이벤트", "EVENT"),
	POP("인기", "POP");
	
	public final String name;
	public final String lowName;
	
	private Topic(String name, String lowName) {
		this.name= name;
		this.lowName= lowName;
	}
	
	public String getDisplay( ) {
		return lowName;
	}
	
	public String getDisplayValue( ) {
		return name;
	}
}
