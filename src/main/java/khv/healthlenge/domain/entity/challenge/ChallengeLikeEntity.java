package khv.healthlenge.domain.entity.challenge;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import khv.healthlenge.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class ChallengeLikeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long clNo;
	
	//FK칼럼 설정: 선택된 상품과 연관관계
	@JoinColumn(name = "cno", nullable = false)
	@ManyToOne //즉시로딩
	private Challenge challenge;
	
	//FK칼럼 설정: 상품 좋아요 테이블과 연관관계
	//ManyToOne쪽이 주인관계, 부모라고 혼자 생각하고있음
	@JoinColumn(name = "mno", nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH)
	private Member member;
}
