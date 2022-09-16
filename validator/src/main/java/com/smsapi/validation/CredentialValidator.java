package com.smsapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.smsapi.model.ErrorField;
import com.smsapi.model.ErrorModel;
import com.smsapi.model.RequestModel;
import com.smsapi.model.UserCacheModel;  
  
public class CredentialValidator implements ConstraintValidator<Credential,RequestModel> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    public boolean isValid(RequestModel object, ConstraintValidatorContext cvc) {  
          
    	 if (object instanceof RequestModel) {
    		 
    		 if(userAvailabiltyCheck(object)) {
    	        	
    				if(passwordCheck(object)) {
    					
    					return true;
    				}

    	      }
    		 
         }else {
        	 
             throw new IllegalArgumentException("@Credential only applies to RequestModel objects");

         }
    	 
    	 	List<ErrorField> errors = new ArrayList<ErrorField>();
        	
            errors.add(new ErrorField("username","Invalid Credential",object.getUsername()));
            errors.add(new ErrorField("password","Invalid Credential",""));

        	ErrorModel error1 = new ErrorModel(HttpStatus.UNAUTHORIZED, "Validation Error",errors );
       
        	throw new ValidationException("Invalid Credential",error1,HttpStatus.UNAUTHORIZED);
  	

    }  
    
    
    private boolean userAvailabiltyCheck(RequestModel requestModel) {
		
    	
		if(usercachemodel.getUsercache().containsKey(requestModel.getUsername())) {
			
		
			return true;
		}
		
		
		return false;
		
	}
    
    
    private boolean passwordCheck(RequestModel requestModel) {
	
    	if(usercachemodel.getUsercache().containsKey(requestModel.getUsername())) {

    		String password=requestModel.getPassword()==null?"":requestModel.getPassword().trim();

		if(usercachemodel.getUsercache().get(requestModel.getUsername()).getPassword().equals(password)) {
		
			return true;
		}
    	}		
		return false;
		
	}

}  