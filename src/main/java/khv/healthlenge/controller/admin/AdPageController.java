package khv.healthlenge.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import khv.healthlenge.service.ManagementService;

@RequestMapping("/admin/*")
@Controller
public class AdPageController {
	
	@Autowired
	private ManagementService service;
	
	//list 페이지 이동
	@GetMapping("{topic}")
	public String list(Model m, @PathVariable String topic) {
		//관리자 메인 페이지 이동
		if(topic.equals("index")) return "admin/index";
		
		return service.list(m, topic);
	}
	
	//등록 페이지 이동
	@GetMapping("{topic}/write")
	public String write(@PathVariable String topic, Model m) {
		if(topic.equals("challenge")) {
			return service.chWrite(m);
		}
		return "admin/" + topic + "/write";
	}

	
	//index페이지에 사용될 사진 업데이트
	@ResponseBody
	@PostMapping("{topic}/{no}/isShow")
	public boolean updateIsShow(@PathVariable long no, @PathVariable String topic, boolean isShow) {
		return service.updateIsShow(no, topic, isShow);
	}
	
	//데이터 삭제
	@ResponseBody
	@PostMapping("{topic}/{no}/delect")
	public boolean delectVisual(@PathVariable long no, @PathVariable String topic) {
		return service.delect(no, topic);
	}
}
