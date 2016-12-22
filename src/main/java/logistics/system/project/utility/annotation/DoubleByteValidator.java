package logistics.system.project.utility.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import logistics.system.project.utility.Chartset;

import org.apache.commons.lang.StringUtils;


public class DoubleByteValidator implements
		ConstraintValidator<DoubleByte, Object> {

	@Override
	public void initialize(DoubleByte annotation) {
	}

	@Override
	public boolean isValid(Object strObj,
			ConstraintValidatorContext constraintValidatorContext) {
		
		String str = (String) strObj;

		if (StringUtils.isEmpty(str)) {
			return true;
		}
		
//		for (int i = str.length() - 1; 0 <= i; i--) {
//			if (Chartset.isSingleByte(str.charAt(i))) {
//				return false;
//			}
//		}

		return true;
	}
	
	public static void main(String[] args) {
		String str = "ｑ　ｊ　ｋｋ　";
		
		System.out.println(str.getBytes().length);
		
		System.out.println(str.length() *2);
		
		System.out.println(str.getBytes().length != str.length() *2);
	}
}
