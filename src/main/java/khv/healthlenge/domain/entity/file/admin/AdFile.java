package khv.healthlenge.domain.entity.file.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class AdFile {
	
	//이미지 파일 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long adFno;
	
	//이미지 파일 카테고리
	//DB에 저장시 문자열로 저장할때 적용
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AdFileCategory category;

	//이미지 파일 주소
	@Column(nullable = false)
	private String url;
	
	//이미지 파일 이름
	@Column(nullable = false)
	private String name;
	
	//이미지 파일 크기
	@Column(nullable = false)
	private long size;
	
	//이미지 파일 제목
	@Column(nullable = false)
	private String title;
	
	//이미지 파일 배너에 보여질 순서
	private int num;
	
	//이미지 파일을 index에 보여주기 여부
	private boolean isShow;
	public AdFile updateIsShow(boolean isShow) {
		this.isShow= isShow;
		return this;
	}
}
