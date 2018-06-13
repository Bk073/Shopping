package com.shopping.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	 @Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	        auth.authenticationProvider(authenticationProvider());
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.httpBasic().authenticationEntryPoint(getBasicAuthenticationEntryPoint());*/
	    http.authorizeRequests().antMatchers("/admin*/**")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/cart*/**").access("hasRole('ROLE_USER')")
		.and().formLogin()
		.loginPage("/login").failureUrl("/login?error").permitAll()
		.loginProcessingUrl("/login").usernameParameter("username")
		.passwordParameter("password")	
		.and().logout().logoutSuccessUrl("/login?logout")
		.and().csrf().disable();
		/*.and().exceptionHandling().accessDeniedPage("/403");*/
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

	
	
	
	
}