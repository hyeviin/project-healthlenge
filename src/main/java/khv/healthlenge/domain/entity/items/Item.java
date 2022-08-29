package khv.healthlenge.domain.entity.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import khv.healthlenge.domain.entity.file.item.ItemFile;
import khv.healthlenge.domain.entity.order.Orders;
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
	@JoinColumn(name = "ino")//fk이름 설정가능함?? 설정하지 않으면 자동으로 설정됨.
	//상품쪽에서만 접근가능한 단방향입니다.
	@OneToMany(cascade = CascadeType.ALL)
	List<ItemFile> files= new ArrayList<>();
	
	public Item addFile(ItemFile itemFile) {
		files.add(itemFile);
		return this;
	}
	
}
