package khv.healthlenge.domain.dto.member;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Data;

@Data
public class MemberListDTO {
	private long mno;
	private String nickName;
	private String info;
	private boolean deleted;
	
	public MemberListDTO (Member e) {
		super();
		this.mno= e.getMno();
		this.nickName= e.getNickname();
		this.info= e.getInfo();
		this.deleted= e.isDeleted();
	}
}
