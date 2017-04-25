package logistics.system.project.utility.validator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.time.DateUtils;

import logistics.system.project.utility.Constants;
import logistics.system.project.utility.LogRecord;
import logistics.system.project.utility.annotation.DateFuture;

public class DateFutureValidator implements ConstraintValidator<DateFuture, String> {
	private String message;

	@Override
	public void initialize(DateFuture constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Date d;

		try {
			d = Constants.DATE_FORMAT_FORM.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

		LogRecord.info( today.toString() );

		if( today.compareTo(d) > 0 ){
			return false;
		}

		return true;

	}
}
