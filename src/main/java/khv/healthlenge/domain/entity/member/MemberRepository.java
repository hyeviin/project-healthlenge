package khv.healthlenge.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	//select resultType은 매핑Entity로 설정 Member
	
	Optional<Member> findByEmail(String username);// 회원가입시점에서 이메일중복체크시에 사용
	
	Optional<Member> findByEmailAndIsDeleted(String username, boolean isDeleted);
	
	Optional<Member> findByEmailAndIsDeletedAndIsSocial(String username, boolean isDeleted, boolean isSocial);
}
