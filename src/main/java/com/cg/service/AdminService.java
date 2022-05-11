package com.cg.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cg.entity.Admin;
import com.cg.entity.Customer;
import com.cg.entity.Hall;
import com.cg.entity.Supervisor;
import com.cg.entity.Vendor;
import com.cg.exception.AdminAvailableException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.AdminLoggedInException;
import com.cg.exception.AdminLoggedOutException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.SupervisorNotFoundException;
import com.cg.repository.AdminRepository;
import com.cg.repository.CustomerRepository;
import com.cg.repository.HallRepository;
import com.cg.repository.SupervisorRepository;
import com.cg.repository.VendorRepository;
import com.cg.exception.VendorNotFoundException;

//Admin repository
@Service
public class AdminService {

	// Admin repository instance autowired
	@Autowired
	AdminRepository adminRepository;

	// Supervisor repository instance autowired
	@Autowired
	private SupervisorRepository supervisorRepository;

	// Vendor repository instance autowired
	@Autowired
	private VendorRepository vendorRepository;

	// Hall repository instance autowired
	@Autowired
	private HallRepository hallRepository;

	// Customer repository instance autowired
	@Autowired
	private CustomerRepository customerRepository;

	Admin currentAdmin = null;

	// Logger object initialized
	static Logger log = Logger.getLogger(AdminService.class.getName());

	// Admin services

	// Method for admin login, stores admin instance in currentAdmin
	public ResponseEntity<Object> loginAdmin(String email, String password) {

		if (currentAdmin != null) {

			log.error("Admin with ID, " + currentAdmin.getAdminId() + ", was already logged in.");
			throw new AdminLoggedInException("Admin is already logged in.");

		}

		if ((currentAdmin = adminRepository.findByAdminEmailAndAdminPassword(email, password)) != null) {

			log.info("Admin with ID, " + currentAdmin.getAdminId() + ", logged in successfully.");
			return new ResponseEntity<>("Admin login successfull.", HttpStatus.OK);

		}

		log.error("Admin with email, " + email + ", tried to login, invalid credentials.");

		throw new InvalidCredentialsException("Admin login failed, invalid credentials.");
	}

	// Method for admin logout, makes currentAdmin instance null
	public ResponseEntity<Object> logoutAdmin() {

		if (currentAdmin != null) {

			log.info("Admin with ID, " + currentAdmin.getAdminId() + ", logged out successfully.");

			currentAdmin = null;

			return new ResponseEntity<>("Admin logout successfull.", HttpStatus.OK);

		}

		log.error("Admin tried to logout, but no admin logged-in.");

		throw new AdminLoggedOutException("Currently no admin logged-in.");
	}

	// Method for addition of admin
	public ResponseEntity<Object> addAdmin(Admin admin) {

		if (adminRepository.findAll().isEmpty()) {

			try {

				adminRepository.save(admin);
				log.info("Admin with ID " + admin.getAdminId() + ", added successfully");

				return new ResponseEntity<>("Admin added successfully.", HttpStatus.OK);

			} catch (Exception e) {

				log.error("Tried to add, but admin data is invalid.");

				if (e.getCause().toString()
						.equals("javax.persistence.RollbackException: Error while committing the transaction")) {
					return new ResponseEntity<>("Please provide valid data for admin.", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("Error while adding admin.", HttpStatus.FORBIDDEN);
			}
		}

		log.error("Tried to add, but admin is available.");

		throw new AdminAvailableException("Error, currently admin is available.");

	}

	// Method for removal of admin with adminId equals to currentAdmin Id
	public ResponseEntity<Object> removeAdmin() {

		if (currentAdmin != null) {

			adminRepository.deleteById(currentAdmin.getAdminId());
			log.info("Admin with ID " + currentAdmin.getAdminId() + ", deleted successfully.");

			return new ResponseEntity<>("Admin deleted successfully.", HttpStatus.OK);

		}

		log.error("Tried to remove admin, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for updating email of admin who is currently logged in.
	public ResponseEntity<Object> updateAdminEmail(String email) {

		if (currentAdmin != null) {

			currentAdmin.setAdminEmail(email);
			adminRepository.save(currentAdmin);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", updated their email.");

			return new ResponseEntity<>("Admin email updated successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to update email, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for updating password of admin who is currently logged in.
	public ResponseEntity<Object> updateAdminPassword(String password) {

		if (currentAdmin != null) {

			currentAdmin.setAdminPassword(password);
			adminRepository.save(currentAdmin);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", updated their password.");

			return new ResponseEntity<>("Admin password updated successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to update password, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for updating admin who is currently logged in.
	public ResponseEntity<Object> updateAdmin(Admin newAdmin) {

		if (currentAdmin != null) {

			newAdmin.setAdminId(currentAdmin.getAdminId());

			currentAdmin = newAdmin;

			adminRepository.save(currentAdmin);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", updated their data.");

			return new ResponseEntity<>("Admin data updated successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to update data, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for getting admin, returns currentAdmin
	public ResponseEntity<Object> getAdmin() {

		if (currentAdmin != null) {

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed all admins.");

			return new ResponseEntity<>(currentAdmin, HttpStatus.OK);

		}

		log.error("Tried to access admin, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for getting admin revenue
	public ResponseEntity<Object> getAdminRevenue() {

		if (currentAdmin != null) {

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed revenue.");

			return new ResponseEntity<>("Revenue for admin with ID: " +
					currentAdmin.getAdminId() + " is:" + currentAdmin.getAdminRevenue(), HttpStatus.OK);

		}

		log.error("Tried to access admin revenue, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// Method for collecting admin revenue from each hall
	public ResponseEntity<Object> collectAdminRevenue() {

		if (currentAdmin != null) {

			List<Hall> halls = hallRepository.findAll();
			double totalRevenue = 0;

			for (Hall hall : halls) {

				totalRevenue += hall.getHallRevenue();

			}

			currentAdmin.setAdminRevenue(totalRevenue);

			adminRepository.save(currentAdmin);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", collected revenue.");

			return new ResponseEntity<>("Admin revenue collected.", HttpStatus.OK);

		}

		log.error("Tried to collect admin revenue, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// supervisor services
	public ResponseEntity<Object> addSupervisor(Supervisor s) {

		if (currentAdmin != null) {

			Supervisor supervisor = supervisorRepository.findBySupervisorEmail(s.getSupervisorEmail());
			if (supervisor == null) {
				s.setAdmin(currentAdmin);
				supervisorRepository.save(s);

				log.info(
						"Admin with ID " + currentAdmin.getAdminId() + ", added supervisor with ID,"
								+ s.getSupervisorId());

				return new ResponseEntity<>("Supervisor added successfully", HttpStatus.OK);
			}
			return new ResponseEntity<>("Supervisor with this mail already exists.", HttpStatus.OK);

		}

		log.error("Admin tried to add supervisor, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> getAllSupervisor() {

		if (currentAdmin != null) {

			List<Supervisor> supervisors = supervisorRepository.findAll();

			if (supervisors.isEmpty()) {

				log.error("Admin tried to access supervisors, but no supervisors present.");

				throw new SupervisorNotFoundException("Supervisors not found.");

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed supervisors.");

			return new ResponseEntity<>(supervisors, HttpStatus.OK);
		}

		log.error("Admin tried to access supervisors without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> removeAllSupervisor() {

		if (currentAdmin != null) {

			List<Supervisor> supervisors = supervisorRepository.findAll();

			if (supervisors.isEmpty()) {

				log.error("Admin tried to remove all supervisors, but no supervisors present.");

				throw new SupervisorNotFoundException("Supervisors not found.");

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", deleted all supervisors.");

			return new ResponseEntity<>("All supervisors deleted successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to delete supervisors without login.");
		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> removeBySupervisorId(Integer id) {

		if (currentAdmin != null) {

			Optional<Supervisor> supervisor = supervisorRepository.findById(id);

			if (supervisor.isEmpty()) {

				log.error("Admin tried to remove supervisor, but no supervisor present.");

				throw new SupervisorNotFoundException();

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", deleted supervisor with ID, " + id);
			return new ResponseEntity<>("Supervisor deleted successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to delete supervisor without login.");
		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> getBySupervisorId(Integer id) {

		if (currentAdmin != null) {

			Optional<Supervisor> supervisor = supervisorRepository.findById(id);

			if (supervisor.isEmpty()) {

				log.error("Admin tried to access supervisor by id, but no supervisor present.");

				throw new SupervisorNotFoundException();

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed supervisor with ID, " + id);

			return new ResponseEntity<>(supervisor, HttpStatus.OK);

		}

		log.error("Admin tried to access supervisor without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> getSortedBySupervisorName() {

		if (currentAdmin != null) {

			List<Supervisor> supervisors = supervisorRepository.findAll(Sort.by("supervisorName"));

			if (supervisors.isEmpty()) {

				log.error("Admin tried to get sorted supervisors, but no supervisors present.");

				throw new SupervisorNotFoundException();

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed all supervisors.");

			return new ResponseEntity<>(supervisors, HttpStatus.OK);
		}

		log.error("Admin tried to access supervisor without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// vendor services
	// method to add vendor
	public ResponseEntity<Object> addVendor(Vendor vendor) {

		if (currentAdmin != null) {
			Vendor vendorNew = vendorRepository.findByVendorContact(vendor.getVendorContact());
			if (vendorNew == null) {
				vendor.setAdmin(currentAdmin);
				vendorRepository.save(vendor);
				log.info("Admin with ID " + currentAdmin.getAdminId() + ", added vendor with ID,"
						+ vendor.getVendorId());

				return new ResponseEntity<>("Vendor added successfully.", HttpStatus.OK);

			}
			return new ResponseEntity<>("Vendor with this contact already exists.", HttpStatus.OK);

		}
		log.error("Admin tried to add vendor, but no admin logged in");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// method to remove all present vendors from database.
	public ResponseEntity<Object> removeAllVendor() {

		if (currentAdmin != null) {

			if (vendorRepository.count() != 0) {

				vendorRepository.deleteAll();
				log.info("Admin with ID " + currentAdmin.getAdminId() + " deleted all vendors succesfully.");

				return new ResponseEntity<>("All vendors deleted successfully.", HttpStatus.OK);

			}
			log.error("No vendors are present");
			throw new VendorNotFoundException("Vendor not found.");
		}

		log.error("Admin tried to remove vendors without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// method to remove a specific vendor using its id.
	public ResponseEntity<Object> removeByVendorId(int id) {

		if (currentAdmin != null) {

			if (vendorRepository.existsById(id)) {

				vendorRepository.deleteById(id);
				log.info("Admin with ID " + currentAdmin.getAdminId() + ", removed vendor with ID," + id);

				return new ResponseEntity<>("Vendor deleted successfully.", HttpStatus.OK);

			}
			log.error("No vendor present");
			throw new VendorNotFoundException("Vendor not found.");
		}

		log.error("Admin tried to remove vendor without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");
	}

	// method to fetch all the vendors added in the database
	public ResponseEntity<Object> getAllVendor() {

		if (currentAdmin != null) {

			List<Vendor> vendors = vendorRepository.findAll();

			if (vendors.isEmpty()) {
				log.error("Admin tried to access vendors, but no vendors present.");

				throw new VendorNotFoundException("Vendor not found.");

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed vendors.");

			return new ResponseEntity<>(vendors, HttpStatus.OK);
		}

		log.error("Admin tried to access vendors without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// method to update vendor contact details.
	public ResponseEntity<Object> updateVendorByContact(int vendorId, String contact) {

		if (currentAdmin != null) {

			Vendor vendor = vendorRepository.findById(vendorId).get();

			if (vendor == null) {

				log.error("No vendor is present with these details.");
				throw new VendorNotFoundException("Vendor not found.");

			}

			vendor.setVendorContact(contact);
			vendorRepository.save(vendor);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", updated vendor with ID " + vendorId);

			return new ResponseEntity<>("Vendor with id " + vendorId + " updated successfully.", HttpStatus.OK);
		}

		log.error("Admin tried to access vendors without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// method to update the vendor services details.
	public ResponseEntity<Object> updateVendorByServices(int vendorId, boolean flower, boolean music, boolean catering,
			boolean video) {

		if (currentAdmin != null) {

			Vendor vendor = vendorRepository.findById(vendorId).get();

			if (vendor == null) {

				log.error("No vendor is present with these details.");
				throw new VendorNotFoundException("Vendor not found.");

			}

			vendor.setFlower(flower);
			vendor.setMusic(music);
			vendor.setVideo(video);
			vendor.setCatering(catering);
			vendorRepository.save(vendor);

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", updated vendor with ID " + vendorId);

			return new ResponseEntity<>("Vendor with id " + vendorId + " updated successfully.", HttpStatus.OK);
		}

		log.error("Admin tried to access vendors without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	// method to fetch a specific vendor using its id
	public ResponseEntity<Object> getByVendorId(int id) {

		if (currentAdmin != null) {

			Optional<Vendor> vendors = vendorRepository.findById(id);

			if (vendors.isEmpty()) {

				log.error("Admin tried to access vendors, but no vendors present.");

				throw new VendorNotFoundException("Vendor Not Found");

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", accessed vendor with id " + id);

			return new ResponseEntity<>(vendors, HttpStatus.OK);

		}

		log.error("Admin tried to access vendor without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> getAllCustomers() {

		if (currentAdmin != null) {

			List<Customer> customers = customerRepository.findAll();

			if (customers.isEmpty()) {

				log.error("Admin tried to access customers, but no customers present.");

				throw new CustomerNotFoundException("Customers not found.");
			}

			log.info("Admin accessed all customers.");

			return new ResponseEntity<>(customers, HttpStatus.OK);

		}

		log.error("Admin tried to access customers without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> getCustomersBetween(Date from, Date to) {

		if (currentAdmin != null) {

			List<Customer> customers = customerRepository.findAll();

			if (customers.isEmpty()) {

				log.error("Admin tried to access customers, but no customers present.");

				throw new CustomerNotFoundException("Customers not found.");
			}

			for (Customer customer : customers) {

				if ((!customer.getBookHallFrom().after(from) || customer.getBookHallTo().before(to))) {
					customers.remove(customer);
				}
			}

			log.info("Admin accessed customers, between date " + from + " to " + to);

			return new ResponseEntity<>(customers, HttpStatus.OK);

		}

		log.error("Admin tried to access customers without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");

	}

	public ResponseEntity<Object> removeCustomerById(int id) {

		if (currentAdmin != null) {

			Optional<Customer> customer = customerRepository.findById(id);

			if (customer.isEmpty()) {

				log.error("Admin tried to access supervisors, but no customers present.");

				throw new CustomerNotFoundException("Customers not found.");

			}

			log.info("Admin with ID " + currentAdmin.getAdminId() + ", deleted customer with ID, " + id);

			return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);

		}

		log.error("Admin tried to remove customer without login.");

		throw new AdminLoggedOutException("No admin logged in, please login as admin.");
	}
}