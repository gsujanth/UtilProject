package com.sujanth.utils.AuthUtil;

import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

public class JWTAuth {
	private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
	private static final int TEN = 10;
	private static final String TOKEN_CLAIM_ISSUER = "SUJANTH";
	private static final String TOKEN_HEADER_TYP = "typ";
	private static final String TOKEN_HEADER_TYP_VALUE = "JWT";
	private static final String TOKEN_SUBJECT = "demo";
	private static final String ENCRYPT_ALG = "RSA";
	private static String ID = "21";
	
	public static String generateToken(String id) {
		id = ID;
		Claims claims = Jwts.claims();
		claims.setIssuer(TOKEN_CLAIM_ISSUER).setIssuedAt(new Date(System.currentTimeMillis())).
		setId(id);
		JwtBuilder jwtBuilder = null;
		try {
			jwtBuilder = Jwts.builder().setClaims(claims).setHeaderParam(TOKEN_HEADER_TYP, TOKEN_HEADER_TYP_VALUE);
		}catch(Exception e) {
			e.printStackTrace();
		}
		PrivateKey privateKey = KeyPairFromJKS.getPrivateKey();
		jwtBuilder.signWith(signatureAlgorithm, privateKey).compact();
		String jwtToken = jwtBuilder.compact();
		return jwtToken;
	}
	
	public static String validateToken(String jwt) {
		PublicKey publicKey = KeyPairFromJKS.getPublicKey();
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt).getBody();
			if((new Date(System.currentTimeMillis()).getTime()-claims.getIssuedAt().getTime()<= TimeUnit.MINUTES.toMillis(TEN))
					&& claims.getIssuer().equals(TOKEN_CLAIM_ISSUER)) {
				return claims.getId();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
