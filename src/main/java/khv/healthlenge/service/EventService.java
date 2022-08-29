package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.Challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.event.EventInsertDTO;
import khv.healthlenge.domain.dto.item.ItemInsertDTO;

public interface EventService {

	//이벤트 메소드//
	String eventList(Model model);

	String eventSave(MultipartFile vimg, EventInsertDTO dto, int num);

	String indexEventlist(Model model);

	//카테고리 메소드//
	String categoryList(Model model);

	String categorySave(MultipartFile vimg, EventInsertDTO dto, int num);

	String indexCategorylist(Model model);

	//챌린지 메소드//
	String boardList(Model model);
	
	String boardSave(MultipartFile vimg, ChallengeInsertDTO dto);

	String indexBoardlist(Model model);
	
	//상품 메소드//
	String itemList(Model model);
	String itemSave(ItemInsertDTO dto);
	
	//공통
	boolean updateIsShow(boolean isShow, long vno);
	boolean updateBnoIsShow(boolean isShow, long bno);
	
	boolean delectVisual(long vno, String topic);
//	boolean delectBnoVisual(long bno);

	String tempFileUpload(MultipartFile file);

	String indexItemList(Model model);

	String detail(Model model, long bno, String topic);

}
