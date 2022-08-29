package khv.healthlenge.domain.entity.notice;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import khv.healthlenge.domain.entity.member.BaseTimeEntity;
import khv.healthlenge.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate//실제로 update처리하는 컬럼만 쿼리에 적용된다.
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Notice extends BaseTimeEntity{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long nno;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private int count;

	//회원번호와 연결합니다.
	@ManyToOne
	@JoinColumn(name = "mno")
	private Member member;
		
	public Notice setMno(Member member) {
		this.member= member;
		return this;
	}
	
}
