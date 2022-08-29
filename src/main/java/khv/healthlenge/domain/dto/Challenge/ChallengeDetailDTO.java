package khv.healthlenge.domain.dto.Challenge;

import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.file.FileEntity;
import lombok.Data;

@Data
public class ChallengeDetailDTO {
	private long boardNo;
	private String title;
	private String content;
	private String topic;
	private FileEntity file;
	//시작날짜, 참여인원 추가예정
	
	public ChallengeDetailDTO(Challenge b) {
		this.boardNo= b.getCno();
		this.title= b.getTitle();
		this.content= b.getContent();
		this.topic= b.getTopics().toString();
		this.file= b.getFile();
	}
}
