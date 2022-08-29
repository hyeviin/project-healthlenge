package khv.healthlenge.domain.entity.itemLikes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khv.healthlenge.domain.entity.items.Item;
import khv.healthlenge.domain.entity.member.Member;

@Repository
public interface ItemLikeEntityRepository extends JpaRepository<ItemLikeEntity, Long>{

	Optional<ItemLikeEntity> findByMemberAndItems(Member member, Item item);

	List<ItemLikeEntity> findAllByMember(Member member);

}
