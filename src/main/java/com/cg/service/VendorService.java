package com.cg.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.entity.Vendor;
import com.cg.repository.HallRepository;
import com.cg.repository.VendorRepository;
import com.cg.entity.Hall;

//Vendor Service Class 

@Service
public class VendorService {

	@Autowired // auto-wiring vendor and hall repository.
	private VendorRepository vendorRepo;

	@Autowired
	private HallRepository hallRepository;

	// method to book a vendor by customer

	public boolean bookVendor(int hallId, Date from, Date to, boolean flower, boolean catering, boolean music,
			boolean video) {

		List<Vendor> vendors = vendorRepo.findByServices(flower, catering, music, video); // fetching vendors from
																							// repository
																							// according to mentioned
																							// services.
		if (!vendors.isEmpty()) {
			for (Vendor vendor : vendors) {

				Hall hall = hallRepository.findById(hallId).get();

				if (hall != null && (vendor.getIsVendorAvailable() || vendor.getBookVendorFrom().after(to)
						|| vendor.getBookVendorTo().before(from))) {

					vendor.setIsVendorAvailable(false); // making vendor status false -> booked

					hall.getVendors().add(vendor);
					hallRepository.save(hall);

					vendor.setBookVendorFrom(from);
					vendor.setBookVendorTo(to);
					vendor.setHall(hall);

					vendorRepo.save(vendor);

					return true;

				}

			}
			return false;
		}
		return false;
	}

}
