//Vendor Repository Class
package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.*;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	List<Vendor> findByVendorFirstName(String firstName); // to find vendors using their first name.

	List<Vendor> findByVendorLastName(String lastName); // to find vendors using their last name.

	Vendor findByVendorContact(String adminContact); // to find vendors using their contact.

	// Query returns list of vendors with vendor services available equals to
	// services required from parameter
	@Query("select vendor from Vendor vendor where vendor.flower=:flower and vendor.catering=:catering and vendor.music=:music and vendor.video=:video")
	List<Vendor> findByServices(
			@Param("flower") boolean flower,
			@Param("catering") boolean catering,
			@Param("music") boolean music,
			@Param("video") boolean video);

}
