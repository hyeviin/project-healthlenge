package khv.healthlenge.domain.entity.file;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.items.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter	
@Entity
public class FileEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long vno;
	
	private String url;
	
	private String  orgName;
	
	private long size;

	private String title;
	
	private String sub;
	
	private int num;
	
	private boolean isShow;
	
	//배너에 보여줘야한다면 보여줍니다.
	public FileEntity updateIsShow(boolean isShow) {
		this.isShow= isShow;
		return this;
	}
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열로 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<FileCategory> categorys= new HashSet<>();
	
	public FileEntity addCategory(FileCategory category) {
		categorys.add(category);
		return this;
	}
	public FileEntity removeCategory(FileCategory category) {
		categorys.remove(category);
		return this;
	}
	
	//게시글과 연관설정
	@OneToOne(mappedBy = "file")
	private Challenge challenge;
	
	//상품과 연관설정
	@ManyToOne(cascade = CascadeType.ALL)
	private Item item;
	
	public FileEntity setItem(Item item) {
		this.item= item;
		return this;
	}
}
