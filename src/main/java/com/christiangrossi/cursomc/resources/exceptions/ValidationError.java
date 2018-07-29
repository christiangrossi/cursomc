package com.christiangrossi.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	public List<FieldMessage> errors = new ArrayList<>();
	public ValidationError(Integer status, String msg, long timeStamp) {
		super(status, msg, timeStamp);
	}
	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fildName, String message) {
		errors.add(new FieldMessage(fildName, message));
	}

}
