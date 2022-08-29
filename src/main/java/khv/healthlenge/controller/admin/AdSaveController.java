package khv.healthlenge.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.admin.category.CategoryInsertDTO;
import khv.healthlenge.domain.dto.admin.challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.admin.images.ImagesInsertDTO;
import khv.healthlenge.domain.dto.admin.item.ItemInsertDTO;
import khv.healthlenge.service.ManagementService;

@RequestMapping("/admin/*")
@Controller
public class AdSaveController {
	@Autowired
	private ManagementService service;

	////////////////DB저장////////////////
	//홈 배너 등록
	@PostMapping("images/write")
	public String imagesSave(MultipartFile vimg, ImagesInsertDTO dto, int num) {
		return service.imagesSave(vimg, dto, num);
	}
	
	//카테고리 등록
	@PostMapping("category/write")
	public String categorySave(MultipartFile vimg, CategoryInsertDTO dto, int num) {
		return service.categorySave(vimg, dto, num);
	}
	
	//챌린지 등록
	@PostMapping("challenge/write")
	public String challengeSave(MultipartFile vimg, ChallengeInsertDTO dto) {
		return service.challengeSave(vimg, dto);
	}
	
	//파일 업로드
	@ResponseBody
	@PostMapping("uploadSummernoteImg")
	public String uploadSummernoteImg(MultipartFile file) {
		//System.out.println(file.getContentType());
		if(!file.getContentType().contains("image") ) return null;
		
		String url="/images/summernote/";
		ClassPathResource cpr= new ClassPathResource("static" + url);
		String orgName= file.getOriginalFilename();
		String saveName= UUID.randomUUID() + "_" + orgName;
		
		try {
			//??이게 문제라고합니다?왜???
			File location= cpr.getFile();
			file.transferTo(new File(location, saveName));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url+saveName;
	}
	
	//사진 등록 -> Temp저장용
	@ResponseBody
	@PostMapping("/admin/tempFile")
	public String challengeTempFile(MultipartFile file) {
		return service.tempFileUpload(file);
	}
	
	//상품 등록 -> DB저장용
	@PostMapping("item/write")
	public String adminItemSave(ItemInsertDTO dto) {
		return service.itemSave(dto);
	}
	
	//Q&A 등록
}
