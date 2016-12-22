package logistics.system.project.utility.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })  
@Constraint(validatedBy = { DoubleByteValidator.class })
public @interface DoubleByte {
	
	String field();
	
	String message() default "{field.not.double.byte}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default {};
}