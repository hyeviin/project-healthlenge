package khv.healthlenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.Challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.event.EventInsertDTO;
import khv.healthlenge.domain.dto.item.ItemInsertDTO;
import khv.healthlenge.service.EventService;

@Controller
public class AdminController {
	
	@Autowired
	private EventService eventService;

	
	////////////////////////////////////관리자 메인에서 페이지 이동/////////////////////////////////////////////////
	//관리자 메인 페이지
	@GetMapping("/admin")
	public String adminPage() {
		return "/admin/admin";
	}
	
	//홈 이벤트 관리 페이지
	@GetMapping("/admin/indexEvnet")
	public String adminIndexEvnetPage() {
		return "/admin/indexEvnet";
	}
	
	
	/////////////////////////////////이벤트 배너/////////////////////////////////////////////////
	//이벤트 배너 리스트 페이지
	@GetMapping("/admin/event")
	public String adminEventPage(Model model) {
		return eventService.eventList(model);
	}
	
	//이벤트 배너 등록 페이지
	@GetMapping("/admin/event/write")
	public String adminEventWritePage() {
		return "/admin/index/event/write";
	}
	
	//이벤트 배너 등록 컨트롤
	@PostMapping("/admin/event/visuals")
	public String adminEventSave(MultipartFile vimg, EventInsertDTO dto, int num) {
		return eventService.eventSave(vimg,dto, num);
	}
	
	
	/////////////////////////////////카테고리 관리/////////////////////////////////////////////////
	//카테고리 리스트 페이지
	@GetMapping("/admin/category")
	public String adminCategoryPage(Model model) {
		return eventService.categoryList(model);
	}
	
	//카테고리 등록 페이지
	@GetMapping("/admin/category/write")
	public String adminCategoryWritePage(Model model) {
		return "/admin/index/category/write";
	}
	
	//카테고리 등록 컨트롤
	@PostMapping("/admin/category/visuals")
	public String adminCategorySave(MultipartFile vimg, EventInsertDTO dto, int num) {
		return eventService.categorySave(vimg,dto, num);
	}
	
	/////////////////////////////////챌린지 관리/////////////////////////////////////////////////
	//챌린지 관리 리스트 페이지
	@GetMapping("/admin/challenge")
	public String adminBoardPage(Model model) {
		return eventService.boardList(model);
	}
	
	//챌린지 등록 페이지
	@GetMapping("/admin/challenge/write")
	public String adminBoardWritePage() {
		return "/admin/index/challenge/write";
	}
	
	@PostMapping("/admin/challenge/visuals")
	public String adminBoardSave(MultipartFile vimg, ChallengeInsertDTO dto) {
		return eventService.boardSave(vimg, dto);
	}
	
	//자세히 페이지
	@GetMapping("/admin/{topic}/{no}")
	public String adminBoardWritePage(Model model, @PathVariable long no, @PathVariable String topic) {
		return eventService.detail(model, no, topic);
	}

	/////////////////////////////////인덱스에 보여주는 컨트롤/////////////////////////////////////////////////
	//index페이지의 슬로건 배너 db가져오기
	@GetMapping("/index/evnets")
	public String indexEventlist(Model model) {
		return eventService.indexEventlist(model);
	}
	
	///index페이지의 카테고리 db가져오기
	@GetMapping("/index/categorys")
	public String indexCategorylist(Model model) {
		return eventService.indexCategorylist(model);
	}
	
	///index페이지의 챌린지 게시판 db가져오기
	@GetMapping("/index/boards")
	public String indexBoardlist(Model model) {
		return eventService.indexBoardlist(model);
	}
	
	//index페이지의 슬로건 배너 db가져오기
	@ResponseBody
	@PostMapping("/admin/visuals/{vno}/isShow")
	public boolean updateIsShow(@PathVariable long vno, boolean isShow) {
		return eventService.updateIsShow(isShow, vno);
	}
	
	//index페이지의 슬로건 배너 db가져오기
	@ResponseBody
	@PostMapping("/admin/board/{bno}/isShow")
	public boolean updateBnoIsShow(@PathVariable long bno, boolean isShow) {
		return eventService.updateBnoIsShow(isShow, bno);
	}
	
	//삭제 컨트롤
	@ResponseBody
	@PostMapping("/admin/{topic}/{vno}/delect")
	public boolean delectVisual(@PathVariable long vno, @PathVariable String topic) {
		return eventService.delectVisual(vno, topic);
	}
		
	/////////////////////////////////상품 관리/////////////////////////////////////////////////
	//상품 관리 리스트 페이지
	@GetMapping("/admin/item")
	public String adminItemPage(Model model) {
		return eventService.itemList(model);
	}
	
	@GetMapping("/common/item")
	public String indexItemPage(Model model) {
		return eventService.indexItemList(model);
	}
	
	//상품 등록 페이지
	@GetMapping("/admin/item/write")
	public String adminItemWritePage(Model model) {
		//model.addAttribute();
		return "admin/index/item/write";
	}
	
	//상품 등록 컨트롤->DB저장용
	@PostMapping("/admin/item/visuals")
	public String adminItemSave(ItemInsertDTO dto) {
		System.out.println("is rate: " + dto.isRate());
		return eventService.itemSave(dto);
	}
	
	//상품 등록 컨트롤->Temp저장용
	@ResponseBody
	@PostMapping("/admin/item/tempFile")
	public String adminItemTempFile(MultipartFile file) {
		return eventService.tempFileUpload(file);
	}
	
}
