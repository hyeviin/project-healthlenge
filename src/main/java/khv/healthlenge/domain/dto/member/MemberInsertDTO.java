package khv.healthlenge.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Data;

@Data
public class MemberInsertDTO {
	private String email;
	private String nickname;
	private String pass;
	private String userIp;
	private boolean isSocial;
	
	public MemberInsertDTO passEncode(PasswordEncoder pe) {
		this.pass= pe.encode(pass);
		return this;
	}
	
	//입력받은 dto-> Member 엔티티에 매핑: Member 객체생성
	public Member toMember() {
		return Member.builder()
					.email(email).nickname(nickname).pass(pass).userIp(userIp).isSocial(isSocial)
					.build();
	}
		
}
