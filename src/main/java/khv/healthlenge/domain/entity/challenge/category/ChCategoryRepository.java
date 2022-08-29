package khv.healthlenge.domain.entity.challenge.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChCategoryRepository extends JpaRepository<ChCategory, Long>{

	//Collection<ChCategory> findAllOrderByAdFileNum();

}
