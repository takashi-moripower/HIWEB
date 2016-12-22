package logistics.system.project.utility.annotation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;


public class TimeValidator implements ConstraintValidator<Time, Object> {

	@Override
	public void initialize(Time annotation) {		
	}

	@Override
	public boolean isValid(Object strObj, ConstraintValidatorContext constraintValidatorContext) {
		String str = (String) strObj;
		if(StringUtils.isEmpty(str)) {
			return true;
		}
		
		String jpStr = str;
		jpStr = jpStr.replace(":", "");
		
		DateFormat df = new SimpleDateFormat("HHmm");
        Date d = null;
        try {
            d = df.parse(jpStr);
        }catch(Exception e) {
            return false;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeStr = sdf.format(d);
        return str.equals(timeStr);
       
	}

}
