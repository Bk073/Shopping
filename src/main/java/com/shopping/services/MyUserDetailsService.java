package com.shopping.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.UserDao;
import com.shopping.entity.UserRole;
@Transactional
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	//get user from the database, via Hibernate
	 static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	private UserDao userDao;

	 @Transactional(readOnly=true)
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {
	        com.shopping.entity.User user = userDao.findByUserName(username);
	        logger.info("User : {}", user);
	        if(user==null){
	            logger.info("User not found");
	            throw new UsernameNotFoundException("Username not found");
	        }
	            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
	                 true, true, true, true, getGrantedAuthorities((Set<UserRole>) user.getUserRole()));
	    }
	 
	     
	    private List<GrantedAuthority> getGrantedAuthorities(Set<UserRole> userRoles){
	    	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

			// Build user's authorities
			for (UserRole userRole : userRoles) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
			}

			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

			return Result;
	       
	    }
}