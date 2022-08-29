package khv.healthlenge.domain.entity.file;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileCategory {
	EVENT("EVENT", "이벤트 배너"),
	CATEGORY("CATEGORY", "토픽 아이콘"),
	BOARD("BOARD", "챌린지"),
	TOGETHER("TOGETHER", "챌린지인증"),
	ITEM("ITEM", "상품");
	
	public final String categoryName;
	public final String title;
}
