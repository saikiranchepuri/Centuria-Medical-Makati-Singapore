package com.nzion.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sandeep Prusty
 * Apr 12, 2010
 */
public class UtilValidator {

	public static final Pattern ALPHABETIC_PATTERN = Pattern.compile("[\\p{Alpha}]+");

	public static final Pattern ALPHABETIC_WHITE_SPACE_PATTERN = Pattern.compile("[\\p{Alpha}\\p{Space}]+");

	public static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAIL_PATTERN);

	public static final Pattern NUMERIC_PATTERN = Pattern.compile("[\\d]+");

	public static final Pattern NINE_DIGIT_NUMERIC_PATTERN = Pattern.compile("[\\d]{9}");

	public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])(?=.{8,}).+$");

	public static final Pattern NON_DIGIT_PATTERN = Pattern.compile("[\\D]+");

	public static final Pattern ALPHA_NUMERIC_PATTERN = Pattern.compile("[\\p{Alnum}]+");

	public static final Pattern ALPHABETIC_DOT_PATTERN = Pattern.compile("[\\p{Alpha}.]+");

	public static final Pattern CHARACTER_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[\\p{Alpha}])(?=.*[\\p{Punct}]).{8,})");

	public static final Pattern ALPHANUMERIC_WHITE_SPACE_PATTERN = Pattern.compile("[\\p{Alnum}\\p{Alpha}\\p{Space}]+");

	public static boolean isAllEmpty(Object object, String ...fields){
		for(String fieldName : fields)
			if(isNotEmpty(UtilReflection.getNestedFieldValue(object, fieldName)))
				return false;
		return true;
	}

	public static boolean isEmpty(Object object){
		if(object == null)
			return true;
		if(object instanceof Collection)
			return isEmpty((Collection<?>) object);
		if(object instanceof Map)
			isEmpty((Map<?, ?>)object);
		if(object instanceof String)
			isEmpty((String)object);
		return false;
	}

	public static boolean isNotEmpty(Object object){
		return !isEmpty(object);
	}

	public static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.size() == 0;
	}

	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}

	public static boolean isEmpty(Map<?,?> map){
		return map == null || map.size() == 0;
	}

	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}

	public static boolean isEmpty(String s){
		return s == null || s.trim().length() == 0;
	}

	public static boolean isNotEmpty(String s){
		return !isEmpty(s);
	}

	public static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}

	public static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}

	public static boolean validateEmail(String email){
		return validatePattern(email, EMAIL_PATTERN);
	}

	public static boolean validateOnlyAlphabets(String alphabets){
		return validatePattern(alphabets, ALPHABETIC_PATTERN);
	}

	public static boolean validateOnlyAlphabetsAndWhiteSpaces(String alphabetsAndWhiteSpaces){
		return validatePattern(alphabetsAndWhiteSpaces,ALPHABETIC_WHITE_SPACE_PATTERN);
	}

	public static boolean validateOnlyAlphaNumericAndWhiteSpaces(String alphabetsAndWhiteSpaces){
		return validatePattern(alphabetsAndWhiteSpaces,ALPHANUMERIC_WHITE_SPACE_PATTERN);
	}

	public static boolean validateOnlyDigits(String digits){
		return validatePattern(digits, NUMERIC_PATTERN);
	}

	public static boolean validateNonDigits(String nonDigits){
		return validatePattern(nonDigits, NON_DIGIT_PATTERN);
	}

	public static boolean validateAlphaNumeric(String alphaNumeric){
		return validatePattern(alphaNumeric, ALPHA_NUMERIC_PATTERN);
	}

	public static boolean validateAlphabetsAndDots(String alphabetsAndDots){
		return validatePattern(alphabetsAndDots, ALPHABETIC_DOT_PATTERN);
	}

	public static boolean validateCharacters(String characters){
		return validatePattern(characters, CHARACTER_PATTERN);
	}

	public static boolean validateNineDigits(String nineDigits){
		return validatePattern(nineDigits, NINE_DIGIT_NUMERIC_PATTERN);
	}

	public static boolean validatePasswordWithPolicy(String password){
		if(checkIfConsecutiveCharSpecialCharacter(password))
			return validatePattern(password, PASSWORD_PATTERN);
		else {
			return Boolean.FALSE;
		}
	}

	private static boolean checkIfConsecutiveCharSpecialCharacter(String password){
		String iChars = "!@#$%^&*";
		for (int index = 0; index < password.length()-1; index++) {
			if (iChars.indexOf(password.charAt(index)) != -1) {
				if(iChars.indexOf(password.charAt(index+1)) != -1){
					return false;
				}
			}
		}
		if(password.indexOf(' ') != -1){
			return false;
		}
		return true;
	}

	private static boolean validatePattern(String toBeValidated, Pattern pattern){
		if(isEmpty(toBeValidated))
			return false;
		Matcher m = pattern.matcher(toBeValidated);
		return m.matches();
	}
}
