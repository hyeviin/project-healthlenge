package khv.healthlenge.domain.entity.order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khv.healthlenge.domain.entity.member.Member;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{


	List<Orders> findAllByMember(Member member);

	Optional<Orders> findByPoint(long pno);

}
