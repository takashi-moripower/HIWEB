package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class RangeValidator implements ConstraintValidator<Range, Object> {

	private int min;

	private int max;

	@Override
	public void initialize(Range annotation) {
		this.min = annotation.min();
		this.max = annotation.max();
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		if(strObj == null) {
			return true;
		}
		
		Integer value = null;
		if (strObj instanceof String) {
			String str = (String) strObj;
			if(StringUtils.isEmpty(str)) {
				return true;
			}
			try {
				value = Integer.parseInt((String) strObj);
			} catch (Exception e) {
				return false;
			}
		} else if (strObj instanceof Integer) {
			value = (Integer) strObj;
		}
		
		return value >= min && value <= max;
	}

}
