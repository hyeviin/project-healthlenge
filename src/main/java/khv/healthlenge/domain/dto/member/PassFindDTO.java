package khv.healthlenge.domain.dto.member;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PassFindDTO {
	private long memberNo;
	private String email;
	
	public PassFindDTO(Member m) {
		this.memberNo= m.getMno();
		this.email= m.getEmail();
	}
}
