package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.entity.Admin;
import com.cg.entity.Hall;
import com.cg.entity.Supervisor;
import com.cg.repository.SupervisorRepository;
import com.cg.service.AdminService;
import com.cg.service.SupervisorService;

class SupervisorControllerTest extends MhbaApplicationTests {
	@Autowired
	SupervisorService supervisorService;
	
	@Autowired
	AdminService adminService;
	
	@MockBean
	SupervisorRepository supervisorRepository;
	
	Admin admin;
	Hall hall;
	Supervisor supervisor;
	double amount;

	@BeforeEach
	void setUp() {
		admin = new Admin(101, "onkar", "M", "onkar@gmail.com", 31, "893821128312", "qazwsx");
		hall = new Hall(101, "palace", 40, 500, "civil line", "city", 70000, false, null, null);
		supervisor = new Supervisor(101, "Surya", "surya@gmail.com", "90909022339",hall);
		adminService.addAdmin(admin);
		adminService.addSupervisor(supervisor);
	}
	
	@Test
	void addHallTest() {
		assertEquals("Hall added successfully.", supervisorService.addHall(101, hall).getBody().toString());
	}

	@Test
	void removeHallTest() {
	 	assertEquals("Hall removed successfully.", supervisorService.removeHall(101).getBody().toString());
	}

	@Test
	void getSupervisorHallDetailsTest() {
		 when(supervisorRepository.getById(101).getHall()).thenReturn(hall);
		 assertEquals(hall.toString(), supervisorService.getSupervisorHallDetails(101).getBody().toString());
	}

	@Test
	void generateBillTest() {
		 when(supervisorService.generateBill(101, false, false, false, false)).thenReturn(amount);
		 assertEquals(amount, supervisorService.generateBill(101, false, false, false, false));
	}
	
 }
