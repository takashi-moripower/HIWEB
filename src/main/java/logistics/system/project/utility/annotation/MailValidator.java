package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class MailValidator implements ConstraintValidator<Mail, Object> {
	
//	public static final int minLength = 15;
	
	public static final int maxLength = 60;

	@Override
	public void initialize(Mail annotation) {
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		return str.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
	}

}
