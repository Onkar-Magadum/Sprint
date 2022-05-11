package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Hall;

//Hall repository
public interface HallRepository extends JpaRepository<Hall, Integer> {

	//Method returns List of Halls for a given city
	public List<Hall> findByHallCity(String city);

	//Method returns list of Halls for a provided location at a particular city
	public List<Hall> findByHallCityAndHallLocation(String city, String location);

	//Method returns List of Hall for a given city according to its capacity
	public List<Hall> findByHallCityAndHallCapacity(String city, int capacity);

}
