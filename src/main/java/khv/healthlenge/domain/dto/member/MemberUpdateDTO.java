package khv.healthlenge.domain.dto.member;

import lombok.Data;

@Data
public class MemberUpdateDTO {
	private long mno;
	private String nickName;
	private String info;
}
