package khv.healthlenge.domain.dto.faq;

import khv.healthlenge.domain.entity.faq.Div;
import khv.healthlenge.domain.entity.faq.FaqEntity;
import lombok.Getter;

@Getter
public class FaqListDTO {
	private long faqNo;
	private String question;
	private String answer;
	private Div div;
	
	public FaqListDTO(FaqEntity e) {
		this.faqNo= e.getFno();
		this.question= e.getQuestion();
		this.answer= e.getAnswer();
		this.div= e.getDiv();
	}
}
