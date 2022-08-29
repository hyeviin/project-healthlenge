package khv.healthlenge.domain.dto.admin.category;

import khv.healthlenge.domain.dto.FileData;
import khv.healthlenge.domain.entity.challenge.category.ChCategory;
import khv.healthlenge.domain.entity.file.admin.AdFile;
import khv.healthlenge.domain.entity.file.admin.AdFileCategory;
import lombok.Setter;

@Setter
public class CategoryInsertDTO extends FileData{
	private String caName;
	private int num;
	
	public ChCategory toEntity() {
		return ChCategory.builder()
				.name(caName)
				.adFile(AdFile.builder()
								.title(name).url(url)
								.name(name).size(size)
								.num(num).category(AdFileCategory.CATEGORY).build())
				.build();
	}
	
	public void addFileData(FileData fileData, int num) {
		url = fileData.getUrl();
		name = fileData.getName();
		size = fileData.getSize();
		this.num= num;
	}
}
