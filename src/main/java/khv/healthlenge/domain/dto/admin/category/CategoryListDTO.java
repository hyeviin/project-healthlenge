package khv.healthlenge.domain.dto.admin.category;

import khv.healthlenge.domain.entity.challenge.category.ChCategory;
import lombok.Getter;

@Getter
public class CategoryListDTO {
	
	//카테고리 인덱스 번호
	private long caNo;
	
	//카테고리명
	private String name;
	
	//카테고리 대표 이미지
	private String url;
	private int num;
	private String fileName;
	private boolean isShow;
	
	public CategoryListDTO(ChCategory e) {
		super();
		this.caNo = e.getCaNo();
		this.name = e.getName();
		this.fileName= e.getAdFile().getName();
		this.url = e.getAdFile().getUrl();
		this.num= e.getAdFile().getNum();
		this.isShow= e.getAdFile().isShow();
	}
}
