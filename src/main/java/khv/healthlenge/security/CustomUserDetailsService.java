package khv.healthlenge.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import khv.healthlenge.domain.entity.member.Member;
import khv.healthlenge.domain.entity.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	//DaoAuthenticationProvider. 
	//DAO : jpa-Repository
	private final MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username -> login페이지에서 입력한 email 정보입니다.

		//db에 회원존재하는지 체크 존재하면 UserDetails 으로 세팅해서 리턴
		Optional<Member> result= repository.findByEmailAndIsDeletedAndIsSocial(username, false, false);
		//isDeleted : true는 탈퇴회원, false가 정상회원
		
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않거나 탈퇴회원");
		}
		
		//UserDetails 타입으로 리턴 User활용		
		return new CustomUserDetails(result.get());
	}

}
