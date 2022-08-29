package khv.healthlenge.domain.entity.challenge.category;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import khv.healthlenge.domain.entity.file.admin.AdFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
//챌린지 
public class ChCategory {
	
	//카테고리 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long caNo;
	
	//카테고리 제목
	@Column(nullable = false)
	private String name;

	//1:1 관계= 단방향으로 설정합니다. 여기서 관리합니다.
	//챌린지 카테고리 대표 이미지
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adFno", nullable = false)
    private AdFile adFile;
	
	public ChCategory updateIsShow(boolean isShow) {
		adFile.updateIsShow(isShow);
		return this;
	}
	
}
