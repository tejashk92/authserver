package com.example.springoauth2.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class UserTokenRestTemplateConfig {
	
	
	@Bean @Scope (value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode. TARGET_CLASS) 
	OAuth2RestTemplate oAuth2RestTemplate() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Building Oauth2 Restemplate for +auth. getName ()") ; 
		ResourceOwnerPasswordResourceDetails resource=new ResourceOwnerPasswordResourceDetails();
		resource. setAccessTokenUri ("http: //localhost: 9000/oauth/token");
		resource. setClientId("clientId"); 
		resource. setClientSecret ("secret");
		resource. setGrantType ("password" );
		resource. setUsername (auth. getName () ) ;
		resource. setPassword(auth.getCredentials().toString());
		AccessTokenRequest atr = new DefaultAccessTokenRequest (); 
		return new OAuth2RestTemplate (resource, new DefaultOAuth2ClientContext(atr));
	}
	
}
