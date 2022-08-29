package khv.healthlenge;

import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import khv.healthlenge.domain.dto.item.ItemInsertDTO;
import khv.healthlenge.domain.entity.challenge.ChallengeRepository;
import khv.healthlenge.domain.entity.faq.FaqEntityRepository;
import khv.healthlenge.domain.entity.file.FileEntity;
import khv.healthlenge.domain.entity.items.Category;
import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.items.ItemRepository;
import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import khv.healthlenge.security.MemberRole;

@SpringBootTest
class HealthlengeApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ChallengeRepository boardRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	FaqEntityRepository faqEntityRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Test
	void 관리자생성() {
		memberRepository.save(Member.builder()
				.email("test@test.com").pass(pe.encode("aaaa")).name("test1").userIp("127.0.0.1")
				.build().addRole(MemberRole.USER));
	}
	
	//@Test
	void 상품등록테스트() {
		
		ItemInsertDTO dto= new ItemInsertDTO();
		dto.setName("얼라이브");
		dto.setContent("세계 판매 1위 종합비타민!test3");
		dto.setPrice(25000);
		dto.setStock(25);
		
		List<FileEntity> imgs= new Vector<FileEntity>();
		
		imgs.add(FileEntity.builder().orgName("run.jpg")
				.size(503237)
				.url("/images/boards/")
				.sub("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹㄴㅁㅇㄹ")
				.title("test1").build());
		imgs.add(FileEntity.builder().orgName("run.jpg")
				.size(503237)
				.url("/images/boards/")
				.sub("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹㄴㅁㅇㄹ")
				.title("test2").build());
		imgs.add(FileEntity.builder().orgName("run.jpg")
				.size(503237)
				.url("/images/boards/")
				.sub("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹㄴㅁㅇㄹ")
				.title("test2").build());
		
		Item item= Item.builder()
					.name("얼라이브").content("세계 판매 1위 종합비타민!test3")
					.price(35000).stock(500)
					.build();
		
		itemRepository.save(item.addCategory(Category.VITAMIN));
	}

}
