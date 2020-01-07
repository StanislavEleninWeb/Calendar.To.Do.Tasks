package app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.entity.User;
import app.service.UserService;

@Component
public class UserRegisterValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		// User
		User user = (User) target;

		// Username
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		// First Name
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
		if (user.getFirstName().length() < 2 || user.getFirstName().length() > 32) {
			errors.rejectValue("firstName", "Size.userForm.firstName");
		}

		// Last Name
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		if (user.getLastName().length() < 2 || user.getLastName().length() > 32) {
			errors.rejectValue("lastName", "Size.userForm.lastName");
		}

	}

}
