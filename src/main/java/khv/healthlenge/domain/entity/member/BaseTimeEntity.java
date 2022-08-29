package khv.healthlenge.domain.entity.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.style.ValueStyler;

import lombok.Getter;

//테이블과 매핑의 목적이 아니고 자식클래스의 상속을 하기위해 사용
//엔티티는 아니기에 findByCreatedDate
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedDate;

}
