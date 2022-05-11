package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.cg.entity.Admin;
import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;
import com.cg.MhbaApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.service.CustomerService;

class CustomerServiceTest extends MhbaApplicationTests {

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	Customer customer;
	Admin admin;

	@BeforeEach
	void setUp() {
		admin = new Admin(101, "Onkar", "Magadum", "onkarmagadum@gmail.com", 31, "9000000000", "Abc@123");
		customer = new Customer(501, "Kaustubh Chillure", "kaustubh@gmail.com", "Abc@123", "9000000000", null, null);
	}

	@Test
	final void testLoginCustomer() {

		when(customerRepository.findByCustomerEmailAndCustomerPassword("kaustubh@gmail.com",
				"Abc@123"))
				.thenReturn(customer);

		assertEquals("<200 OK OK,Customer login successfull.,[]>",
				customerService.loginCustomer("kaustubh@gmail.com", "Abc@123").toString());
	}

	@Test
	final void testLogoutCustomer() {
		when(customerRepository.findByCustomerEmailAndCustomerPassword("kaustubh@gmail.com",
				"Abc@123"))
				.thenReturn(customer);

		customerService.loginCustomer("kaustubh@gmail.com", "Abc@123");

		assertEquals("<200 OK OK,Customer logout successfull.,[]>",
				customerService.logoutCustomer().toString());
	}

}
