package khv.healthlenge.domain.entity.file.item;

import javax.persistence.Entity;
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
public class ItemFile {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ifNo;
	
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg; //true: 대표 이미지 false: 추가 이미지
	private long size;
}
