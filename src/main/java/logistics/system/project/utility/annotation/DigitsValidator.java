package logistics.system.project.utility.annotation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;


public class DigitsValidator implements
		ConstraintValidator<Digits, Object> {

	private static final Log log = LoggerFactory.make();

	private int maxIntegerLength;
	private int maxFractionLength;
	private int length;

	@Override
	public void initialize(Digits annotation) {
		this.maxIntegerLength = annotation.integer();
		this.maxFractionLength = annotation.fraction();
		this.length = annotation.length();
		validateParameters();
	}

	@Override
	public boolean isValid(Object strObj,
			ConstraintValidatorContext constraintValidatorContext) {
		
		String str = (String) strObj;
		if (StringUtils.isEmpty(str)) {
			return true;
		}

		BigDecimal bigNum = getBigDecimalValue(str);
		if (bigNum == null) {
			return false;
		}

		int integerPartLength = bigNum.precision() - bigNum.scale();
		int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();
		
		if(this.length <= 0) {
			return (maxIntegerLength >= integerPartLength && maxFractionLength >= fractionPartLength);
		}
		else {
			return length > integerPartLength + fractionPartLength;
		}
	}

	private BigDecimal getBigDecimalValue(String str) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(str);
		} catch (NumberFormatException nfe) {
			return null;
		}
		return bd;
	}

	private void validateParameters() {
		if (maxIntegerLength < 0) {
			throw log.getInvalidLengthForIntegerPartException();
		}
		if (maxFractionLength < 0) {
			throw log.getInvalidLengthForFractionPartException();
		}
	}
	
}
