package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import logistics.system.project.utility.annotation.Length.Type;

import org.apache.commons.lang.StringUtils;

public class LengthValidator implements ConstraintValidator<Length, Object> {

	private int max;
	
	private String type;

	@Override
	public void initialize(Length annotation) {
		Type types[] = annotation.types();
		this.max = StringUtils.isEmpty(annotation.max()) ? 0 : 
			(StringUtils.isNumeric(annotation.max()) ? Integer.valueOf(annotation.max()): 0) ;
		type = StringUtils.EMPTY;
		for ( Type t : types ) {
			type = StringUtils.isEmpty(t.getValue()) ? "" : t.getValue();
		}
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		if(type.equals(Type.DATE_TYPE.getValue())) {
			str = str.replaceAll("/", "");
			int lastIndex = str.indexOf("(") == -1 ? str.length() : str.indexOf("(")-1;
			str = str.substring(0, lastIndex);
		}
		
		if(type.equals(Type.TIME_TYPE.getValue())) {
			str = str.replaceAll(":", "");
		}
		
		if(type.equals(Type.MONEY_TYPE.getValue())) {
			str = str.replaceAll(",", "");
		}
		
		if(type.equals(Type.MINUS_TYPE.getValue())) {
			str = str.replaceAll("-", "");
		}
		
		return str.trim().length() <= this.max;
	}

}
