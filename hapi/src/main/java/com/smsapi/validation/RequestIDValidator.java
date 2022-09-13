package com.smsapi.validation;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.smsapi.model.RequestModel;  
  
public class RequestIDValidator implements ConstraintValidator<RequestID,RequestModel> {  
  

    public boolean isValid(RequestModel requestModel, ConstraintValidatorContext cvc) {  
          

    	 if (requestModel instanceof RequestModel) {
    		 
    		 if(requestModel.getRequestID()==null||requestModel.getRequestID().trim().length()<1) {
    				
    				requestModel.setRequestID(UUID.randomUUID().toString());
    				
    			}
    		 
    		 
         }else {
        	 
             throw new IllegalArgumentException("@Credential only applies to RequestModel objects");

         }
    	 
       
        return true;  
    }  
    
    

}  