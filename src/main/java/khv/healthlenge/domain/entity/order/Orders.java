package khv.healthlenge.domain.entity.order;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import khv.healthlenge.domain.entity.BaseTimeEntity;
import khv.healthlenge.domain.entity.delivery.Delivery;
import khv.healthlenge.domain.entity.item.Item;

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
public class Orders extends BaseTimeEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ono;

	@Column(nullable = false)
	private String uid;
	
	@Column(nullable = false)
	private String merchant;
	
	@Column(nullable = false)
	private long amount;
	
	private int applyNum;
	
	@Column(nullable = false)
	private int itemCount;
	
	
	@Column(nullable = false)
	private String status;

	
	//회원번호와 조인
	//이 테이블에 FK키 칼럼을 생성함
	@JoinColumn(name = "mno", nullable = false)
	@ManyToOne//defalut:fetch = FetchType.EAGER
	private Member member;
	
	public Orders addMember(Member member) {
		this.member= member;
		return this;
	}
	
	//배송지관리번호와 조인
	@JoinColumn(name = "dno", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Delivery delivery;
	
	public Orders addDelivery(Delivery delivery) {
		this.delivery= delivery;
		return this;
	}
	
	//테이블 기준 -> 부모: item, 자식: Orders
	//읽을 때 지연로딩이 기본입니다.
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "orderItem")
	@Builder.Default
	private List<Item> items= new Vector<Item>();
	
	public Orders addItems(Item item) {
		this.items.add(item);
		return this;
	}
}
