package logistics.system.project.utility.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;


public class PatternsValidator implements ConstraintValidator<Patterns, Object> {
	
	private Pattern p;
	
	@Override
	public void initialize(Patterns annotation) {
		p = Pattern.compile(annotation.regexp());
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		Matcher m = p.matcher(str);  
		
        return m.matches();
       
	}

}
