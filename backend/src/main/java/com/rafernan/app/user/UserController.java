package com.rafernan.app.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class UserController {

	@Autowired
	private final UserRepository userRepository;

    @GetMapping("/user")
    public User test(Principal principal) {
		return userRepository.findByUsername(principal.getName()).get();
    }
}
