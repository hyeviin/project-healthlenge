package khv.healthlenge.domain.dto.member;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Data;

@Data
public class MemberInfoDTO {
	private long mno;
	private boolean deleted;
	private String name;
	
	public MemberInfoDTO(Member e) {
		this.mno= e.getMno();
		this.name= e.getName();
		this.deleted= e.isDeleted();
	}
}
