package khv.healthlenge.domain.entity.item;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import khv.healthlenge.domain.entity.file.item.ItFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Item {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ino;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private int sale;
	
	private int stock;
	@Column(nullable = false)
	private String content;
	
	private boolean isRate;
	
	@Builder.Default
	@ElementCollection
	@Enumerated(EnumType.STRING)
	Set<Category> categorys= new HashSet<>();
	
	public Item addCategory(Category category) {
		categorys.add(category);
		return this;
	}
	
	@Builder.Default
	@JoinColumn(name = "ino")
	@OneToMany(cascade = CascadeType.ALL)
	private Set<ItFile> itFiles= new HashSet<>();
	
	public Item addFile(ItFile itFile) {
		itFiles.add(itFile);
		return this;
	}
	
}
