package khv.healthlenge.domain.entity.file.admin;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdFileRepository extends JpaRepository<AdFile, Long>{

	List<AdFile> findAllByCategoryAndIsShowOrderByNum(AdFileCategory category, boolean b);

	Collection<AdFile> findAllByCategoryOrderByNum(AdFileCategory category);

}
