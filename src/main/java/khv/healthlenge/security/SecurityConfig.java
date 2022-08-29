package khv.healthlenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//권한에 따른 보여줄 페이지 설정
		http
			.authorizeHttpRequests(authorize -> 
			authorize
					.antMatchers("/", "/common/**", "/request-key/**").permitAll()
					.antMatchers("/admin/**", "/favicon.*").hasAnyRole("ADMIN")
					.anyRequest().authenticated()
			);
		
		//로그인 페이지 설정
		http.formLogin(formLogin ->
				formLogin
					.loginPage("/common/signin")
					.usernameParameter("email")
					.passwordParameter("pass")
					.failureUrl("/common/signin?errMsg")
					.loginProcessingUrl("/common/login")
					.defaultSuccessUrl("/")
					.permitAll()
			);
		
		http.csrf();
		
		return http.build();
	}
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
        		"/css/**"
        		,"/js/**"
        		,"/images/**"
        		,"/favicon.*");
    }
}
