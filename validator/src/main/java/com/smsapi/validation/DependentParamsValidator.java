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
  
public class DependentParamsValidator implements ConstraintValidator<DependentParams,RequestModel> {  
  
	@Autowired
	UserCacheModel usercachemodel;
	
    public boolean isValid(RequestModel object, ConstraintValidatorContext cvc) {  
        
    		boolean isEntity=true;
    		
    		boolean isTemplateId=true;

    	 if (object instanceof RequestModel) {
    		 
    		if (object.getEntityid()==null||object.getEntityid().trim().length()<1) {
    			
    			isEntity=false;
    		}
    		
    		if(object.getTemplateid()==null||object.getTemplateid().trim().length()<1) {
    			
    			isTemplateId=false;
    		}
    		
    		if((isEntity==false&&isTemplateId==false)) {
    			
    			return true;
    		}else if(isEntity&&isTemplateId) {
    			
    			return true;
    		}else {
    	
    	 	 	List<ErrorField> errors = new ArrayList<ErrorField>();
            	
                errors.add(new ErrorField("entityid","please submit the both entityid and templateid values",object.getEntityid()));
                errors.add(new ErrorField("templateid","please submit the both entityid and templateid values",object.getTemplateid()));

            	ErrorModel error1 = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error",errors );
           
            	throw new ValidationException("Parameter Dependency Error",error1,HttpStatus.BAD_REQUEST);

    			
    	   		}
    		 
         }else {
        	 
             throw new IllegalArgumentException("@DependentParams only applies to RequestModel objects");

         }
    	
    }  
    
    
}  