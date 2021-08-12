package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrp.pma.dao.UserAccountRepository;
import com.jrp.pma.entities.UserAccount;

@Controller
public class SecurityController {

	@Autowired
	UserAccountRepository accountRepo;

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);

		return "security/register";
	}

	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		//NB. Encrypt password from form input before saving to DB.
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		//Use repo to save to DB but change it to a service later like project and employee
		accountRepo.save(user);

		return "redirect:/";

	}

}
