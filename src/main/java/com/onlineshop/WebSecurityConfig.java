package com.onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/kupac/dodajUOmiljene", "/api/korpa/**", "/api/kupac/**/korpa",
						"/api/kupac/**/**", "/api/kupac/dodajUKorpu", "/api/kupac/**/omiljeni",
						"/api/kupac/omiljeni/**/**", "/api/korpa").hasRole("KUPAC")
				.antMatchers("/api/dostavljac/**").hasRole("DOSTAVLJAC")
				.antMatchers("/api/admin/**").hasRole("ADMIN")
				.antMatchers("/app/**", "/assets/**", "/api/kategorije/**",
						"/login", "/api/korisnik/**", "/h2/**", 
						"/api/artikli/**", "/").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/#!/login").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/#!/")
				.and()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/#!/")
					.permitAll();
		http.headers().frameOptions().disable(); // for allowing use of h2 console
	}

}
