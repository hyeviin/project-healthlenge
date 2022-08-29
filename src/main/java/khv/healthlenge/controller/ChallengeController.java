package khv.healthlenge.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.service.ChallengeService;

@Controller
public class ChallengeController {
	
	@Autowired
	private ChallengeService challengeService;
	
	//게시판에 카테고리 불러오기! 하..제발...
	@GetMapping("/common/challenge")
	public String list(Model model) {
		return challengeService.boardIndexCategory(model);
	}
	
	//게시판에 카테고리 목록 불러오기
	@GetMapping("/common/challenge/{divNo}")
	public ModelAndView boardIndexList(ModelAndView mv, @PathVariable int divNo, String email) {
		return challengeService.boardIndexList(mv, divNo, email);
	}
	//챌린지 등록시 파일 업로드
	@ResponseBody
	@PostMapping("/admin/uploadSummernoteImg")
	public String uploadSummernoteImg(MultipartFile file) {
		//System.out.println(file.getContentType());
		if(!file.getContentType().contains("image") ) return null;
		
		String url="/images/summernote/";
		ClassPathResource cpr=new ClassPathResource("static"+url);
		String orgName=file.getOriginalFilename();
		String saveName=UUID.randomUUID()+"_"+orgName;
		//System.out.println(saveName);
		
		try {
			//??이게 문제라고합니다?왜???
			File location= cpr.getFile();
			file.transferTo(new File(location, saveName));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url+saveName;
	}
	
	//챌린지 자세히
	@GetMapping("/common/challenge/user/{cno}")
	public String challengeDetail(Model model, @PathVariable long cno) {
		return challengeService.detail(model, cno);
	}
	
	//좋아요로직!!
	@ResponseBody
	@PostMapping("/common/{topic}/like/{no}")
	public Boolean challengeLike(String email, @PathVariable long no, @PathVariable String topic) {
		return challengeService.like(email, no, topic);
	}
}
