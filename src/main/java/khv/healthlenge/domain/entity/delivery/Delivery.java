package khv.healthlenge.domain.entity.delivery;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Delivery {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dno;

	@Column(nullable = false)
	private int zipcode;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String detail;
	
	private String status;
	
	@OneToOne(mappedBy = "delivery")
	private Orders orders;
}
