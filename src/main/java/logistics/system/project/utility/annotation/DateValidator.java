package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import logistics.system.project.utility.ComUtils;

import org.apache.commons.lang.StringUtils;

public class DateValidator implements ConstraintValidator<Date, Object> {

	private String param;
	@Override
	public void initialize(Date annotation) {
		this.param = annotation.param();
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		
		if(StringUtils.isEmpty(str)) {
			return true;
		}
	
		if("jp".equalsIgnoreCase(this.param)) {
			String jpStr = str;
			jpStr = jpStr.replace("/", "");
			int lastIndex = jpStr.indexOf("(") == -1 ? jpStr.length() : jpStr.indexOf("(")-1;
			jpStr = jpStr.substring(0, lastIndex);
			
			try {
				jpStr = ComUtils.editDate(jpStr, "yyyyMMdd", "yyyy/MM/dd (E)");
			} catch( Exception e) {
				return false;
			}
			
			if(str.equals(jpStr)) {
				return true;
			}
			
			return false;
		}
		return false;
	}

}
