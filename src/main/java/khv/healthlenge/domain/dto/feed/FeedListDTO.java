package khv.healthlenge.domain.dto.feed;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import khv.healthlenge.domain.dto.feed.file.FeFileListDTO;
import khv.healthlenge.domain.entity.feed.Feed;
import lombok.Getter;

@Getter
public class FeedListDTO {
	//feed pk
	private long feNo;
	//feed -> challenge: no, title
	private long chNo;
	private String chTitle;
	//feed -> member: no, nickName
	private long mno;
	private String nickName;
	//feed -> imgFile
	private List<FeFileListDTO> feFiles;
	//feed -> create date
	private LocalDateTime createdDate;
	
	
	public FeedListDTO(Feed e) {
		this.feNo= e.getFeNo();
		
		this.chNo= e.getChallenge().getChNo();
		this.chTitle= e.getChallenge().getTitle();
		
		this.mno= e.getMember().getMno();
		this.nickName= e.getMember().getNickname();
		
		this.feFiles= e.getFeFiles().stream().map(FeFileListDTO::new).collect(Collectors.toList());
		
		this.createdDate= e.getCreatedDate();
	}
}
