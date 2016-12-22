package logistics.system.project.utility.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })  
@Constraint(validatedBy = { LengthValidator.class })
public @interface Length {
	String field();  
	
	String max();
	
	Type[] types() default {};
	  
    String message() default "{field.max.size}"; 
    
    Class<?>[] groups() default {};  
    
    Class<? extends Payload>[] payload() default {}; 
     
	public static enum Type {
		
		DATE_TYPE("date"),
		
		TIME_TYPE("time"),
		
		MONEY_TYPE("money"),
		
		MINUS_TYPE("minus");		

		private final String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
