package khv.healthlenge.domain.entity.items;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long>{

	Collection<Item> findAllByCategorys(Category category);
}
