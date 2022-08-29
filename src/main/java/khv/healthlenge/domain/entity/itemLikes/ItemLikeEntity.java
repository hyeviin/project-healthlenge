package khv.healthlenge.domain.entity.itemLikes;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import khv.healthlenge.domain.entity.BaseTimeEntity;
import khv.healthlenge.domain.entity.item.Item;
import khv.healthlenge.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class ItemLikeEntity extends BaseTimeEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ilNo;
	private int count;
	
	//회원번호와 조인
	@JoinColumn(name = "mno", nullable = false) //email
	@ManyToOne(cascade = CascadeType.DETACH)//defalut:fetch = FetchType.EAGER
	private Member member;
	
	//세팅하기 위한 편의 메서드
	public ItemLikeEntity addMember(Member member) {
		this.member= member;
		return this;
	}
	
	//FK칼럼 설정: 선택된 상품과 연관관계
	@JoinColumn(name = "ino", nullable = false)
	@ManyToOne(cascade = CascadeType.DETACH) //즉시로딩
	private Item items;
	
	//세팅하기 위한 편의 메서드
	public ItemLikeEntity addItem(Item itme) {
		this.items= itme;
		return this;
	}
	
}
