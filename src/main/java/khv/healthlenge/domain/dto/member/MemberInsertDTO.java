package khv.healthlenge.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.entity.delivery.Delivery;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.security.MemberRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInsertDTO {
	private String email;
	private String name;
	private String pass;
	private String userIp;
	private boolean isSocial;
	//주소도 같이 받아요
	private int zipcode;
	private String address;
	private String detail;
	
	public MemberInsertDTO passEncode(PasswordEncoder pe) {
		this.pass= pe.encode(pass);
		return this;
	}
	
	//입력받은 dto-> Member 엔티티에 매핑: Member 객체생성
	public Member toMember() {
		return Member.builder()
				.email(email).name(name).pass(pass).userIp(userIp).isSocial(isSocial)
				.build();
	}
	
	public Delivery toDelivery() {
		return Delivery.builder()
				.zipcode(zipcode).address(address)
				.detail(detail).status("결제완료").build();
	}
}
