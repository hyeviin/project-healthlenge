package khv.healthlenge.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Data
public class PassChangeDTO {
	
	private long memberNo;
	private String pass;
	
	public PassChangeDTO(Member e) {
		this.memberNo= e.getMno();
		System.out.println("DTO: " + e.getPass());
		this.pass= e.getPass();
	}
	
	public PassChangeDTO passEncode(PasswordEncoder pe) {
		this.pass= pe.encode(pass);
		return this;
	}
}
