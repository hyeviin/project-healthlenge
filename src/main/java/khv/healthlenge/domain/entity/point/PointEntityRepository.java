package khv.healthlenge.domain.entity.point;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khv.healthlenge.domain.entity.order.Orders;

@Repository
public interface PointEntityRepository extends JpaRepository<PointEntity, Long>{

}
