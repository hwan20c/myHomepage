package com.bh.tb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  /**
	 * description : 유저 인증정보를 설정 할 수 있다. jdbc 인증정보 연결  
	 */
	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	return new UserDetailServiceImpl();
	// }
	
	/**
	 * description : static file 같은 인증이 필요없는 리소스를 이곳에서 설정한다.
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
	}
	
	/**
	 * description : 리소스 보안 부분
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/files/**").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/myBoard/create").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/myBoard/{id}").hasAnyRole("ADMIN")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("loginId") 
			.passwordParameter("password")
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/")
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
