package khv.healthlenge.domain.dto.admin.challenge;

import java.time.LocalDate;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.challenge.ChStatus;
import khv.healthlenge.domain.entity.challenge.Challenge;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChallengeInsertDTO extends FileData{
	//챌린지 제목
	private String title;
	//챌린지 내용
	private String content;
	//카테고리번호
	private long topic;
	//기간
	private int period;
	//목표금액
	private int amount;
	
	public Challenge toEntity() {
		return Challenge.builder()
				.title(title).content(content).startDate(LocalDate.now())
				.period(period).amount(amount).status(ChStatus.BEFORE)
				.build();
	}
	
	public void addFileData(FileData fileData) {
		url = fileData.getUrl();
		name = fileData.getName();
		size = fileData.getSize();
	}
}
