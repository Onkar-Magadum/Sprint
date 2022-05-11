package com.cg.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.entity.Customer;
import com.cg.entity.Hall;
import com.cg.repository.CustomerRepository;
import com.cg.repository.HallRepository;
import com.cg.exception.HallNotFoundException;
import com.cg.exception.CustomerNotLoggedInException;
import com.cg.exception.InvalidCredentialsException;

@Service
public class CustomerService {

	// Customer repository instance autowired
	@Autowired
	private CustomerRepository customerRepository;

	// Hall repository instance autowired
	@Autowired
	private HallRepository hallRepository;

	// Vendor Service instance autowired
	@Autowired
	private VendorService vendorService;

	// Supervisor Service instance autowired
	@Autowired
	private SupervisorService supervisorService;

	// Hall Service instance autowired
	@Autowired
	private HallService hallService;

	// Logger object initialized
	static Logger log = Logger.getLogger(CustomerService.class.getName());

	Customer currentCustomer = null;

	// Customer services

	// Method for customer login, stores customer instance in currentAdmin
	public ResponseEntity<Object> loginCustomer(String email, String password) {

		if (currentCustomer != null) {

			log.info("Customer loggedin");
			return new ResponseEntity<>("Customer is already logged in.", HttpStatus.BAD_REQUEST);

		}

		if ((currentCustomer = customerRepository.findByCustomerEmailAndCustomerPassword(email, password)) != null) {

			log.info("Customer with ID, " + currentCustomer.getCustomerId() + ", logged in successfully.");
			return new ResponseEntity<>("Customer login successfull.", HttpStatus.OK);

		}

		log.error("Customer with email, " + email + ", tried to login, invalid credentials.");

		throw new InvalidCredentialsException("Customer login failed, invalid credentials.");

	}

	// Method for customer logout, makes currentCustomer instance null
	public ResponseEntity<Object> logoutCustomer() {

		if (currentCustomer != null) {
			currentCustomer = null;
			log.info("customer loggedout");
			return new ResponseEntity<>("Customer logout successfull.", HttpStatus.OK);
		}
		log.error("No customer is currently logged-In");
		throw new CustomerNotLoggedInException("Error, currently no Customer logged-in.");
	}

	// Method for adding Customer to customer repository
	public ResponseEntity<Object> addCustomer(Customer c) {

		if (c.getBookHallFrom().before(c.getBookHallTo())) {

			try {
				customerRepository.save(c);
				log.info("Customer added");
				return new ResponseEntity<>("Customer added successfully.", HttpStatus.OK);
			} catch (Exception e) {
				log.error("Tried to add customer, but customer data is invalid.");
				if (e.getCause().toString()
						.equals("javax.persistence.RollbackException: Error while committing the transaction"))
					return new ResponseEntity<>("Please provide valid data.", HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>("Error while adding admin.", HttpStatus.FORBIDDEN);
			}
		}

		log.error("Tried with invalid booking dates");
		return new ResponseEntity<>("Please provide valid booking dates.", HttpStatus.OK);
	}

	// Method for updating email of customer who is currently logged in.
	public ResponseEntity<Object> updateCustomerEmail(String email) {

		if (currentCustomer != null) {

			currentCustomer.setCustomerEmail(email);
			customerRepository.save(currentCustomer);

			log.info("Customer with ID " + currentCustomer.getCustomerId() + ", updated their email.");

			return new ResponseEntity<>("Customer email updated successfully.", HttpStatus.OK);

		}

		log.error("Customer tried to update email, but no customer logged in");

		throw new CustomerNotLoggedInException("No customer logged in, please login as customer.");

	}

	// Method for updating password of customer who is currently logged in.
	public ResponseEntity<Object> updateCustomerPassword(String password) {

		if (currentCustomer != null) {

			currentCustomer.setCustomerPassword(password);
			customerRepository.save(currentCustomer);

			log.info("Customer with ID " + currentCustomer.getCustomerId() + ", updated their email.");

			return new ResponseEntity<>("Customer password updated successfully.", HttpStatus.OK);

		}

		log.error("Customer tried to update password, but no customer logged in");

		throw new CustomerNotLoggedInException("No admin logged in, please login as admin.");

	}

	// Method for booking hall and vendor with customer preferences
	public ResponseEntity<Object> bookHall(String city, String location, boolean flower,
			boolean catering,
			boolean music, boolean video) {
		if (currentCustomer != null) {
			int customerId = currentCustomer.getCustomerId();
			Customer c = customerRepository.findById(customerId).get();
			List<Hall> halls = hallRepository.findByHallCityAndHallLocation(city, location);

			if (halls.isEmpty()) {
				log.error("No hall available in " + city + " at " + location);
				throw new HallNotFoundException("Currently no halls available at your location");
			} else {

				boolean booked = false;
				Hall hall = null;
				for (Hall h : halls) {
					if ((h.getHallBookedFrom() == null && h.getHallBookedTo() == null)
							|| (c.getBookHallFrom().after(h.getHallBookedTo())
									|| c.getBookHallTo().before(h.getHallBookedFrom()))) {

						h.setHallBookedFrom(c.getBookHallFrom());
						h.setHallBookedTo(c.getBookHallTo());

						List<Hall> hallList = c.getHalls();

						c.setHalls(hallList);
						h.setHallBookingStatus(true);

						hallList.add(h);
						log.error("Hall already booked for mentioned duration");

						booked = true;
						hall = h;

						break;

					}
				}

				if (!booked) {

					log.error("Hall already booked at that duration");
					return new ResponseEntity<>("Hall already booked at that duration , please select another slot.",
							HttpStatus.OK);
				}

				boolean status = vendorService.bookVendor(hall.getHallId(), c.getBookHallFrom(),
						c.getBookHallTo(), flower, catering, music, video);

				if (!status) {

					log.error("No vendor available for mentioned service");
					return new ResponseEntity<>(
							"No vendor available for mentioned services, please select another combinations.",
							HttpStatus.OK);
				}

				double bill = supervisorService.generateBill(hall.getHallId(), flower, catering, music, video);

				c.setCustomerBill(Math.round(bill));

				hallService.updateRevenue(hall.getHallId(), bill);

				hallRepository.save(hall);
				customerRepository.save(c);

				log.info("Hall and vendor booked successfully");
				return new ResponseEntity<>(
						"Hall and vendor boooked successfully, your bill amount is: " + bill, HttpStatus.OK);

			}
		}
		log.error("No customer logged-in");
		throw new CustomerNotLoggedInException("No customer logged in, please login as customer");
	}
}
