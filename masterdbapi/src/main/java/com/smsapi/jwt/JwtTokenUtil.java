package com.smsapi.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;


@Component
public class JwtTokenUtil implements Serializable {
	
	@Value("${security.key}")
	private  String secretKey;

    public String getUsernameFromToken(String token) {
    	System.out.println("nside getUsernameFromToken"+ token);
    	String userName = getClaimFromToken(token, Claims::getSubject);
        
        System.out.println("nside getUsernameFromToken"+ userName);
        return userName;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String username) {
    	System.out.println("Generate Token");
        return doGenerateToken(username);
    }

    private String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        System.out.println("Doooo Generate Token +Claims put");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://amizhth.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000*1000)) // 1.6 hrs
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	System.out.println("Entered validateToken method");
        final String username = getUsernameFromToken(token);
        System.out.println("Entered validateToken method  username:" + username );
        

        System.out.println("Entered validateToken method  userDetails.getUsername():" + userDetails.getUsername() );
        boolean bool = username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
        
        System.out.println("Entered validateToken method  username:" + username +" bool : " +bool);

        return bool;
    }

}