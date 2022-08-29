package khv.healthlenge.domain.entity.file.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFileRepository extends JpaRepository<ItemFile, Long> {

}
