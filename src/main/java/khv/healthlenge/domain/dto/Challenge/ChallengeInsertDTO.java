package khv.healthlenge.domain.dto.Challenge;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.challenge.Challenge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeInsertDTO extends FileData{
	
	private String title;
	private String content;
	private String email;
	private String topic;
	private String totaldate;
	private String price;
	
	
	//입력받은 dto-> board 엔티티에 매핑: board 객체생성
	public Challenge toBoard() {
		return Challenge.builder()
				.title(title).content(content)
				.build();
	}
	
	public void addFileData(FileData fileData) {
		url = fileData.getUrl();
		orgName = fileData.getOrgName();
		size = fileData.getSize();
	}
}
