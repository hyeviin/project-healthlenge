package khv.healthlenge.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.admin.category.CategoryInsertDTO;
import khv.healthlenge.domain.dto.admin.challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.admin.images.ImagesInsertDTO;
import khv.healthlenge.domain.dto.admin.item.ItemInsertDTO;


public interface ManagementService {
	
	//각자 관리 페이지 이동시 처리될 서비스
	String list(Model m, String topic);
	
	//index페이지에 사용될 사진 업데이트 service
	boolean updateIsShow(long no, String topic, boolean isShow);
	
	//DB에서 삭제 서비스
	boolean delect(long no, String topic);
	
	////////////////DB저장////////////////
	//홈 배너 등록
	String imagesSave(MultipartFile vimg, ImagesInsertDTO dto, int num);
	//카테고리 등록
	String categorySave(MultipartFile vimg, CategoryInsertDTO dto, int num);
	//챌린지 등록페이지 이동시 카테고리 테이블 내용 저장하기
	String chWrite(Model m);
	//챌린지 등록 
	String challengeSave(MultipartFile vimg, ChallengeInsertDTO dto);
	//사진들 temp에 저장
	String tempFileUpload(MultipartFile file);
	//상품 등록
	String itemSave(ItemInsertDTO dto);
}
