package khv.healthlenge.domain.entity.itemLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLikeEntityRepository extends JpaRepository<ItemLikeEntity, Long>{

}
