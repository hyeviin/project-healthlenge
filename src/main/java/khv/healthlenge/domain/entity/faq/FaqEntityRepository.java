package khv.healthlenge.domain.entity.faq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqEntityRepository extends JpaRepository<FaqEntity, Long>{

	List<FaqEntity> findAllByDiv(Div div);

	

}
