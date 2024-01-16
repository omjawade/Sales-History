package com.saleshistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.saleshistory.config.jwt.AuthEntryPointJwt;
import com.saleshistory.config.jwt.AuthTokenFilter;
import com.saleshistory.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity

public class WebSecurityConfig {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	 /**
     * Create and configure the authentication token filter bean.
     *
     * @return the authentication token filter bean
     */
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	/**
     * Create and configure the DAO authentication provider bean.
     *
     * @return the DAO authentication provider bean
     */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	/**
     * Create and configure the authentication manager bean.
     *
     * @param authConfig the authentication configuration
     * @return the authentication manager bean
     * @throws Exception if an error occurs while creating the authentication manager
     */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	/**
     * Create and configure the security filter chain.
     *
     * @param http the HttpSecurity object to configure
     * @return the security filter chain
     * @throws Exception if an error occurs while configuring the security filter chain
     */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.cors().and().csrf().disable().authorizeHttpRequests()
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/auth/signup", "/auth/signin").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/test/**").permitAll().anyRequest().permitAll();
//				.and()
//				.formLogin();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
