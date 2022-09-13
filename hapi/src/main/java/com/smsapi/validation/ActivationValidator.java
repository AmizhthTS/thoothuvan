package com.smsapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.smsapi.model.ErrorField;
import com.smsapi.model.ErrorModel;
import com.smsapi.model.UserCacheModel;  
  
public class ActivationValidator implements ConstraintValidator<Activation,String> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    @SuppressWarnings("deprecation")
	public boolean isValid(String username, ConstraintValidatorContext cvc) {  
          
    	
    	if(usercachemodel.getUsercache().containsKey(username)) {
    	if(usercachemodel.getUsercache().get(username).getStatus()==1) {

    		return true;
		
    	}

 	 	List<ErrorField> errors = new ArrayList<ErrorField>();
    	
        errors.add(new ErrorField("username","username Disabled",username));

    	ErrorModel error1 = new ErrorModel(HttpStatus.MOVED_TEMPORARILY, "Validation Error",errors );
   
    	throw new ValidationException("username Disabled",error1,HttpStatus.MOVED_TEMPORARILY);


    	}else {
    		
    		return true;
    	}
    	
		

    }  
    
}  