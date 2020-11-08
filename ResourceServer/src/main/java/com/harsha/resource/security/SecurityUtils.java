package com.harsha.resource.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

	public static String getUserName() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = "anonymous";
		
		if(authentication != null) {
			if(authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName =  springSecurityUser.getUsername();
			}else if(authentication.getPrincipal() instanceof String){
				userName =  (String) authentication.getPrincipal();
			}
		}
		return userName;
	}

	public static Set<String> getUserRoles() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		
		Set<String> roles = new HashSet<>();
		
		if(authentication != null) {
			authentication.getAuthorities().forEach(e -> roles.add(e.getAuthority()));
		}
		
		return roles;
	}
	
//	public static String getToken() {
//		return oAuthUserDetails.getTokenValue();
//	}

	public static OAuth2AuthenticationDetails oAuthUserDetails() {
		
		OAuth2AuthenticationDetails details = null;
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getDetails() != null) {
			details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		}
		return details;
	}

}
