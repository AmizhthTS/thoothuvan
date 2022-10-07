package com.smsapi.sc.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorMessage {

	public static String getMessage(Exception ex) {
		
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
