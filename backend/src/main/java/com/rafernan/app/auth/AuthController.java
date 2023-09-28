package com.rafernan.app.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafernan.app.auth.dto.UserCredentialsDto;
import com.rafernan.app.user.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	private final CookieService cookieService;

    @PostMapping("/signin")
    public void signin(@Valid @RequestBody UserCredentialsDto credentials, HttpServletResponse res) {
		User user = authService.signin(credentials.getUsername(), credentials.getPassword());
		res.addCookie(cookieService.createCookie(user.getUsername()));
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody UserCredentialsDto credentials, HttpServletResponse res) {
		User user = authService.signUp(credentials.getUsername(), credentials.getPassword());
		res.addCookie(cookieService.createCookie(user.getUsername()));
		res.setStatus(HttpStatus.CREATED.value());
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse res) {
		res.addCookie(cookieService.deleteCookie());
    }
}
