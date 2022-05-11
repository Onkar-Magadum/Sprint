package com.cg.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.entity.Vendor;
import com.cg.exception.HallNotFoundException;
import com.cg.entity.Hall;
import com.cg.entity.Supervisor;
import com.cg.repository.HallRepository;
import com.cg.repository.SupervisorRepository;
import com.cg.repository.VendorRepository;

@Service
public class SupervisorService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private HallRepository hallRepository;

	@Autowired
	private SupervisorRepository supervisorRepository;

	static Logger log = Logger.getLogger(SupervisorService.class.getName());

	public ResponseEntity<Object> addHall(int id, Hall hall) {

		supervisorRepository.getById(id).setHall(hall);

		log.info("Supervisor with ID, " + id + " added hall, with ID, " + hall.getHallId());

		return new ResponseEntity<>("Hall added successfully.", HttpStatus.OK);

	}

	public ResponseEntity<Object> removeHall(int id) {

		if (supervisorRepository.existsById(id)) {

			Supervisor supervisor = supervisorRepository.getById(id);

			Hall hall = supervisor.getHall();

			hallRepository.deleteById(hall.getHallId());
			supervisor.setHall(null);
			supervisorRepository.save(supervisor);

			log.info("Supervisor deleted hall.");

			return new ResponseEntity<>("Hall deleted successfully.", HttpStatus.OK);
		}

		log.error("No hall found.");

		throw new HallNotFoundException();
	}

	public ResponseEntity<Object> getSupervisorHallDetails(int id) {

		Hall hall = supervisorRepository.findById(id).get().getHall();

		if (hall == null) {

			log.error("Supervisor tried to access hall, but no hall assigned.");

			throw new HallNotFoundException();
		}

		log.info("Supervisor with ID, " + id + " accessed hall, with ID, " + hall.getHallId());

		return new ResponseEntity<>(hall, HttpStatus.OK);

	}

	public double generateBill(int hallId, boolean flower, boolean catering, boolean music, boolean video) {

		double billAmount = 0.0;

		Hall hall = hallRepository.getById(hallId);

		billAmount += hall.getHallPrice();

		List<Vendor> vendors = vendorRepository.findByServices(flower, catering, music, video);
		billAmount += vendors.get(0).getVendorCost();

		billAmount *= 1.18;

		log.info("Hall with ID, " + hallId + ", generated bill: " + billAmount);

		return billAmount;

	}
}
