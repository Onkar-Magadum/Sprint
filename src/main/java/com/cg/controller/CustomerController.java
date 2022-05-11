package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.entity.Customer;
import com.cg.service.CustomerService;
import com.cg.service.HallService;

//Service controller
@RestController
public class CustomerController {

  // Customer service instance autowired
  @Autowired
  private CustomerService customerService;

  // Hall service instance autowired
  @Autowired
  private HallService hallService;

  // Controller takes customer email and password from path variable, verify
  // against database, and returns response entity.
  @RequestMapping(value = "/loginCustomer/{email}/{password}")
  public ResponseEntity<Object> loginAdmin(@PathVariable String email, @PathVariable String password) {
    return customerService.loginCustomer(email, password);
  }

  // Controller takes request to logout customer, and returns response entity as
  // result.
  @RequestMapping(value = "/logoutCustomer")
  public ResponseEntity<Object> logoutCustomer() {

    return customerService.logoutCustomer();
  }

  // Controller takes customer object as parameter from request body and returns
  // response entity.
  @PostMapping("/addCustomer")
  public ResponseEntity<Object> addCustomer(@RequestBody Customer c) {
    return customerService.addCustomer(c);
  }

  // Controller takes city preference as path variable and returns Response entity
  @GetMapping("/getHallByCity/{city}")
  public ResponseEntity<Object> getHallByCity(@PathVariable String city) {
    return hallService.findHallByCity(city);
  }

  // Controller takes city and location preference as path variables and returns
  // Response entity
  @GetMapping("/getHallByLocation/{city}/{location}")
  public ResponseEntity<Object> getHallByLocation(@PathVariable String city, @PathVariable String location) {
    return hallService.findHallByLocation(city, location);
  }

  // Controller takes capacity , city and location preference as path variables
  // and returns Response entity
  @GetMapping("/getHallByCapacity/{city}/{capacity}")
  public ResponseEntity<Object> getHallByCapacity(@PathVariable String city, @PathVariable int capacity) {
    return hallService.findByCapacity(city, capacity);
  }

  @RequestMapping(value = "/updateCustomerEmail/{email}")
  public ResponseEntity<Object> updateCustomerEmail(@PathVariable String email) {

    return customerService.updateCustomerEmail(email);

  }

  // Controller takes request to update password for customer who is currently
  // logged
  // in and returns response entity.
  @RequestMapping(value = "/updateCustomerPassword/{password}")
  public ResponseEntity<Object> updateCustomerPassword(@PathVariable String password) {

    return customerService.updateCustomerPassword(password);

  }

  // Controller returns all the halls as response entity.
  @GetMapping("/getAllHall")
  public ResponseEntity<Object> getHall() {
    return hallService.getAllHall();
  }

  // Controller takes customer preferences as parameter as path variable and
  // returns response entity.
  @PostMapping("/bookHall/{city}/{location}/{flower}/{catering}/{music}/{video}")
  public ResponseEntity<Object> bookHall(@PathVariable String city,
      @PathVariable String location, @PathVariable boolean flower,
      @PathVariable boolean catering, @PathVariable boolean music, @PathVariable boolean video) {
    return customerService.bookHall(city, location, flower, catering, music, video);
  }

}