package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.exception.AdminAvailableException;
import com.cg.exception.AdminLoggedInException;
import com.cg.exception.AdminLoggedOutException;
import com.cg.exception.AdminNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CustomerNotLoggedInException;
import com.cg.exception.HallNotAvailableException;
import com.cg.exception.VendorNotFoundException;
import com.cg.exception.HallNotFoundException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.SupervisorNotFoundException;

//Exception controller class
@ControllerAdvice
public class ExceptionController {

	//Method handling admin already logged in exception
	@ExceptionHandler(value = AdminLoggedInException.class)
	public ResponseEntity<Object> alreadyLoggedInException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	//Method handling invalid admin credentials exception
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<Object> invalidCredentialsException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);

	}

	//Method handling admin already logged out exception
	@ExceptionHandler(value = AdminLoggedOutException.class)
	public ResponseEntity<Object> alreadyLoggedOutException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);

	}

	//Method handling admin already available exception
	@ExceptionHandler(value = AdminAvailableException.class)
	public ResponseEntity<Object> adminAlreadyAvailableException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	//Method handling admin not found exception
	@ExceptionHandler(value = AdminNotFoundException.class)
	public ResponseEntity<Object> adminNotFoundException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	//Method handling supervisor not found exception
	@ExceptionHandler(value = SupervisorNotFoundException.class)
	public ResponseEntity<Object> supervisorNotFoundException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	//Method handling vendor not found exception
	@ExceptionHandler(value = VendorNotFoundException.class)
	public ResponseEntity<Object> vendorNotFoundException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	//Method handling customer not found exception
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> customerNotFoundException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	//Method handling customer not logged in exception
	@ExceptionHandler(value = CustomerNotLoggedInException.class)
	public ResponseEntity<Object> customerNotLoggedInException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	//Method handling hall not found exception
	@ExceptionHandler(value = HallNotFoundException.class)
	public ResponseEntity<Object> hallNotFoundException(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	//Method handling hall not available exception
	@ExceptionHandler(value = HallNotAvailableException.class)
	public ResponseEntity<Object> hallNotAvailableException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
