package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

//Customer repository
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //Method returns customer with email and password equals to that of given from parameters
	public Customer findByCustomerEmailAndCustomerPassword(String email, String password);
}
