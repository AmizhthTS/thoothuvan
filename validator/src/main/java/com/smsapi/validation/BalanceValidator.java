package com.smsapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.smsapi.model.ErrorField;
import com.smsapi.model.ErrorModel;
import com.smsapi.model.UserCacheModel;  
  
public class BalanceValidator implements ConstraintValidator<Balance,String> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    public boolean isValid(String username, ConstraintValidatorContext cvc) {  
          
    
    	if(usercachemodel.getUsercache().containsKey(username)) {

    	if(usercachemodel.getUsercache().get(username).getBalanceavailable()==1) {
			
    		
			return true;
		}
    	
    	
 	 	List<ErrorField> errors = new ArrayList<ErrorField>();
    	
        errors.add(new ErrorField("username","username have no balance",username));

    	ErrorModel error1 = new ErrorModel(HttpStatus.PAYMENT_REQUIRED, "Validation Error",errors );
   
    	throw new ValidationException("username have no balance",error1,HttpStatus.PAYMENT_REQUIRED);

    	}
		
	
    	return true;
	
    }  
    
}  