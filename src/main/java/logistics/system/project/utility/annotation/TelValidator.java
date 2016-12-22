package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class TelValidator implements ConstraintValidator<Tel, Object> {

	@Override
	public void initialize(Tel annotation) {
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		return str.matches("^[0-9-]*$");
	}

}
