package khv.healthlenge.domain.dto.Challenge;

import java.util.Set;

import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.challenge.ChallengeLikeEntity;
import khv.healthlenge.domain.entity.challenge.enums.Status;
import khv.healthlenge.domain.entity.challenge.enums.Topic;
import lombok.Getter;

@Getter
public class ChallengeListDTO {
	private long boardNo;
	private long file;
	private String title;
	private String content;
	private String sub;
	private boolean isShow;
	private String url;
	private String orgName;
	private Set<Status> status;
	private String topic;
	private String name;
	
	public ChallengeListDTO(ChallengeLikeEntity cle) {
		this(cle.getChallenge());
	}
	
	public ChallengeListDTO(Challenge e) {
		this.boardNo= e.getCno();
		this.file= e.getFile().getVno();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.sub = e.getSub();
		this.isShow = e.isShow();
		this.url= e.getFile().getUrl();
		this.orgName= e.getFile().getOrgName();
		this.status= e.getStatuss();
		
		e.getTopics().forEach(topics ->{
			this.topic= topics.lowName; 
		});
		
		this.name= e.getMember().getName();
	}
}
