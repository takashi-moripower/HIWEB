package logistics.system.project.utility.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import logistics.system.project.utility.validator.DateFutureValidator;

@Documented
@Constraint(validatedBy = { DateFutureValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER })
@Retention(RUNTIME)
public @interface DateFuture {
	String message() default "今日以降の日付を入れてください";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}