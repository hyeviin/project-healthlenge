package khv.healthlenge.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.security.MemberRole;
import khv.healthlenge.service.MemberService;


@Service
public class MemberServiceProcess implements MemberService {

	//DAO : jpa-Repository, mybatis-Mapper, jdbc-Connection(singleton으로 구성)
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	PasswordEncoder pe;
	
	//회원가입
	@Override
	public String save(MemberInsertDTO dto, HttpServletRequest request) {
		dto.passEncode(pe);
		dto.setUserIp(request.getRemoteAddr());//프록시서버(카페24) 또는 로드 밸런스를 통해 서버에 접속한경우 127.0.0.1
		
		Member member= dto.toMember().addRole(MemberRole.USER);
		
		//user롤 적용회원가입
		repository.save(member);
		return "redirect:/commom/signin";
	}
	
	//입력받은 이메일이 DB에 있는지 확인
	@Override
	public String findPass(String email, Model model) {
		Optional<Member> result= repository.findByEmail(email);
		 
		if(result.isEmpty()) {
			model.addAttribute("not-email", "회원가입하지 않았거나, 탈퇴한 회원입니다.");
			return "/member/pass-find";
		}
		
		System.out.println("model에 저장된 회원번호가 잘 있는데???왜??? " + repository.findByEmail(email).map(PassChangeDTO::new).get().getMno());

		model.addAttribute("member", repository.findByEmail(email).map(PassChangeDTO::new).get());
		return "sign/changePass";
	}

	//비밀번호 재설정
	@Override
	public String changePass(long mno, String pass, Model model) {
		Member entity= repository.findById(mno).orElseThrow();

		PassChangeDTO dto= repository.findById(mno).map(PassChangeDTO::new).get();
		dto.setPass(pass);
		
		entity.changePass(dto.passEncode(pe));
		repository.save(entity);
		
		return "redirect:/login";
	}

}
