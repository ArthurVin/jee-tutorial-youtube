package com.example.j2eeapp.services.impl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserAuthenticationProviderService;

/**
 * Provides processing service to set user authentication session
 * 
 * @author Arthur Vin
 */
public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {

	private AuthenticationManager authenticationManager;
	
	/**
	 * Process user authentication
	 * 
	 * @param user
	 * @return
	 */
	public boolean processUserAuthentication(UserEntity user) {
		
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			
			return true;
		} catch(AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Sorry!"));
			
			return false;
		}
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
