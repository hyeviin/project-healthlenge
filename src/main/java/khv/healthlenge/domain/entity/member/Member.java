package khv.healthlenge.domain.entity.member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.domain.entity.challenge.Challenge;
import khv.healthlenge.domain.entity.notice.Notice;
import khv.healthlenge.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@DynamicInsert//insert시 Entity에 null데이터는 퀄리에 적용시키지 않아요
@DynamicUpdate//실제로 update처리하는 컬럼만 쿼리에 적용된다.

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Member extends BaseTimeEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long mno;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String pass;
	
	public Member changePass(PassChangeDTO dto) {
		this.pass= dto.getPass();
		return this;
	}
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String userIp;
	
	private boolean isDeleted;//default:false 탈퇴여부 //true:탈퇴신청한회원
	public Member updateIsDeleted(boolean deleted) {
		this.isDeleted= deleted;
		return this;
	}
	
	private boolean isSocial;//ture:소셜로 가입함, false: 소셜로 가입하지 않음
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열로 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MemberRole> roleSet=new HashSet<>();
	
	public Member addRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	public Member removeRole(MemberRole role) {
		roleSet.remove(role);
		return this;
	}
	
	//게시글과 연관관계 설정합니다.
	@Builder.Default
	@OneToMany(mappedBy = "member")
	private List<Challenge> challengeList= new Vector<Challenge>();
	
	//공지사항과 연관관계 설정합니다.
	@OneToMany(mappedBy = "member")
	private List<Notice> noticeList= new Vector<Notice>();
	
}
