package khv.healthlenge.domain.entity.challenge;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChStatus {
	BEFORE("시작 예정"),
	PROGRESS("진행중");
	
	public final String name;
}
