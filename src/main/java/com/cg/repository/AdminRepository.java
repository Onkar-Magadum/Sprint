package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Admin;

//Admin repository
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	//Query returns admin with adminId equals to id from parameter
	@Query("select admin from Admin admin where admin.adminId=?1")
	Admin getAdminTotalRevenue(int id);

	//Method returns admin with email and password equals to that of given from parameters
	Admin findByAdminEmailAndAdminPassword(String email, String password);

}
