package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	 private final String SECRET_KEY = "042a80a3988dd798a5ffb8f1b28e64145195986059c834810e23201b9468bf84";

	    private Key getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	    }

	    // Extract username from token
	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    //  Extract role from token
	    public String extractRole(String token) {
	        return extractClaim(token, claims -> claims.get("role", String.class));
	    }

	    //  Extract any claim
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    // Extract all claims from the JWT token
	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    //  Check if token is expired
	    private boolean isTokenExpired(String token) {
	        return extractAllClaims(token).getExpiration().before(new Date(1));
	    }

	    //  Generate token with ROLE_ prefix
	    public String generateToken(String username, String role) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("role", "ROLE_" + role); // Spring Security expects ROLE_ prefix
	        return createToken(claims, username);
	    }

	    // Create the token
	    private String createToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    // Validate token
	    public boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
	}