package khv.healthlenge.domain.entity.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//login
	Optional<Member> findByEmailAndIsDeletedAndIsSocial(String username, boolean b, boolean c);
	
	//index -> nickname! 
	Optional<Member> findByEmail(String userEmail);

}
