package khv.healthlenge.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberRole {
	ADMIN("ROLE_ADMIN","관리자"),
	USER("ROLE_USER","회원");
	
	public final String roleName;
	public final String title;
}
