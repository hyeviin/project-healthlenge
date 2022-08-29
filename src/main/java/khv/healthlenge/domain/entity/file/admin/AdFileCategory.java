package khv.healthlenge.domain.entity.file.admin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AdFileCategory {
	
	EVENT("EVENT", "이벤트 배너"),
	CATEGORY("CATEGORY", "토픽 아이콘"),
	CHALLENGE("CHALLENGE", "챌린지");
	
	public final String categoryName;
	public final String title;
}
