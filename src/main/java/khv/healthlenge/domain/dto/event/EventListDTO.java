package khv.healthlenge.domain.dto.event;

import khv.healthlenge.domain.entity.file.FileEntity;
import lombok.Getter;

@Getter
public class EventListDTO {
	private long vno;
	private String url;
	private String orgName;
	private long size;
	private String title;
	private String sub;
	private int num;
	private boolean isShow;
	
	public EventListDTO(FileEntity e) {
		super();
		this.vno = e.getVno();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.size = e.getSize();
		this.title = e.getTitle();
		this.sub = e.getSub();
		this.num= e.getNum();
		this.isShow= e.isShow();
	}
}
