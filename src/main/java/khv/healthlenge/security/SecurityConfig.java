package khv.healthlenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> 
			authorize
				.antMatchers("/", "/common/**", "/index/**", "/my/**", "/request-key/**", "/member/pass-find","/payment/**").permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
			);
		//소셜로그인 실행!
		http.oauth2Login(oauth2Login -> 
			oauth2Login
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.userInfoEndpoint()
				.userService(customOAuth2UserService));//구글 로그인 링크 적용
		
		
		///로그인페이지 설정
		http.formLogin(formLogin ->
			formLogin
				.loginPage("/common/signin")//get 로그인페이지이동 url
				.usernameParameter("email")
				.passwordParameter("pass")
				.failureUrl("/login?errMsg")
				.loginProcessingUrl("/login")//form action post Security가 처리해주는 url -> UserdetailsService
				.defaultSuccessUrl("/")
				//.successHandler(successHandler)
				//.failureHandler(failureHandler)
				.permitAll()
		);
		

		http.logout(logout->logout.logoutSuccessUrl("/"));//default is "/login?logout".
		
		http.csrf();
			
		return http.build();
	}
	
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
        		"/css/**"
        		,"/js/**"
        		,"/images/**"
        		,"/favicon.ico*"
        		,"/favicon.*"
        		);
    }
	
	
}
