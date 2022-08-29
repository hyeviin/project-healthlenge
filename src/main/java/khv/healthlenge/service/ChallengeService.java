package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface ChallengeService {

	ModelAndView boardIndexList(ModelAndView mv, int divNo, String email);
	
	//게시판 카테고리 불러오기
	String boardIndexCategory(Model model);

	String detail(Model model, long cno);

	Boolean like(String email, long no, String topic);

}
