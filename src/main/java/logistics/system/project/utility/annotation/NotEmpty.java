package logistics.system.project.utility.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })  
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {
	String field();  
	  
    String message() default "{org.hibernate.validator.constraints.NotEmpty.message}";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {}; 
}
