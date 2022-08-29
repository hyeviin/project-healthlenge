package khv.healthlenge.domain.entity.challenge.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
	BEFORE("시작 예정"),
	PROGRESS("진행중"),
	END("종료");
	
	public final String statusName;
}
