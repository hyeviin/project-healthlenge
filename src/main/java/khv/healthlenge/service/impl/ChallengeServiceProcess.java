package khv.healthlenge.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import khv.healthlenge.domain.dto.Challenge.ChallengeDetailDTO;
import khv.healthlenge.domain.dto.Challenge.ChallengeListDTO;
import khv.healthlenge.domain.dto.event.EventListDTO;
import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.challenge.ChallengeLikeEntity;
import khv.healthlenge.domain.entity.challenge.ChallengeLikeRepository;
import khv.healthlenge.domain.entity.challenge.ChallengeRepository;
import khv.healthlenge.domain.entity.challenge.enums.Topic;
import khv.healthlenge.domain.entity.file.FileCategory;
import khv.healthlenge.domain.entity.file.FileRepository;
import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntity;
import khv.healthlenge.domain.entity.itemLikes.ItemLikeEntityRepository;
import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.items.ItemRepository;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.service.ChallengeService;

@Service
public class ChallengeServiceProcess implements ChallengeService{
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private FileRepository visualFileRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ChallengeLikeRepository challengeLikeRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemLikeEntityRepository itemLikeEntityRepository;
	
	//챌린지 페이지에서 선택된 카테고리마다 다른 챌린지 보여주기
	@Override
	public ModelAndView boardIndexList(ModelAndView mv, int divNo, String email) {
		
		if(divNo == 11) {
			Member member= memberRepository.findByEmail(email).get();
			
			
			mv.addObject("challengelist", challengeLikeRepository.findAllByMember(member).stream()
					.map(ChallengeListDTO::new).collect(Collectors.toList()));
		}else {
			
			Topic topics[]= Topic.values();
			Topic topic= topics[divNo];
			
			System.out.println(topic);
//		faqEntityRepository.findAllByDiv(div).forEach(System.out::println);
//		List<FaqListDTO> result= faqEntityRepository.findAllByDiv(div).stream()
//				.map(FaqListDTO::new).collect(Collectors.toList());
			
			mv.addObject("challengelist", challengeRepository.findAllByTopics(topic).stream()
					.map(ChallengeListDTO::new).collect(Collectors.toList()));
		}		
		mv.setViewName("layout/challenge/list");			
		return mv;
	}

	@Override
	public String boardIndexCategory(Model model) {
		FileCategory category= FileCategory.CATEGORY;
		
		model.addAttribute("categorys", 
				visualFileRepository.findAllByCategorysOrderByNum(category)//Entity로 가져오고
				.stream().map(EventListDTO::new).collect(Collectors.toList())
				);
		
		model.addAttribute("challengelist", challengeRepository.findAll().stream()
					.map(ChallengeListDTO::new).collect(Collectors.toList()));
		return "/challenge/index";
	}
	
	//자세히
	@Override
	public String detail(Model model, long cno) {
		model.addAttribute("boardDetail", 
				challengeRepository.findById(cno).map(ChallengeDetailDTO::new).get()
				);
		
		return "/challenge/detail";
	}
	
	//DB 저장시 true, 삭제시 false!
	@Override
	public Boolean like(String email, long no, String topic) {
		Member member= memberRepository.findByEmail(email).get();
		
		if(topic.equals("challenge")) {
			Challenge challenge= challengeRepository.findById(no).get();		
			
			Optional<ChallengeLikeEntity> result= challengeLikeRepository.findByMemberAndChallenge(member, challenge);
			
			if(result.isEmpty()) {
				challengeLikeRepository.save(ChallengeLikeEntity.builder()
						.member(member).challenge(challenge).build());
				return true;
			}
			
			challengeLikeRepository.deleteById(result.get().getClNo());
			
			return false;
		}
		
		//아이템일때
		Item item= itemRepository.findById(no).get();
		
		Optional<ItemLikeEntity> result= itemLikeEntityRepository.findByMemberAndItems(member, item);
		
		if(result.isEmpty()) {
			itemLikeEntityRepository.save(ItemLikeEntity.builder()
					.member(member).items(item).build());
			return true;
		}
		
		itemLikeEntityRepository.deleteById(result.get().getIlNo());
		
		return false;
	}

}
