package khv.healthlenge.domain.dto.feed.file;

import khv.healthlenge.domain.entity.file.feed.FeFile;
import lombok.Getter;

@Getter
public class FeFileListDTO {
	//pk
	private long feFno;
	//url
	private String url;
	
	
	public FeFileListDTO(FeFile e) {
		this.feFno= e.getFeFno();
		this.url= e.getUrl() + e.getName();
	}
}
