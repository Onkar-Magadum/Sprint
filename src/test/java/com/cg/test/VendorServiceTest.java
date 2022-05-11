package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;
import com.cg.entity.Vendor;
import com.cg.entity.Admin;
import com.cg.repository.VendorRepository;
import com.cg.MhbaApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.service.VendorService;
import com.cg.service.AdminService;
import com.cg.repository.AdminRepository;
import static org.mockito.Mockito.when;

class VendorServiceTest extends MhbaApplicationTests {

    @MockBean
    VendorRepository vendorRepository;

    @MockBean
    AdminRepository adminRepository;

    @Autowired
    VendorService vendorService;

    @Autowired
    AdminService adminService;

    Vendor vendor;
    Admin admin;

    @BeforeEach
    void setUp() {
        vendor = new Vendor(501, "Bhavya", "Aggarwal", "9999999998", true, true, true, true, true);
        admin = new Admin(101, "Onkar", "Magadum", "onkarmagadum@gmail.com", 31, "9000000000", "Abc@123");

    }

    @Test
    final void testAddVendor() {

        when(adminRepository.findByAdminEmailAndAdminPassword("onkarmagadum@gmail.com", "Abc@123")).thenReturn(admin);

        when(vendorRepository.findByVendorContact("9999999998")).thenReturn(vendor);

        adminService.loginAdmin("onkarmagadum@gmail.com", "Abc@123");

        assertEquals("<200 OK OK,Vendor with this contact already exists.,[]>",
                adminService.addVendor(vendor).toString());

    }

}