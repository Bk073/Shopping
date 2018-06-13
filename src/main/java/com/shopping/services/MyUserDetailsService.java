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
	public UserDetails loadUserByUsername( String username) 
		throws UsernameNotFoundException {
	
		com.shopping.entity.User user = userDao.findByUserName(username);
		if(user == null){
			logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                true, true, true, true, buildUserAuthority((Set<UserRole>) user));
		/*List<GrantedAuthority> authorities = 
                                      buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);*/
		
	}

	// Converts com.shopping.users.entity.User user to
	// org.springframework.security.core.userdetails.User
	/*private User buildUserForAuthentication(com.shopping.entity.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
			user.isEnabled(), true, true, true, authorities);
	}*/

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
}