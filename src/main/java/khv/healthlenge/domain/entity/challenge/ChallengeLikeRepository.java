package khv.healthlenge.domain.entity.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khv.healthlenge.domain.entity.member.Member;

@Repository
public interface ChallengeLikeRepository extends JpaRepository<ChallengeLikeEntity, Long>{

	Optional<ChallengeLikeEntity> findByMemberAndChallenge(Member member, Challenge challenge);

	List<ChallengeLikeEntity> findAllByMember(Member member);

}
