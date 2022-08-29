package khv.healthlenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.security.MemberRole;

@SpringBootTest
class HealthlengeApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Test
	void 관리자생성() {
		memberRepository.save(Member.builder()
				.email("admin@admin.com").pass(pe.encode("aaaa")).nickname("admin1").userIp("127.0.0.1")
				.build().addRole(MemberRole.USER));
	}

}
