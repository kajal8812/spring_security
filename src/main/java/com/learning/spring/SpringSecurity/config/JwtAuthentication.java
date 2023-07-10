package com.learning.spring.SpringSecurity.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

//@Configuration
public class JwtAuthentication {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//this is for basic authentication cant has option for logout 
		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated());
		//http.formLogin();
		http.httpBasic();
		http.csrf().disable();

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		//	to congigure username and password
		UserDetails userDetails = User.builder().username("Rishi").password(passwordEncoder().encode("mishra"))
				.roles("NORMAL").build();
		//UserDetails userDetails1 = User.builder().username("shashi").password(passwordEncoder().encode("kumar"))
		//.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//to encode passowrd
		return new BCryptPasswordEncoder();
	}

	@Bean
	public KeyPair keyPair() {

		try {
			var k = KeyPairGenerator.getInstance("RSA");
			k.initialize(2048);
			return k.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {

		return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey((RSAPrivateKey) keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString()).build();

	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		var jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, context) -> jwkSelector.select(jwkSet);
	}

	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();

	}

	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);

	}
}
