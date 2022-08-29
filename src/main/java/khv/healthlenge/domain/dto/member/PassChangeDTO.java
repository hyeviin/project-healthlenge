package khv.healthlenge.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Data;

@Data
public class PassChangeDTO {
	private long mno;
	private String email;
	private String pass;
	
	public PassChangeDTO(Member e) {
		this.mno= e.getMno();
		this.email= e.getEmail();
		this.pass= e.getPass();
	}
	
	public PassChangeDTO passEncode(PasswordEncoder pe) {
		this.pass= pe.encode(pass);
		return this;
	}
}
