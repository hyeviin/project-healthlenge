package khv.healthlenge.domain.dto.event;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.file.FileEntity;
import lombok.Setter;

@Setter
public class EventInsertDTO extends FileData{
	
	private String title;
	private String sub;
	private int num;

	public FileEntity toVisualFile() {
		
		return FileEntity.builder()
				.title(title).sub(sub)
				.url(url).orgName(orgName).size(size)
				.num(num)
				.build();
	}
	
	public void addFileData(FileData fileData, int num) {
		url = fileData.getUrl();
		orgName = fileData.getOrgName();
		size = fileData.getSize();
		this.num= num;
	}
	
}
