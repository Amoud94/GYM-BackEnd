package com.org.spring.gym.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/coach")
	@PreAuthorize("hasRole('COACH')")
	public String userAccess() {
		return "Coach Content.";
	}

	@GetMapping("/employee")
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
	public String moderatorAccess() {
		return "Employee Board.";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER')")
	public String adminAccess() {
		return "Eanager Board.";
	}
}
