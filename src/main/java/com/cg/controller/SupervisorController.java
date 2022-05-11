package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Hall;
import com.cg.service.SupervisorService;

@RestController
public class SupervisorController {

	@Autowired
	private SupervisorService supervisorService;

	@RequestMapping(value = "/addHall")
	public ResponseEntity<Object> addHall(@RequestBody int id, @RequestBody Hall hall) {
		return supervisorService.addHall(id, hall);
	}

	@RequestMapping(value = "/removeHall/{id}")
	public ResponseEntity<Object> removeHall(@PathVariable int id) {
		return supervisorService.removeHall(id);
	}

	@RequestMapping(value = "/getSupervisorHallDetails/{id}")
	public ResponseEntity<Object> getSupervisorHallDetails(@PathVariable int id) {
		return supervisorService.getSupervisorHallDetails(id);
	}

}
