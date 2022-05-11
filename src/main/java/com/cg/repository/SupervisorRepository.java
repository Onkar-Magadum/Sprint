package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

	List<Supervisor> findBySupervisorName(String name);

	List<Supervisor> findBySupervisorContact(String contact);

	Supervisor findBySupervisorEmail(String email);

}
