package com.smsapi.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;  
  
@Constraint(validatedBy = BalanceValidator.class)  
@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Balance {  
    //error message  
        public String message() default "No Balance for send the sms";  
    //represents group of constraints     
        public Class<?>[] groups() default {};  
    //represents additional information about annotation  
        public Class<? extends Payload>[] payload() default {};  
}  
