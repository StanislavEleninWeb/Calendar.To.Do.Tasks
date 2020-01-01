package app.util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

@Component
public class PassayUtil {

	public String generateSalt() {

		PasswordGenerator passwordGenerator = new PasswordGenerator();

		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		CharacterData digitsCaseChars = EnglishCharacterData.Digit;
		CharacterRule digitsCaseRule = new CharacterRule(digitsCaseChars);
		digitsCaseRule.setNumberOfCharacters(2);

		CharacterData specialCaseChars = new CharacterData() {

			@Override
			public String getErrorCode() {
				return "";
			}

			@Override
			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule specialCaseRule = new CharacterRule(specialCaseChars);
		specialCaseRule.setNumberOfCharacters(2);

		String salt = passwordGenerator.generatePassword(8, lowerCaseRule, upperCaseRule, digitsCaseRule,
				specialCaseRule);

		return salt;
	}

	public String generatePassword() {

		PasswordGenerator passwordGenerator = new PasswordGenerator();

		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(4);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(4);

		CharacterData digitsCaseChars = EnglishCharacterData.Digit;
		CharacterRule digitsCaseRule = new CharacterRule(digitsCaseChars);
		digitsCaseRule.setNumberOfCharacters(4);

		CharacterData specialCaseChars = new CharacterData() {

			@Override
			public String getErrorCode() {
				return "";
			}

			@Override
			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule specialCaseRule = new CharacterRule(specialCaseChars);
		specialCaseRule.setNumberOfCharacters(4);

		String salt = passwordGenerator.generatePassword(16, lowerCaseRule, upperCaseRule, digitsCaseRule,
				specialCaseRule);

		return salt;
	}

}
