package khv.healthlenge.domain.dto.admin.challenge;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import khv.healthlenge.domain.entity.challenge.ChStatus;
import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.challenge.category.ChCategory;
import khv.healthlenge.domain.entity.feed.Feed;
import lombok.Getter;

@Getter
public class ChallengeListDTO {
	//챌린지 pk
	private long chNo;
	//챌린지 카테고리
	private ChCategory category;
	//챌린지 타이틀
	private String title;
	//챌린지 진행상태
	private ChStatus status;
	//챌린지 시작 날짜
	private LocalDate date;
	//챌린지 대표이미지
	private String fileUrl;
	//참여한 회원 수
	private int memberNum;
	
	public ChallengeListDTO(Challenge e) {
		this.chNo= e.getChNo();
		this.category= e.getCategory();
		this.title= e.getTitle();
		this.status= e.getStatus();
		this.date= e.getStartDate();

		this.fileUrl= e.getAdFile().getUrl()+e.getAdFile().getName();
		
		Set<Feed> feeds= e.getFeeds();
		Iterator<Feed> iter= feeds.iterator();
		while(iter.hasNext()) {
			this.memberNum++;
			System.out.println(iter.next());
		}
	}
}
