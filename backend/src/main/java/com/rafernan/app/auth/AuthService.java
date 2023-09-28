package com.rafernan.app.auth;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafernan.app.user.User;
import com.rafernan.app.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	public static final String COOKIE_NAME = "userid";

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
  	private final AuthenticationManager authenticationManager;

	public User signin(String username, String password) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(username, password)
		);
		return userRepository.findByUsername(username).get();
	}

	public User signUp(String username, String password) {
		Optional<User> user = userRepository.findByUsername(username);
		
        if (user.isPresent()) {
			throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
		newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
        newUser = userRepository.save(newUser);
		return newUser;
	}
}
