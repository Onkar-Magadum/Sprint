package com.cg.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Admin;
import com.cg.entity.Supervisor;
import com.cg.entity.Vendor;
import com.cg.service.AdminService;

//Admin controller
@RestController
@RequestMapping("/admin")
public class AdminController {

	// Admin service instance autowired
	@Autowired
	AdminService adminService;

	// Admin controllers

	// Controller takes admin email and password from path variable, verify against
	// database, and returns response entity.
	@GetMapping(value = "/loginAdmin/{email}/{password}")
	public ResponseEntity<Object> loginAdmin(@PathVariable String email, @PathVariable String password) {

		return adminService.loginAdmin(email, password);

	}

	// Controller takes request to logout admin, and returns response entity as
	// result.
	@GetMapping(value = "/logoutAdmin")
	public ResponseEntity<Object> logoutAdmin() {

		return adminService.logoutAdmin();

	}

	// Controller takes admin object as parameter from request body and returns
	// response entity.
	@PostMapping(value = "/addAdmin")
	public ResponseEntity<Object> addAdmin(@RequestBody Admin admin) {

		return adminService.addAdmin(admin);

	}

	// Controller takes request to remove admin who is currently logged in and
	// returns response entity.
	@DeleteMapping(value = "/removeAdmin")
	public ResponseEntity<Object> removeAdmin() {

		return adminService.removeAdmin();

	}

	// Controller takes request to update email for admin who is currently logged in
	// and returns response entity.
	@PutMapping(value = "/updateAdminEmail/{email}")
	public ResponseEntity<Object> updateAdminEmail(@PathVariable String email) {

		return adminService.updateAdminEmail(email);

	}

	// Controller takes request to update password for admin who is currently logged
	// in and returns response entity.
	@PutMapping(value = "/updateAdminPassord/{password}")
	public ResponseEntity<Object> updateAdminPassord(@PathVariable String password) {

		return adminService.updateAdminPassword(password);

	}

	// Controller takes request to update data for admin who is currently logged in
	// and returns response entity.
	@PutMapping(value = "/updateAdmin")
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin) {

		return adminService.updateAdmin(admin);

	}

	// Controller takes request to get details of currently logged in admin and
	// returns response entity.
	@GetMapping(value = "/getAdmin")
	public ResponseEntity<Object> getAdmin() {

		return adminService.getAdmin();

	}

	// Controller takes request to get revenue of currently logged in admin and
	// returns response entity.
	@GetMapping(value = "/getAdminRevenue")
	public ResponseEntity<Object> getAdminRevenue() {

		return adminService.getAdminRevenue();

	}

	// Controller takes request to collect revenue of currently logged in admin and
	// returns response entity.
	@GetMapping(value = "/collectAdminRevenue")
	public ResponseEntity<Object> collectAdminRevenue() {

		return adminService.collectAdminRevenue();

	}

	// Controller takes supervisor object as parameter from request body and returns
	// response entity.
	@RequestMapping(value = "/addSupervisor")
	public ResponseEntity<Object> addSupervisor(@RequestBody Supervisor supervisor) {

		return adminService.addSupervisor(supervisor);

	}

	// Controller takes request to remove all supervisors and returns response
	// entity.
	@RequestMapping(value = "/removeAllSupervisor")
	public ResponseEntity<Object> removeAllSupervisor() {

		return adminService.removeAllSupervisor();

	}

	// Controller takes request to remove supervisor with id from path variable and
	// returns response entity.
	@RequestMapping(value = "/removeBySupervisorId/{id}")
	public ResponseEntity<Object> removeBySupervisorId(@PathVariable int id) {

		return adminService.removeBySupervisorId(id);

	}

	// Controller takes request to get all supervisors and returns response entity.
	@RequestMapping(value = "/getAllSupervisor")
	public ResponseEntity<Object> getAllSupervisor() {

		return adminService.getAllSupervisor();

	}

	// Controller takes request to get supervisor with id from path variable and
	// returns response entity.
	@RequestMapping(value = "/getBySupervisorId/{id}")
	public ResponseEntity<Object> getBySupervisorId(@PathVariable int id) {

		return adminService.getBySupervisorId(id);

	}

	// Controller takes request to get sorted supervisor with name and returns
	// response entity.
	@RequestMapping(value = "/getSortedBySupervisorName")
	public ResponseEntity<Object> getSortedBySupervisorName() {

		return adminService.getSortedBySupervisorName();

	}

	// vendor controller

	// Controller takes vendor object as parameter from request body and returns
	// response entity
	@RequestMapping(value = "/addVendor")
	public ResponseEntity<Object> addVendor(@RequestBody Vendor vendor) {

		return adminService.addVendor(vendor);

	}

	// Controller takes request to remove all vendors and returns response entity.
	@RequestMapping(value = "/removeAllVendor")
	public ResponseEntity<Object> removeAllvendor() {

		return adminService.removeAllVendor();

	}

	// Controller takes request to remove a specific vendor using id and returns
	// response entity.
	@RequestMapping(value = "/removeByVendorId/{id}")
	public ResponseEntity<Object> removeByVendorId(@PathVariable int id) {

		return adminService.removeByVendorId(id);

	}

	// Controller takes request to fetch all vendors and returns response entity.
	@RequestMapping(value = "/getAllVendor")
	public ResponseEntity<Object> getAllVendor() {

		return adminService.getAllVendor();

	}

	// Controller takes request to update a specific vendor's contact details
	// using id and returns response entity.
	@RequestMapping(value = "/updateVendorByContact/{id}/{contact}")
	public ResponseEntity<Object> updateVendorByContact(@PathVariable int id, @PathVariable String contact) {

		return adminService.updateVendorByContact(id, contact);

	}

	// Controller takes request to update a specific vendor's service details
	// using id and returns response entity.
	@RequestMapping(value = "/updateVendorByServices/{id}/{flower}/{music}/{catering}/{video}")
	public ResponseEntity<Object> updateVendorByServices(@PathVariable int id, @PathVariable boolean flower,
			@PathVariable boolean music, @PathVariable boolean catering, @PathVariable boolean video) {

		return adminService.updateVendorByServices(id, flower, music, catering, video);

	}

	// Controller takes request to fetch a vendor detail
	// using id and returns response entity.
	@RequestMapping(value = "/getByVendorId/{id}")
	public ResponseEntity<Object> getByVendorId(@PathVariable int id) {

		return adminService.getByVendorId(id);

	}

	@RequestMapping(value = "/getAllCustomers")
	public ResponseEntity<Object> getAllCustomers() {

		return adminService.getAllCustomers();

	}

	@RequestMapping(value = "/getCustomersBetween")
	public ResponseEntity<Object> getCustomersBetween(@RequestBody Date from, @RequestBody Date to) {

		return adminService.getCustomersBetween(from, to);

	}

	@RequestMapping(value = "/removeCustomerById/{id}")
	public ResponseEntity<Object> removeCustomerById(@PathVariable int id) {

		return adminService.removeCustomerById(id);

	}

}
