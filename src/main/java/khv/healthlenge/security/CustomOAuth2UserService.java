package khv.healthlenge.security;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// 소셜로그인 인증완료 상태
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// DB저장
		return saveSocialUser(oAuth2User, registrationId);
	}

	private OAuth2User saveSocialUser(OAuth2User oAuth2User, String registrationId) {
		String name = null;
		String email = null;
		String pass = null;
		if (registrationId.equals("google")) {
			name = oAuth2User.getAttribute("name");
			email = oAuth2User.getAttribute("email");
			pass = oAuth2User.getAttribute("sub");

			// registrationId에 담겨있는 내용물 출력
//			Map<String, Object> userInfo= oAuth2User.getAttributes();
//			userInfo.keySet().forEach(name -> {
//				System.out.println(name + ", " + userInfo.get(name));
//			});
		} else if (registrationId.equals("naver")) {

			Map<String, Object> response = oAuth2User.getAttribute("response");
			name = (String) response.get("name");
			email = (String) response.get("email");
			pass = (String) response.get("id");

		} else if (registrationId.equals("kakao")) {
			Map<String, Object> attributes = oAuth2User.getAttributes();
			Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");

			email = (String) kakao_account.get("email");
			Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
			name = (String) properties.get("nickname");
			pass = "1234";
		}

		Optional<CustomUserDetails> result = memberRepository.findByEmail(email).map(CustomUserDetails::new);// 일반회원 또는
																												// 소셜회원
																												// 중
																												// 존재하는지
																												// 체크
		if (result.isPresent()) {
			// 이미등록된회원
			return result.get();
		}

		// 가입이 되지않은 회원은 소셜정보로 회원가입
		Member entity = memberRepository
				.save(Member.builder().email(email).name(name).pass(passwordEncoder.encode(pass)).userIp("127.0.0.1")
						.isSocial(true).build().addRole(MemberRole.USER));

		return new CustomUserDetails(entity);
	}

}
