package com.librarymanagementsystem.librarymanagementsystem.configuration;

import com.librarymanagementsystem.librarymanagementsystem.serviceImp.CustomSuccessHandler;
import com.librarymanagementsystem.librarymanagementsystem.serviceImp.CustomUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailsServices customUserDetailsServices;


	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

////	@SuppressWarnings("removal")
////	@Bean
////	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
////
////		http.csrf().disable().authorizeHttpRequests()
////		.requestMatchers("/signup").permitAll()
////		.requestMatchers("/home","/").permitAll()
////				//.requestMatchers("/").permitAll()
////				.and()
////		.formLogin()
////		.loginPage("/login")
////		.loginProcessingUrl("/login")
////		.defaultSuccessUrl("/home", true).permitAll()
////		.and()
////		.logout()
////		.invalidateHttpSession(true)
////	     .clearAuthentication(true)
////	     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////	     .logoutSuccessUrl("/login?logout").permitAll();
////
////		return http.build();
////
////}
//
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http.csrf(c -> c.disable())

				.authorizeHttpRequests(request -> request.requestMatchers("/admin-page")
						.hasAuthority("ADMIN").requestMatchers("/user-page").hasAuthority("USER")
						.requestMatchers("/signup", "/css/**","/viewCart/**").permitAll()
						.requestMatchers("/","/viewBooks","/bookRegister","/about","/contact","/image/**").permitAll()
						.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						.successHandler(customSuccessHandler).permitAll())

				.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll());

		return http.build();

	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsServices).passwordEncoder(passwordEncoder());
	}

}