/**
 * 
 */
package com.java.techhub.security.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.java.techhub.security.jwt.filters.JwtTokenInterceptor;

/**
 * @author mahes
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.ant.matchers}")
	private String[] securityAntMatchers;

	@Autowired
	private JdbcUserDetailsService jdbcUserDetailsService;

	@Autowired
	private JwtTokenInterceptor jwtTokenInterceptor;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jdbcUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests().antMatchers(securityAntMatchers).permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.httpBasic();
		httpSecurity.addFilterBefore(jwtTokenInterceptor, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.csrf().ignoringAntMatchers(securityAntMatchers).disable();
		httpSecurity.headers().frameOptions().sameOrigin();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
