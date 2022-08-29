package khv.healthlenge.domain.entity.member;

import java.util.HashSet;
import java.util.Set;

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

import khv.healthlenge.domain.dto.member.MemberUpdateDTO;
import khv.healthlenge.domain.dto.member.PassChangeDTO;
import khv.healthlenge.domain.entity.BaseTimeEntity;
import khv.healthlenge.domain.entity.feed.Feed;
import khv.healthlenge.security.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Entity
public class Member extends BaseTimeEntity {
	
	//회원 번호
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long mno;
	
	//회원 이메일 (로그인시 아이디)
	@Column(nullable = false, unique = true)
	private String email;
	
	//회원 비밀번호
	@Column(nullable = false)
	private String pass;
	
	//비밀번호 수정시 사용할 메서드
	public Member changePass(PassChangeDTO dto) {
		this.pass= dto.getPass();
		return this;
	}
	
	//회원 닉네임
	@Column
	private String nickname;

	//회원 한 줄 소개
	private String info;
	//변경시 사용할 메서드
	public Member changeInfo(MemberUpdateDTO dto) {
		//만약 입력된 값이 있다면 수정합니다.
		if(!(dto.getNickName()==null)) {
			this.nickname= dto.getNickName();
		}
		if(!(dto.getInfo()==null)) {
			this.info= dto.getInfo();
		}
		//입력된 값이 없다면 원래 정보 리턴
		return this;
	}
	
	//회원 ip
	@Column(nullable = false)
	private String userIp;
	
	//회원 탈퇴여부: default:false 정상회원 true:탈퇴신청한회원
	private boolean isDeleted;
	
	public Member updateIsDeleted(boolean deleted) {
		this.isDeleted= deleted;
		return this;
	}
	
	//소셜 회원여부: ture:소셜로 가입함, false: 소셜로 가입하지 않음
	private boolean isSocial;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열로 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MemberRole> roleSet= new HashSet<>();
	
	public Member addRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	
	//작성한 피드친구들
	@Builder.Default
	@OneToMany(mappedBy = "member")
	private Set<Feed> feeds= new HashSet<>();

}
