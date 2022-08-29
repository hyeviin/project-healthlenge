package khv.healthlenge.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberInfoDTO;
import khv.healthlenge.domain.dto.member.MemberInsertDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.domain.dto.member.PassFindDTO;
import khv.healthlenge.domain.entity.delivery.Delivery;
import khv.healthlenge.domain.entity.delivery.DeliveryRepository;
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
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Override
	public String save(MemberInsertDTO dto, HttpServletRequest request) {
		dto.passEncode(pe);
		dto.setUserIp(request.getRemoteAddr());//프록시서버(카페24) 또는 로드 밸런스를 통해 서버에 접속한경우 127.0.0.1
		
		Member member= dto.toMember().addRole(MemberRole.USER);
		//user롤 적용회원가입
		repository.save(member);
		return "redirect:/login";
	}
	
	//비밀번호 찾기 -> 아이디가 있는지 먼저 확인합니다!
	@Override
	public String findPass(String email, Model model) {
		Optional<Member> result= repository.findByEmail(email);
		 
		if(result.isEmpty()) {
			model.addAttribute("not-email", "회원가입하지 않았거나, 탈퇴한 회원입니다.");
			return "/member/pass-find";
		}
		

		model.addAttribute("member", repository.findByEmail(email).map(PassFindDTO::new).get());
		return "sign/changePass";
	}
	
	//새로운 비밀번호 받아서 새로 저장하기
	@Override
	public String changePass(PassChangeDTO dto, Model model) {
		Member entity= repository.findById(dto.getMemberNo()).orElseThrow();

		entity.changePass(dto.passEncode(pe));
		repository.save(entity);
		
		return "redirect:/login";
	}

	@Override
	public String myInfoPage(String name, Model model) {
		model.addAttribute("memberInfo", repository.findByEmail(name).map(MemberInfoDTO::new).get());
		return "/my/info/info";
	}

	//삭제삭제!!!!!!
	@Override
	public String delMember(boolean isCheck, long mno) {

		Optional<Member> result= repository.findById(mno);
			
		repository.save(result.get().updateIsDeleted(isCheck));
		return "redirect:/login";
	}

}
