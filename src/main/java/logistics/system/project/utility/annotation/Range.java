package logistics.system.project.utility.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })  
@Constraint(validatedBy = { RangeValidator.class })
public @interface Range {
	String field();  
	
	int min();
	
	int max();
	  
    String message() default "{field.not.in.range}"; 
    
    Class<?>[] groups() default {};  
    
    Class<? extends Payload>[] payload() default {}; 
}
