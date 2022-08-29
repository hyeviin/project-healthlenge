package khv.healthlenge.domain.dto.admin.images;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.file.admin.AdFile;
import khv.healthlenge.domain.entity.file.admin.AdFileCategory;
import lombok.Setter;

@Setter
public class ImagesInsertDTO extends FileData {
	
	private String title;
	private int num;
	
	public AdFile toEntity() {
		return AdFile.builder()
				.title(title).url(url)
				.name(name).size(size)
				.num(num).category(AdFileCategory.EVENT)
				.build();
	}
	
	public void addFileData(FileData fileData, int num) {
		url = fileData.getUrl();
		name = fileData.getName();
		size = fileData.getSize();
		this.num= num;
	}
}
