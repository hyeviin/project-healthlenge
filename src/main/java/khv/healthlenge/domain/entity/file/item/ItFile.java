package khv.healthlenge.domain.entity.file.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import khv.healthlenge.domain.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class ItFile {
	//이미지 파일 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long itFno;
	
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
	
	//true: 대표 이미지 false: 추가 이미지
	private boolean isDefImg; 
}
