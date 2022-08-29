package khv.healthlenge.domain.entity.faq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter

@Entity(name = "faq")
public class FaqEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fno;
	
	private String question; //질문
	private String answer; //응답
	
	@Column(name = "divs")
	@Enumerated(EnumType.STRING)
	private Div div;

}
