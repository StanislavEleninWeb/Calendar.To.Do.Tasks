package app.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.Role;
import app.entity.User;
import app.repository.RoleRepository;
import app.service.UserService;
import app.util.PassayUtil;
import app.validation.UserRegisterValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PassayUtil passayUtil;

	@Autowired
	private UserRegisterValidator userRegisterValidation;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// @Autowired
	// private SecurityService securityService;

	@GetMapping("/user")
	public String index(Model model) {

		model.addAttribute("users", userService.findAll());

		return "user/index";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {

		// Generate salt
		String salt = passayUtil.generateSalt();

		// Generate password
		String rawPassword = passayUtil.generatePassword();

		// bCript password
		String password = bCryptPasswordEncoder.encode(salt + rawPassword);

		// Generate activation code
		String activationCode = bCryptPasswordEncoder.encode(Long.toString(System.currentTimeMillis()));

		// Set User role
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.getOne(1L));

		user.setSalt(salt);
		user.setPassword(password);
		user.setPasswordConfirm(rawPassword);
		user.setActivationCode(activationCode);
		user.setRoles(roles);

		userRegisterValidation.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "user/register";
		}

		userService.register(user);

		// securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

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

	@GetMapping("/comfirm-activation-code")
	public String confirmActivationCode(@RequestParam("activationCode") String activationCode) {

		User user = userService.findByActivationCode(activationCode);

		LocalDateTime currentDateTime = LocalDateTime.now();

		if (user == null || currentDateTime.isAfter(user.getCreatedAt().plusHours(24))) {
			return "redirect:/denied-activation-code";
		}

		user.setActive(true);
		userService.save(user);

		return "redirect:/login";
	}

	@GetMapping("/denied-activation-code")
	public String deniedActivationCode() {
		return "user/denied-activation-code";
	}

	@GetMapping("/forgotten-password")
	public String forgottenPassword() {
		return "user/forgotten-password";
	}

	@PostMapping("/forgotten-password")
	public String forgottenPassword(String email) {
		return "redirect:/";
	}

	@GetMapping("/forgotten-password/recovery")
	public String forgottenPasswordRecovery() {
		return "user/forgotten-password-recovery";
	}

}
