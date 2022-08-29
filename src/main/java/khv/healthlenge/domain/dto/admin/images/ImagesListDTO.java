package khv.healthlenge.domain.dto.admin.images;

import khv.healthlenge.domain.entity.file.admin.AdFile;
import lombok.Data;

@Data
public class ImagesListDTO {
	private long adFno;
	private String url;
	private String name;
	private String title;
	private int num;
	private boolean isShow;
	
	public ImagesListDTO(AdFile e) {
		super();
		this.adFno = e.getAdFno();
		this.url = e.getUrl();
		this.name = e.getName();
		this.title = e.getTitle();
		this.num= e.getNum();
		this.isShow= e.isShow();
	}
}
