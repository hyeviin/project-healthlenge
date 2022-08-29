package khv.healthlenge.security;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import khv.healthlenge.domain.entity.member.Member;
import lombok.Getter;

@Getter
public class CustomUserDetails extends User implements OAuth2User, UserDetails {
	
	private static final long serialVersionUID = 1L;
	private String email;	// DB에서 PK 값
    private String nickname;	//닉네임
    private Map<String, Object> attributes;
    
    //Super 클래스에 super()존재하지않고 다른생성자(오버로딩생성자)만 존재경우 생성자 명시
  	public CustomUserDetails(Member e) {
  		super(e.getEmail(), e.getPass(), e.getRoleSet().stream()
  													.map(role->new SimpleGrantedAuthority(role.roleName))// Set<MemberRole> -> Collection<? extends GrantedAuthority> authorities
  													.collect(Collectors.toSet()));
  		email= e.getEmail();
  		nickname= e.getNickname();
  	}

  	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return nickname;
	}

}
