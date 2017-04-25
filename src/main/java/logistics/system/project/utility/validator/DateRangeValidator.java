package logistics.system.project.utility.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

import logistics.system.project.utility.annotation.DateRange;

public class DateRangeValidator implements  ConstraintValidator< DateRange , Object>{
    private String message;
    private String fieldStart;
    private String fieldEnd;

	@Override
	public void initialize(DateRange constraintAnnotation) {
        message = constraintAnnotation.message();
        fieldStart = constraintAnnotation.fieldStart();
        fieldEnd = constraintAnnotation.fieldEnd();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String valueStart = ObjectUtils.nullSafeToString (beanWrapper.getPropertyValue(fieldStart));
        String valueEnd = ObjectUtils.nullSafeToString (beanWrapper.getPropertyValue(fieldEnd));

        if(valueStart.compareTo(valueEnd) <= 0 ){
        	return true;

        }else{
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addNode("dateStart").addConstraintViolation();

            return false;

        }
	}
}
