package khv.healthlenge.domain.dto.item;

import khv.healthlenge.domain.entity.file.item.ItemFile;
import lombok.Getter;

@Getter
public class ItemFileDTO {
	private long ifno;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg; // true: 대표 이미지 false: 추가 이미지
	private long size;
	
	
	public ItemFileDTO(ItemFile itemFile) {
		this.ifno = itemFile.getIfNo();
		this.url = itemFile.getUrl();
		this.orgName = itemFile.getOrgName();
		this.newName = itemFile.getNewName();
		this.isDefImg= itemFile.isDefImg();
		this.size = itemFile.getSize();
	}

}
