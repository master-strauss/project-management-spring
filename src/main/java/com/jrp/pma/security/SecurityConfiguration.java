package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * NB: USING H2 DB for AUTHENTICATION
	 */
	@Autowired
	DataSource dataSource;

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		// IF using H2 or Oracle or ...... [Using H2 switch application.properties to "test" fro H2]
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.withDefaultSchema()    //Can use H2 / Oracle ..... for default schema
//		.withUser("myuser").password("pass").roles("USER")
//		.and()
//		.withUser("managerUser").password("pass3").roles("ADMIN");
		
		// TO CUSTOMIZE YOUR OWN DB SCHEMA FOR USER CREDENTIALS
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username, password, enabled "+
				"from user_accounts where username = ?" )
			.authoritiesByUsernameQuery("select username, role "+
				"from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder);
	}
	

	/**
	 * hasRole("ADMIN") => set role in DB 'ROLE_ADMIN'
	 * hasAuthority("ADMIN") => set role in DB 'ROLE'
	 * 
	 * In DB, "update user_accounts set role = 'ADMIN' where username = 'pp'" for hasAuthority()
	 * or "update user_accounts set role = 'ROLE_ADMIN' where username = 'pp'" for hasRole()
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// LEAST PRIORITY TOWARDS BOTTOM
		http.authorizeRequests()
//		.antMatchers("/projects/new").hasAuthority("ADMIN") //hasRole => admin1 (unsername/password)
//		.antMatchers("/projects/save").hasAuthority("ADMIN") // change hasAuthority to hasRole => "ROLE_ADMIN" 
//		.antMatchers("/employees/new").hasAuthority("ADMIN") //hasAuthority => admin2 (unsername/password)
//		.antMatchers("/employees/save").hasAuthority("ADMIN") // hasAuthority already set => "ADMIN"
////		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/", "/**").permitAll()
		.and().formLogin(); //can specify formLogin("/login-page"); if you have controller that can handle /login-page.


//		// NB.NB.NB.NB.UNCOMMENT here and h2 above FOR h2-console
//		http.csrf().disable();
//		http.headers().frameOptions().disable();

	}

}
