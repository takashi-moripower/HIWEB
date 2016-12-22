package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

	@Override
	public void initialize(NotEmpty annotation) {
	}

	@Override
	public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {
		return str !=null && str != ""; 
	}

}
