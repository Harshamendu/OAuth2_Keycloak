package com.harsha.resource.security;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAccessTokenCustomizer extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

	
	private ObjectMapper mapper;
	
	
	public JWTAccessTokenCustomizer(ObjectMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public void configure(JwtAccessTokenConverter converter) {
		converter.setAccessTokenConverter(this);
	}

	
	public OAuth2Authentication extractAuthenication(Map<String,?> tokenMap) {
		JsonNode token = mapper.convertValue(tokenMap, JsonNode.class);
		Set<String> audienceList = extractClients(token);
		List<GrantedAuthority> authorities = extractRoles(token);
		
		 OAuth2Authentication authentication = super.extractAuthentication(tokenMap);
		 OAuth2Request oAuth2Request = authentication.getOAuth2Request();
		 
		 OAuth2Request request = new OAuth2Request(oAuth2Request.getRequestParameters(),oAuth2Request.getClientId()
				 		,authorities,true,oAuth2Request.getScope(),audienceList,null,null,null);
		 
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), "N/A" ,authorities);
		
		return new OAuth2Authentication(request, usernamePasswordAuthenticationToken);
		
	}



	private List<GrantedAuthority> extractRoles(JsonNode token) {
		
		Set<String> rolesWithPrefix = new HashSet<>();
		
		token.path("resource_access").elements().forEachRemaining
						(s -> s.path("roles").elements().forEachRemaining(r -> rolesWithPrefix.add(r.asText())));
		 
		
		List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(rolesWithPrefix.toArray(new String[0]));
		
		return authorityList;
	}



	private Set<String> extractClients(JsonNode token) {
		Set<String> clientNames = new HashSet<>();
		
		if(token.has("resource_access")) {
			
			JsonNode resouceAccessJsonNode = token.findPath("resource_access");
			
			JsonNode azpJsonNode = token.path("azp");
			JsonNode audJsonNode = token.path("aud");
			
			clientNames.add(azpJsonNode.asText());
			audJsonNode.fieldNames().forEachRemaining(clientNames::add);
			resouceAccessJsonNode.fieldNames().forEachRemaining(clientNames::add);
			
		}
		
		return clientNames;
	}
	
}
