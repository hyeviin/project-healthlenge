package khv.healthlenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberListDTO;
import khv.healthlenge.domain.dto.member.MemberUpdateDTO;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.service.MyService;

@Service
public class MyServiceProcess implements MyService {
	
	@Autowired
	private MemberRepository meRepository;
	
	//마이페이지 처음 화면에 닉네임 출력하기
	@Override
	public String indexMove(Model m, String email, String topic) {
		m.addAttribute("memInfo", meRepository.findByEmail(email)
				.map(MemberListDTO::new).get());
		
		if(topic.equals("chagne")) {
			return "/my/info/info";
		}
		
		return "/my/index";
	}
	
	//개인정보 변경
	@Override
	public String infoChagne(Model m, MemberUpdateDTO dto) {
		
		Member entity= meRepository.findById(dto.getMno()).orElseThrow();
		
		entity.changeInfo(dto);
		
		meRepository.save(entity);
		
		return "redirect:/my/index";
	}

}
