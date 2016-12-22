package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class PostCodeValidator implements ConstraintValidator<PostCode, Object> {

	@Override
	public void initialize(PostCode annotation) {
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		return str.matches("^[0-9]{3}-[0-9]{4}$");
	}

}
