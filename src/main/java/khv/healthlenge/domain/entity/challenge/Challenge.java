package khv.healthlenge.domain.entity.challenge;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import khv.healthlenge.domain.entity.challenge.category.ChCategory;
import khv.healthlenge.domain.entity.feed.Feed;
import khv.healthlenge.domain.entity.file.admin.AdFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Challenge {
	//챌린지 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long chNo;
	
	//챌린지 카테고리
	@ManyToOne
	@JoinColumn(name = "caNo", unique = true)
	private ChCategory category;
	public Challenge setCategory(ChCategory category) {
		this.category= category;
		return this;
	}
	
	//챌린지 제목
	@Column(nullable = false)
	private String title;
	
	//챌린지 내용
	@Column(nullable = false)
	private String content;
	
	//챌린지 시작 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(columnDefinition = "timestamp")
	private LocalDate startDate;
	
	//챌린지 총 기간
	@Column(nullable = false)
	private int period;
	
	//챌린지 금액
	@Column(nullable = false)
	private int amount;
	
	//챌린지 대표 이미지
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adFno", nullable = false)
	private AdFile adFile;
	public Challenge setFile(AdFile file) {
		this.adFile= file;
		return this;
	}
	
	//챌린지 진행 상태
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ChStatus status;
	
	//참여한 회원 + 인증 피드
	@OneToMany(mappedBy = "challenge")
	private Set<Feed> feeds= new HashSet<>();
}
