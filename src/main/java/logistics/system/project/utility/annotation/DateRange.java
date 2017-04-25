package logistics.system.project.utility.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import logistics.system.project.utility.validator.DateRangeValidator;

@Documented
@Constraint(validatedBy = { DateRangeValidator.class })
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface DateRange {
	String message() default "開始日時より後の日付を入れてください";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldStart();
	String fieldEnd();
}