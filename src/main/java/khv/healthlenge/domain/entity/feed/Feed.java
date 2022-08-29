package khv.healthlenge.domain.entity.feed;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.file.feed.FeFile;
import khv.healthlenge.domain.entity.member.Member;
import lombok.Getter;

@Getter
@Entity
public class Feed {
	
	//피드 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long feNo;
	
	//작성한 챌린지
	@ManyToOne
	@JoinColumn(name = "caNo", nullable = false)
	private Challenge challenge;
	
	//작성한 회원
	@ManyToOne
	@JoinColumn(name = "mno", nullable = false)
	private Member member;
	
	//피드 내용
	@Column(nullable = false)
	private String content;
	
	//1:N 관계= 양방향으로 설정합니다.
	//피드 파일들
	@Column(nullable = false)
	@OneToMany(mappedBy = "feed")
	private Set<FeFile> feFiles= new HashSet<>();
	
	//피드 작성 시간
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;
}
