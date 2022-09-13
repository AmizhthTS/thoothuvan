package com.smsapi.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;  
  
@Constraint(validatedBy = RequestIDValidator.class)  
@Documented
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface RequestID {  
    //error message  
        public String message() default "Invalid RequestID";  
    //represents group of constraints     
        public Class<?>[] groups() default {};  
    //represents additional information about annotation  
        public Class<? extends Payload>[] payload() default {};  
}  
