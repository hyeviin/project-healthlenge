package khv.healthlenge.service;

import org.springframework.ui.Model;

import khv.healthlenge.domain.dto.member.MemberUpdateDTO;

public interface MyService {

	String indexMove(Model m, String email, String topic);

	String infoChagne(Model m, MemberUpdateDTO dto);

}
