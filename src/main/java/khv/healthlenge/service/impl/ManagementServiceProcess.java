package khv.healthlenge.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.dto.admin.category.CategoryInsertDTO;
import khv.healthlenge.domain.dto.admin.category.CategoryListDTO;
import khv.healthlenge.domain.dto.admin.challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.admin.challenge.ChallengeListDTO;
import khv.healthlenge.domain.dto.admin.images.ImagesInsertDTO;
import khv.healthlenge.domain.dto.admin.images.ImagesListDTO;
import khv.healthlenge.domain.dto.admin.item.ItemInsertDTO;
import khv.healthlenge.domain.dto.admin.item.ItemListDTO;
import khv.healthlenge.domain.dto.feed.FeedListDTO;
import khv.healthlenge.domain.entity.challenge.ChallengeRepository;
import khv.healthlenge.domain.entity.challenge.category.ChCategory;
import khv.healthlenge.domain.entity.challenge.category.ChCategoryRepository;
import khv.healthlenge.domain.entity.feed.FeedRepository;
import khv.healthlenge.domain.entity.file.admin.AdFile;
import khv.healthlenge.domain.entity.file.admin.AdFileCategory;
import khv.healthlenge.domain.entity.file.admin.AdFileRepository;
import khv.healthlenge.domain.entity.file.item.ItFile;
import khv.healthlenge.domain.entity.item.Category;
import khv.healthlenge.domain.entity.item.Item;
import khv.healthlenge.domain.entity.item.ItemRepository;
import khv.healthlenge.service.ManagementService;
import khv.healthlenge.util.MyFileUtils;

@Service
public class ManagementServiceProcess implements ManagementService {
	
	@Autowired
	private AdFileRepository adFileRepository;
	
	@Autowired 
	private ChCategoryRepository categoryRepository;
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	//각자 관리 페이지 리스트 보여주기
	@Override
	public String list(Model m, String topic) {

		if(topic.equals("images")) {
			//홈 배너 관리 페이지
			AdFileCategory category= AdFileCategory.EVENT;
			m.addAttribute("images",
					adFileRepository.findAllByCategoryOrderByNum(category)//IsShow가 true인 것들만 찾아서 가져와라
					.stream().map(ImagesListDTO::new).collect(Collectors.toList())
					);
		}
		if(topic.equals("category")) {
			//카테고리 관리 페이지
			m.addAttribute("category",
					categoryRepository.findAll()
					.stream().map(CategoryListDTO::new).collect(Collectors.toList())
					);
		}
		if(topic.equals("challenge")) {
			//챌린지 관리 페이지
			m.addAttribute("challenge", challengeRepository.findAll()
					.stream().map(ChallengeListDTO::new).collect(Collectors.toList()));
		}
		if(topic.equals("feed")) {
			//피드 관리 페이지
			m.addAttribute("feeds", feedRepository.findAll()
					.stream().map(FeedListDTO::new).collect(Collectors.toList()));
		}
		if(topic.equals("item")) {
			//상품 관리 페이지
			m.addAttribute("items", 
					itemRepository.findAll()//Entity로 가져오고
					.stream().map(ItemListDTO::new).collect(Collectors.toList())
					);
		}
		if(topic.equals("QaA")) {
			//Q&A 관리 페이지
		}
		
		return "admin/" + topic + "/list";
	}
	
	//배너에 사용될 사진 업데이트
	@Override
	public boolean updateIsShow(long no, String topic, boolean isShow) {
		
		if(topic.equals("images")) {
			//홈 배너 관리
			Optional<AdFile> result= adFileRepository.findById(no);
			if(result.isEmpty()) return false;
			adFileRepository.save(result.get().updateIsShow(isShow));
		}
		if(topic.equals("category")) {
			//카테고리 관리
			Optional<ChCategory> result= categoryRepository.findById(no);
			if(result.isEmpty()) return false;
			categoryRepository.save(result.get().updateIsShow(isShow));
		}
		
		return true;
	}
	
	//DB에서 삭제
	@Override
	public boolean delect(long no, String topic) {

		if(topic.equals("images")) {
			//홈 배너 관리 페이지
			adFileRepository.deleteById(no);
		}
		if(topic.equals("category")) {
			//카테고리 관리 페이지
			categoryRepository.deleteById(no);
		}
		if(topic.equals("challenge")) {
			//챌린지 관리 페이지
			challengeRepository.deleteById(no);
		}
		if(topic.equals("feed")) {
			//피드 관리 페이지
			feedRepository.deleteById(no);
		}
		if(topic.equals("item")) {
			//상품 관리 페이지
			itemRepository.deleteById(no);
		}
		if(topic.equals("QaA")) {
			//Q&A 관리 페이지
			
		}
		return true;
	}

	////////////////DB저장////////////////
	//홈 배너 등록
	@Override
	public String imagesSave(MultipartFile vimg, ImagesInsertDTO dto, int num) {
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/images/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		dto.addFileData(fileData, num);

		//db저장
		adFileRepository.save(dto.toEntity());
		return "redirect:/admin/images";
	}
	//카테고리 등록
	@Override
	public String categorySave(MultipartFile vimg, CategoryInsertDTO dto, int num) {
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/category/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		dto.addFileData(fileData, num);

		//db저장
		categoryRepository.save(dto.toEntity());
		return "redirect:/admin/category";
	}

	//챌린지 등록
	//페이지 이동시 카테고리 저장하기
	@Override
	public String chWrite(Model m) {
		m.addAttribute("categorys", categoryRepository.findAll()
				.stream().map(CategoryListDTO::new).collect(Collectors.toList())
				);
		
		return "admin/challenge/write";
	}
	
	//챌린지 저장
	@Override
	public String challengeSave(MultipartFile vimg, ChallengeInsertDTO dto) {
		
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/challenge/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		
		dto.addFileData(fileData);
		
		//db저장
		//게시글테이블 저장
		challengeRepository.save(dto.toEntity()
									.setCategory(ChCategory.builder().caNo(dto.getTopic()).build())
									.setFile(AdFile.builder()
											.category(AdFileCategory.CHALLENGE)
											.url(dto.getUrl()).name(dto.getName())
											.size(dto.getSize())
											.title(dto.getTitle())
											.build()));
		return "redirect:/admin/challenge";
	}
	//사진 temp에 저장	
	@Override
	public String tempFileUpload(MultipartFile file) {
		String path="/images/temp/";
		ClassPathResource cpr= new ClassPathResource("static" + path);
		
		try {
			File location= cpr.getFile();
			File targetFile= new File(location, file.getOriginalFilename());
			file.transferTo(targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path+file.getOriginalFilename();
	}


	//상품 등록
	@Override
	public String itemSave(ItemInsertDTO dto) {
		System.out.println("item: " + dto);
		//서버에 있숨다
		String def= dto.getDefImgName();
		String add= dto.getAddImgName();
		String path= "/images/temp/";
		
		ClassPathResource cpr= new ClassPathResource("static" + path);
		
		Item entity= dto.toEntity();
		
		try {
			File root= cpr.getFile();
			
			ClassPathResource target= new ClassPathResource("static" + "/images/item/");
			
			File defFile= new File(root, def);
			defFile.renameTo(new File(target.getFile(), def));
			String name= defFile.getName();
			long size= defFile.length();
			
			ItFile defItemFile= ItFile.builder().name(name).title(dto.getName())
									.url("/images/item/").size(size).isDefImg(true).build();
			System.out.println("def-file: " + defItemFile);
			File addFile= new File(root, add);
			addFile.renameTo(new File(target.getFile(), add));
			name= defFile.getName();
			size= defFile.length();
			ItFile addItemFile= ItFile.builder().name(name).title(dto.getName())
									.url("/images/item/").size(size).isDefImg(false).build();
			
			System.out.println("add-file: " + addItemFile);
			itemRepository.save(entity.addCategory(Category.valueOf(dto.getCategory())).addFile(addItemFile).addFile(defItemFile));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/item";
	}
}
