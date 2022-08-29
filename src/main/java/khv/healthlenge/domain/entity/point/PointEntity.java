package khv.healthlenge.domain.entity.point;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.member.BaseTimeEntity;
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
public class PointEntity extends BaseTimeEntity{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long pno;
	
	private int givePoint;
	
	@Column(nullable = false)
	private int totalPoint;
}
