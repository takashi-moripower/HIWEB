package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import logistics.system.project.utility.Chartset;

import org.apache.commons.lang.StringUtils;


public class SingleByteValidator implements
		ConstraintValidator<SingleByte, Object> {

	@Override
	public void initialize(SingleByte annotation) {
	}

	@Override
	public boolean isValid(Object strObj,
			ConstraintValidatorContext constraintValidatorContext) {
		
		String str = (String) strObj;

		if (StringUtils.isEmpty(str)) {
			return true;
		}
		
		for (int i = str.length() - 1; 0 <= i; i--) {
			if (!Chartset.isSingleByteDigit(str.charAt(i)) &&
			!Chartset.isSingleByteSpace(str.charAt(i)) &&
			!Chartset.isSingleByteSymbol(str.charAt(i)) &&
			!Chartset.isSingleByteAlpha(str.charAt(i)) &&
			!Chartset.isSingleByteKatakana(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}
}
