package khv.healthlenge.domain.entity.file.feed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import khv.healthlenge.domain.entity.feed.Feed;
import lombok.Getter;

@Getter
@Entity
public class FeFile {
	
	//이미지 파일 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long feFno;
	
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

	//1:N 관계= 양방향으로 설정합니다.
	@ManyToOne
	@JoinColumn(name = "feNo")
	private Feed feed;
}
