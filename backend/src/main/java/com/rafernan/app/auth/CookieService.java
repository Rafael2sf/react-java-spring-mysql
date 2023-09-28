package com.rafernan.app.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.rafernan.app.user.User;
import com.rafernan.app.user.UserRepository;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CookieService {
	public static final String COOKIE_NAME = "userid";
	private static final String DOMAIN = "localhost";
	private static final int MAX_AGE = 86400; // 1 Day

	@Value("${custom.api.secret-key}")
    private String secretKey;

	private final UserRepository userRepository;

	public Cookie createCookie(String username) {
		String jwt = this.createJwtToken(username);
		Cookie jwtTokenCookie = new Cookie(COOKIE_NAME, jwt);
		jwtTokenCookie.setMaxAge(MAX_AGE);
		jwtTokenCookie.setSecure(true);
		jwtTokenCookie.setHttpOnly(true);
		jwtTokenCookie.setPath("/");
		jwtTokenCookie.setDomain(DOMAIN);
		return jwtTokenCookie;
	}

    private String createJwtToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + MAX_AGE * 10000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public User validateCookie(Cookie cookie) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(cookie.getValue());
		return userRepository.findByUsername(decoded.getSubject()).orElseThrow(
			() -> new UsernameNotFoundException("User not found")
		);
    }

	public Cookie deleteCookie() {
		Cookie cookie = new Cookie(COOKIE_NAME, null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setDomain(DOMAIN);
		return cookie;
	}
}
