package khv.healthlenge.domain.entity.challenge;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khv.healthlenge.domain.entity.challenge.enums.Topic;
import khv.healthlenge.domain.entity.file.FileEntity;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>{
	
	List<Challenge> findAllByTopics(Topic topic);

	Collection<Challenge> findAllByTopicsAndIsShowOrderByCno(Topic topic1, boolean b);


	//select resultType은 매핑Entity로 설정 Member
}
