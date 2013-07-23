package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.j2eeapp.dao.UserDao;
import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserService;

/**
 * Service providing service methods to work with user data and entity.
 * 
 * @author Arthur Vin
 */
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserDao userDao;
	
	/**
	 * Create user - persist to database
	 * 
	 * @param userEntity
	 * @return true if success
	 */
	public boolean createUser(UserEntity userEntity) {
		userDao.save(userEntity);
		return true;
	}
	
	/**
	 * Construct UserDetails instance required by spring security
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserEntity user = userDao.loadUserByUserName(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No such user with name provided '%s'", userName));
		}
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		User userDetails = new User(user.getUserName(), user.getPassword(), authorities);
		
		return userDetails;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
