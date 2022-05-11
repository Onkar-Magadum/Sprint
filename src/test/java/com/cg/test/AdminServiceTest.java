package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.cg.entity.Admin;
import com.cg.entity.Hall;
import com.cg.entity.Supervisor;
import com.cg.entity.Vendor;
import com.cg.repository.AdminRepository;
import com.cg.service.AdminService;
import com.cg.MhbaApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class AdminServiceTest extends MhbaApplication {

	@MockBean
	AdminRepository adminRepository;

	@Autowired
	AdminService adminService;

	Admin admin;
	Supervisor supervisor;
	Vendor vendor;
	Hall hall;

	@BeforeEach
	void setUp() {

		admin = new Admin(101, "Onkar", "Magadum", "onkarmagadum@gmail.com", 31, "9000000000", "Abc@123");

	}

	@Test
	final void testLoginAdmin() {

		when(adminRepository.findByAdminEmailAndAdminPassword("onkarmagadum@gmail.com",
				"Abc@123"))
				.thenReturn(admin);

		assertEquals("<200 OK OK,Admin login successfull.,[]>",
				adminService.loginAdmin("onkarmagadum@gmail.com", "Abc@123").toString());

	}

	@Test
	final void testLogoutAdmin() {

		when(adminRepository.findByAdminEmailAndAdminPassword("onkarmagadum@gmail.com",
				"Abc@123"))
				.thenReturn(admin);

		adminService.loginAdmin("onkarmagadum@gmail.com", "Abc@123");

		assertEquals("<200 OK OK,Admin logout successfull.,[]>",
				adminService.logoutAdmin().toString());

	}

	// @Test
	final void testAddAdmin() {

		adminRepository.save(admin);

		assertEquals("<200 OK OK,Admin added successfully.,[]>",
				adminService.addAdmin(admin).toString());

	}

	// @Test
	final void testGetAdmin() {

		when(adminRepository.findByAdminEmailAndAdminPassword("onkarmagadum@gmail.com", "Abc@123")).thenReturn(admin);

		adminService.loginAdmin("onkarmagadum@gmail.com", "Abc@123");

		assertEquals("<200 OK OK," + admin + ",[]>",
				adminService.getAdmin().toString());

	}

	// @Test
	final void testGetAdminRevenue() {
		when(adminRepository.findByAdminEmailAndAdminPassword("onkarmagadum@gmail.com",
				"Abc@123"))
				.thenReturn(admin);

		adminService.loginAdmin("onkarmagadum@gmail.com", "Abc@123");

		assertEquals("<200 OK OK,Revenue for admin with ID: 101 is:0.0,[]>",
				adminService.getAdminRevenue().toString());

	}

	// @Test
	final void testCollectAdminRevenue() {

		assertEquals("<200 OK OK,Admin revenue collected.,[]>",
				adminService.collectAdminRevenue().toString());

	}

}
