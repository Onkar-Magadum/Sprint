package com.cg.test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminTest {

    // @Autowired
    // AdminController adminController;
    //
    // @Test
    // final void testAddAdminFirstName1() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,First name should not contain
    // digit.,[]>", adminController.addAdmin(new Admin(101, "First1", "Last",
    // "email@gmail.com", "9000000000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminLastName1() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Last name should not contain
    // digit.,[]>", adminController.addAdmin(new Admin(101, "First", "Last1",
    // "email@gmail.com", "9000000000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminFirstName2() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,First name is not valid.,[]>",
    // adminController.addAdmin(new Admin(101, "First!@", "Last", "email@gmail.com",
    // "9000000000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminLastName2() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Last name is not valid.,[]>",
    // adminController.addAdmin(new Admin(101, "First", "Last!@", "email@gmail.com",
    // "9000000000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminContactNumber1() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Contact number is not valid, must
    // be of 10 digits.,[]>", adminController.addAdmin(new Admin(101, "First",
    // "Last", "email@gmail.com", "90000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminContactNumber2() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Contact number is not valid, must
    // be numeric.,[]>", adminController.addAdmin(new Admin(101, "First", "Last",
    // "email@gmail.com", "90000000**", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword1() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password should be minimum 8
    // length.,[]>", adminController.addAdmin(new Admin(101, "First", "Last",
    // "email@gmail.com", "9000000000", "pass")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword2() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password must contain at least one
    // uppercase character.,[]>", adminController.addAdmin(new Admin(101, "First",
    // "Last", "email@gmail.com", "9000000000", "password")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword3() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password must contain at least one
    // lowercase character.,[]>", adminController.addAdmin(new Admin(101, "First",
    // "Last", "email@gmail.com", "9000000000", "PASSWORD")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword4() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password must contain at least one
    // digit.,[]>", adminController.addAdmin(new Admin(101, "First", "Last",
    // "email@gmail.com", "9000000000", "PASSword")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword5() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password must contain at least one
    // special character from !,@,#,$,%,*.,[]>", adminController.addAdmin(new
    // Admin(101, "First", "Last", "email@gmail.com", "9000000000",
    // "Password123")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdminPassword6() {
    //
    // assertEquals("<400 BAD_REQUEST Bad Request,Password does not match the
    // policy, "
    // + "it should contain at least one uppercase character, lowercase character,
    // digit and special character.,[]>",
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123<>")).toString());
    //
    // }
    //
    // @Test
    // final void testAddAdmin() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,Admin added successfully.,[]>",
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123")).toString());
    //
    // }
    //
    // @Test
    // final void testRemoveAllAdmin1() {
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // assertEquals("All admin deleted successfully.",
    // adminController.removeAllAdmin());
    //
    // }
    //
    //
    // @Test
    // final void testRemoveAllAdmin2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("Admin not found.", adminController.removeAllAdmin());
    //
    // }
    //
    // @Test
    // final void testRemoveAdminById1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(10, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // assertEquals("Admin deleted successfully.",
    // adminController.removeByAdminId(10));
    //
    // }
    //
    // @Test
    // final void testRemoveAdminById2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("Admin not found.", adminController.removeByAdminId(101));
    //
    // }
    //
    // @Test
    // final void testGetAllAdmin1() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,Admin not found.,[]>",
    // adminController.getAllAdmin().toString());
    //
    // }
    //
    // @Test
    // final void testGetAllAdmin2() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // ResponseEntity<Object> admins = adminController.getAllAdmin();
    //
    // assertNotNull(admins);
    //
    // }
    //
    // @Test
    // final void testGetAdminByAdminId1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // ResponseEntity<Object> admin = adminController.getByAdminId(101);
    //
    // assertNotNull(admin);
    //
    // }
    //
    // @Test
    // final void testGetAdminByAdminId2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,Optional.empty,[]>",
    // adminController.getByAdminId(101).toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminByFirstName1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // assertEquals("<200 OK OK,[Admin [First_Name=First, Last_Name=Last,
    // Email=email@gmail.com, Contact_Number=9000000000,
    // Password=Password@123]],[]>",
    // adminController.getByAdminFirstName("First").toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminByFirstName2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,[],[]>",
    // adminController.getByAdminFirstName("FirstNew").toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminByLastName1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // ResponseEntity<Object> admin = adminController.getByAdminLastName("Last");
    //
    // assertNotNull(admin);
    //
    // }
    //
    // @Test
    // final void testGetAdminByLastName2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,[],[]>",
    // adminController.getByAdminLastName("LastNew").toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminByContactNumber1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // ResponseEntity<Object> admin =
    // adminController.getByAdminContact("9000000000");
    //
    // assertNotNull(admin);
    //
    // }
    //
    // @Test
    // final void testGetAdminByContactNumber2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,Admin not found.,[]>",
    // adminController.getByAdminContact("8000000000").toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminByEmail1() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin(101, "First", "Last", "email@gmail.com",
    // "9000000000", "Password@123"));
    //
    // ResponseEntity<Object> admin =
    // adminController.getByAdminEmail("email@gmail.com");
    //
    // assertNotNull(admin);
    //
    // }
    //
    // @Test
    // final void testGetAdminByEmail2() {
    //
    // adminController.removeAllAdmin();
    //
    // assertEquals("<200 OK OK,Admin not found.,[]>",
    // adminController.getByAdminEmail("emailnew@gmail.com").toString());
    //
    // }
    //
    // @Test
    // final void testGetAdminSortedByFirstName() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin("Onkar", "Magadum", "email1@gmail.com",
    // "9000000000", "Password@123"));
    // adminController.addAdmin(new Admin("Abc", "Xyz", "email2@gmail.com",
    // "8000000000", "Password@123"));
    // adminController.addAdmin(new Admin("Mno", "Pqr", "email3@gmail.com",
    // "7000000000", "Password@123"));
    // adminController.addAdmin(new Admin("First", "Last", "email4@gmail.com",
    // "6000000000", "Password@123"));
    //
    //
    // assertEquals("<200 OK OK,["
    // + "Admin [First_Name=Abc, Last_Name=Xyz, Email=email2@gmail.com,
    // Contact_Number=8000000000, Password=Password@123], "
    // + "Admin [First_Name=First, Last_Name=Last, Email=email4@gmail.com,
    // Contact_Number=6000000000, Password=Password@123], "
    // + "Admin [First_Name=Mno, Last_Name=Pqr, Email=email3@gmail.com,
    // Contact_Number=7000000000, Password=Password@123], "
    // + "Admin [First_Name=Onkar, Last_Name=Magadum, Email=email1@gmail.com,
    // Contact_Number=9000000000, Password=Password@123]],"
    // + "[]>",
    // adminController.getSortedByAdminFirstName().toString());
    // }
    //
    // @Test
    // final void testGetAdminSortedByLastName() {
    //
    // adminController.removeAllAdmin();
    //
    // adminController.addAdmin(new Admin("Onkar", "Magadum", "email1@gmail.com",
    // "9000000000", "Password@123"));
    // adminController.addAdmin(new Admin("Abc", "Xyz", "email2@gmail.com",
    // "8000000000", "Password@123"));
    // adminController.addAdmin(new Admin("Mno", "Pqr", "email3@gmail.com",
    // "7000000000", "Password@123"));
    // adminController.addAdmin(new Admin("First", "Last", "email4@gmail.com",
    // "6000000000", "Password@123"));
    //
    // assertEquals("<200 OK OK,["
    // + "Admin [First_Name=First, Last_Name=Last, Email=email4@gmail.com,
    // Contact_Number=6000000000, Password=Password@123], "
    // + "Admin [First_Name=Onkar, Last_Name=Magadum, Email=email1@gmail.com,
    // Contact_Number=9000000000, Password=Password@123], "
    // + "Admin [First_Name=Mno, Last_Name=Pqr, Email=email3@gmail.com,
    // Contact_Number=7000000000, Password=Password@123], "
    // + "Admin [First_Name=Abc, Last_Name=Xyz, Email=email2@gmail.com,
    // Contact_Number=8000000000, Password=Password@123]],"
    // + "[]>",
    // adminController.getSortedByAdminLastName().toString());
    // }

}
