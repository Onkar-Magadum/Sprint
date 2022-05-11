package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hallOffers")
public class HallOffers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hallOfferId")
	private int hallOfferId;

	@Column(name = "serviceType")
	private String serviceType;

	@Column(name = "serviceDetails")
	private String serviceDetails;

	@Column(name = "available")
	private boolean isAvailable;

	public HallOffers() {
		super();
	}

	public HallOffers(int hallOfferId, String serviceType, String serviceDetails, boolean isAvailable) {
		super();
		this.hallOfferId = hallOfferId;
		this.serviceType = serviceType;
		this.serviceDetails = serviceDetails;
		this.isAvailable = isAvailable;
	}

	public int getHallOfferId() {
		return hallOfferId;
	}

	public void setHallOfferId(int id) {
		this.hallOfferId = id;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "HallOffers [id=" + hallOfferId + ", serviceType=" + serviceType + ", serviceDetails=" + serviceDetails
				+ ", isAvailable=" + isAvailable + "]";
	}

}
