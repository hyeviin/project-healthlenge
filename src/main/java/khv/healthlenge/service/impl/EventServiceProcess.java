package khv.healthlenge.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.dto.Challenge.ChallengeDetailDTO;
import khv.healthlenge.domain.dto.Challenge.ChallengeInsertDTO;
import khv.healthlenge.domain.dto.Challenge.ChallengeListDTO;
import khv.healthlenge.domain.dto.event.EventInsertDTO;
import khv.healthlenge.domain.dto.event.EventListDTO;
import khv.healthlenge.domain.dto.item.ItemDetailDTO;
import khv.healthlenge.domain.dto.item.ItemInsertDTO;
import khv.healthlenge.domain.dto.item.ItemListDTO;
import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.challenge.ChallengeRepository;
import khv.healthlenge.domain.entity.challenge.enums.Price;
import khv.healthlenge.domain.entity.challenge.enums.Status;
import khv.healthlenge.domain.entity.challenge.enums.Topic;
import khv.healthlenge.domain.entity.challenge.enums.TotalDate;
import khv.healthlenge.domain.entity.file.FileCategory;
import khv.healthlenge.domain.entity.file.FileEntity;
import khv.healthlenge.domain.entity.file.FileRepository;
import khv.healthlenge.domain.entity.file.item.ItemFile;
import khv.healthlenge.domain.entity.items.Category;
import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.items.ItemRepository;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.service.EventService;
import khv.healthlenge.util.MyFileUtils;

@Service
public class EventServiceProcess implements EventService {
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ChallengeRepository challengeRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	/////////////////////////////////인덱스에 보여주는 컨트롤/////////////////////////////////////////////////
	//이벤트 친구들 불러오기
	@Override
	public String indexEventlist(Model model) {
		FileCategory category= FileCategory.EVENT;
		
		model.addAttribute("events", 
				fileRepository.findAllByCategorysAndIsShowOrderByNum(category, true)//IsShow가 true인 것들만 찾아서 가져와라
				.stream().map(EventListDTO::new).collect(Collectors.toList())
				);
		return "/layout/admin/event";
	}
	
	//카테고리 친구들 불러오기
	@Override
	public String indexCategorylist(Model model) {
		FileCategory category= FileCategory.CATEGORY;
		
		model.addAttribute("categorys", 
				fileRepository.findAllByCategorysAndIsShowOrderByNum(category, true)//IsShow가 true인 것들만 찾아서 가져와라
				.stream().map(EventListDTO::new).collect(Collectors.toList())
				);
		return "/layout/admin/category";
	}
	
	//챌린지 게시판 친구들불러
	@Override
	public String indexBoardlist(Model model) {
		Topic topic1= Topic.EVENT;
		Topic topic2= Topic.POP;

		model.addAttribute("topic1", 
				challengeRepository.findAllByTopicsAndIsShowOrderByCno(topic1, true)//IsShow가 true인 것들만 찾아서 가져와라
				.stream().map(ChallengeListDTO::new).collect(Collectors.toList())
				);
		
		model.addAttribute("topic2", 
				challengeRepository.findAllByTopicsAndIsShowOrderByCno(topic2, true)//IsShow가 true인 것들만 찾아서 가져와라
				.stream().map(ChallengeListDTO::new).collect(Collectors.toList())
				);
		return "/layout/admin/challenge";
	}
	
	//배너에 사용될 사진 업데이트 합니다.
	//@Transactionalan
	@Override
	public boolean updateIsShow(boolean isShow, long vno) {
		Optional<FileEntity> result = fileRepository.findById(vno);
		if(result.isEmpty()) return false;
		fileRepository.save(result.get().updateIsShow(isShow));
		return true;
	}
	
	@Override
	public boolean updateBnoIsShow(boolean isShow, long bno) {
		Optional<Challenge> result= challengeRepository.findById(bno);
		if(result.isEmpty()) return false;
		challengeRepository.save(result.get().updateIsShow(isShow));
		return true;
	}
	
	//삭제 컨트롤: 등록된 이벤트 친구들 삭제합니다.
	@Override
	public boolean delectVisual(long no, String topic) {
		if(topic.equals("visuals")) {
			fileRepository.deleteById(no);
		}
		if(topic.equals("category")) {
			fileRepository.deleteById(no);
		}
		if(topic.equals("challenge")) {
			challengeRepository.deleteById(no);
		}
		if(topic.equals("item")) {
			itemRepository.deleteById(no);
		}
		return true;
	}

	/////////////////////////////////이벤트 배너/////////////////////////////////////////////////
	//이벤트 배너 리스트 페이지: 이벤트에 분류된 친구들을 불러옵니다.
	@Override
	public String eventList(Model model) {
		FileCategory category= FileCategory.EVENT;
		
		model.addAttribute("events", 
				fileRepository.findAllByCategorysOrderByNum(category)//Entity로 가져오고
				.stream().map(EventListDTO::new).collect(Collectors.toList())
				);
		return "/admin/index/event/list";
	}
	
	//이벤트 배너 등록 페이지: 이벤트에 분류해서 친구들 저장합니다.
	//**물리주소에 저장해야합니다.**//
	@Override
	public String eventSave(MultipartFile vimg, EventInsertDTO dto, int num) {
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/visual/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		dto.addFileData(fileData, num);

		//db저장
		fileRepository.save(dto.toVisualFile().addCategory(FileCategory.EVENT));
		return "redirect:/admin/event";
	}
	
	
	/////////////////////////////category관리///////////////////////////////////////
	//카테고리 리스트 페이지: 카테고리에 분류된 친구들을 불러옵니다.
	@Override
	public String categoryList(Model model) {
		
		FileCategory category= FileCategory.CATEGORY;
		
		model.addAttribute("categorys", 
				fileRepository.findAllByCategorysOrderByNum(category)//Entity로 가져오고
				.stream().map(EventListDTO::new).collect(Collectors.toList())
				);
		return "/admin/index/category/list";
	}
	
	//카테고리 등록 페이지: 카테고리로 분류해서 저장합니다.
	//**물리주소에 저장해야합니다.**//
	@Override
	public String categorySave(MultipartFile vimg, EventInsertDTO dto, int num) {
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/category/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		dto.addFileData(fileData, num);

		//db저장
		fileRepository.save(dto.toVisualFile().addCategory(FileCategory.CATEGORY));
		return "redirect:/admin/category";
	}
	
	/////////////////////////////챌린지 관리///////////////////////////////////////
	//챌린지 리스트 페이지: 챌린지에 분류된 친구들을 불러옵니다.
	@Override
	public String boardList(Model model) {
		
		model.addAttribute("boards", 
				challengeRepository.findAll()//Entity로 가져오고
				.stream().map(ChallengeListDTO::new).collect(Collectors.toList())
				);
		
//		model.addAttribute("categorys", 
//				visualFileRepository.findAllByCategorysOrderByNum(category)//Entity로 가져오고
//				.stream().map(EventListDTO::new).collect(Collectors.toList())
//				);
		
		return "/admin/index/challenge/list";
	}
	
	//챌린지 자세히
	@Override
	public String detail(Model model, long no, String topic) {
		if(topic.equals("challenge")) {
			model.addAttribute("boardDetail", 
					challengeRepository.findById(no).map(ChallengeDetailDTO::new).get()
					);
		}
		if(topic.equals("item")) {
			model.addAttribute("itemDetail", 
					itemRepository.findById(no).map(ItemDetailDTO::new).get()
					);
			
		}
		return "/admin/index/"+ topic +"/detail";
	}
	
	//챌린지 등록 페이지: 챌린지로 분류해서 저장합니다.
	//**물리주소에 저장해야합니다.**//
	@Override
	public String boardSave(MultipartFile vimg, ChallengeInsertDTO dto) {
		Optional<Member> member= memberRepository.findByEmail(dto.getEmail());
		
		//파일 업로드
		//파일DTO = 클래스.static메서드
		String url = "/images/challenge/";// 아직 결정 안했지만 업로드할 서버 위치
		FileData fileData = MyFileUtils.upload(vimg, url);
		
		dto.addFileData(fileData);
		//db저장
		//게시글테이블 저장
		challengeRepository.save(dto.toBoard().addTopic(Topic.valueOf(dto.getTopic()))
								.addStatus(Status.BEFORE)
								.addPrice(Price.valueOf(dto.getPrice()))
								.addTotalDate(TotalDate.valueOf(dto.getTotaldate()))
								.setMno(member.get())
								.setFile(FileEntity.builder()
											.title(dto.getTitle()).sub(dto.getContent())
											.url(url).orgName(dto.getOrgName()).size(dto.getSize())
											.build().addCategory(FileCategory.BOARD)));
		return "redirect:/admin/challenge";
	}
	
	/////////////////////////////상품 관리///////////////////////////////////////
	//상품 리스트 페이지: 상품에 분류된 친구들을 불러옵니다.
	@Override
	public String itemList(Model model) {
		
		model.addAttribute("items", 
				itemRepository.findAll()//Entity로 가져오고
				.stream().map(ItemListDTO::new).collect(Collectors.toList())
				);
		
		return "admin/index/item/list";
	}

	//상품 등록 페이지: DB에 저장합니다.
	@Override
	public String itemSave(ItemInsertDTO dto) {
		System.out.println(dto);
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
			
			ItemFile defItemFile= ItemFile.builder().newName(name).orgName(name)
									.url("/images/item/").size(size).isDefImg(true).build();
			
			File addFile= new File(root, add);
			addFile.renameTo(new File(target.getFile(), add));
			name= defFile.getName();
			size= defFile.length();
			ItemFile addItemFile= ItemFile.builder().newName(name).orgName(name)
									.url("/images/item/").size(size).isDefImg(false).build();
			
			itemRepository.save(entity.addCategory(Category.valueOf(dto.getCategory())).addFile(addItemFile).addFile(defItemFile));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/item";
	}

	@Override
	public String tempFileUpload(MultipartFile file) {
		String path="/images/temp/";
		ClassPathResource cpr= new ClassPathResource("static"+path);
		
		try {
			File location= cpr.getFile();
			File targetFile= new File(location, file.getOriginalFilename());
			file.transferTo(targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path+file.getOriginalFilename();
	}

	@Override
	public String indexItemList(Model model) {
		model.addAttribute("items", 
				itemRepository.findAll()//Entity로 가져오고
				.stream().map(ItemListDTO::new).collect(Collectors.toList())
				);
		
		return "/layout/admin/item";
	}
}
