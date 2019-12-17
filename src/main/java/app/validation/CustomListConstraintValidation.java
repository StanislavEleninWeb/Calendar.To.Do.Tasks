package app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomListConstraintValidation implements ConstraintValidator<CustomListValidation, String> {

	private String coursePrefix;

	public void initialize(CustomListValidation customListValidation) {
		this.coursePrefix = customListValidation.value();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean result;

		if (value != null) {
			result = value.startsWith(coursePrefix);
		} else {
			return false;
		}

		return result;
	}

}
