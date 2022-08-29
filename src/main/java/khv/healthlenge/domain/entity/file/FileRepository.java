package khv.healthlenge.domain.entity.file;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

	List<FileEntity> findAllByCategorysAndIsShowOrderByNum(FileCategory category, boolean isShow);

	Collection<FileEntity> findAllByCategorysOrderByNum(FileCategory category);

}
