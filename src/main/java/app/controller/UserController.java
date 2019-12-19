package app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.entity.User;
import app.service.SecurityService;
import app.service.UserService;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "user/register";
		}

		userService.save(user);

		securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully");

		return "user/login";
	}

	@GetMapping("/welcome")
	public String welcome(Model model) {
		return "user/welcome";
	}
	
	@GetMapping("/forgotten-password")
	public String forgottenPassword() {
		return "user/forgotten-password";
	}

}
