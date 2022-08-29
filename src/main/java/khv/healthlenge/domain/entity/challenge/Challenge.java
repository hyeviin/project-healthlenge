package khv.healthlenge.domain.entity.challenge;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import khv.healthlenge.domain.entity.challenge.enums.Price;
import khv.healthlenge.domain.entity.challenge.enums.Status;
import khv.healthlenge.domain.entity.challenge.enums.Topic;
import khv.healthlenge.domain.entity.challenge.enums.TotalDate;
import khv.healthlenge.domain.entity.file.FileEntity;
import khv.healthlenge.domain.entity.member.BaseTimeEntity;
import khv.healthlenge.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@DynamicInsert//insert시 Entity에 null데이터는 퀄리에 적용시키지 않아요
@DynamicUpdate//실제로 update처리하는 컬럼만 쿼리에 적용된다.
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Challenge extends BaseTimeEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long cno;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	private String sub;
	
	private boolean isShow;
	
	//배너에 보여줘야한다면 보여줍니다.
	public Challenge updateIsShow(boolean isShow) {
		this.isShow= isShow;
		return this;
	}
	
	//회원번호와 연결합니다.
	@ManyToOne
	@JoinColumn(name = "mno")
	private Member member;
	
	public Challenge setMno(Member member) {
		this.member= member;
		return this;
	}
	
	//이미지와 연결합니다. 연관관계생각해보기(1개 아니면 여러개?)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fno", nullable = false)
	private FileEntity file;
	
	public Challenge setFile(FileEntity file) {
		this.file= file;
		return this;
	}
	
	//이넘들 연결
	@Builder.Default
	@ElementCollection//이걸 저장해주는 테이블을 만들겠다??
	//이 친구는 주제이기때문에 주제를 저장해주는 테이블을 만들겠다?
	//아래 저장된 이넘정보를 데이터로 넣겠습니다.
	@Enumerated(EnumType.STRING)//숫자로 넣으면 꼬일 수 있기때문에 문자로 저장하는게 좋습니다.
	//젠장 수정해야겠네
	Set<Topic> topics= new HashSet<>(); 
	
	public Challenge addTopic(Topic topic) {
		topics.add(topic);
		return this;
	}
	
	public Challenge removeTopic(Topic topic) {
		topics.remove(topic);
		return this;
	}
	
	@Builder.Default
	@ElementCollection
	@Enumerated(EnumType.STRING)
	Set<Status> statuss= new HashSet<>(); 
	
	public Challenge addStatus(Status status) {
		statuss.add(status);
		return this;
	}
	
	public Challenge removeStatus(Status status) {
		statuss.remove(status);
		return this;
	}
	
	@Builder.Default
	@ElementCollection
	@Enumerated(EnumType.STRING)
	Set<TotalDate> totalDates= new HashSet<>(); 
	
	public Challenge addTotalDate(TotalDate totalDate) {
		totalDates.add(totalDate);
		return this;
	}
	
	public Challenge removeTotalDate(TotalDate totalDate) {
		totalDates.remove(totalDate);
		return this;
	}
	
	@Builder.Default
	@ElementCollection
	@Enumerated(EnumType.STRING)
	Set<Price> prices= new HashSet<>(); 
	
	public Challenge addPrice(Price price) {
		prices.add(price);
		return this;
	}
	
	public Challenge removePrice(Price price) {
		prices.remove(price);
		return this;
	}
}
