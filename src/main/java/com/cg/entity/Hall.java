package com.cg.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

//Hall Class
@Entity
public class Hall {
	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hallId")
	private int hallId;

	@Column(name = "hallName", nullable = false)
	@Pattern(regexp = "^[A-Za-z ]*$", message = "Name is invalid, must contain alphabets only.")
	private String hallName;

	@Column(name = "noOfRooms")
	private int noOfRooms;

	@Column(name = "capacity")
	private long hallCapacity;

	@Column(name = "location")
	private String hallLocation;

	@Column(name = "city")
	private String hallCity;

	@Column(name = "price")
	private double hallPrice;

	@Column(name = "bookedFrom")
	private Date hallBookedFrom;

	@Column(name = "bookedTo")
	private Date hallBookedTo;

	@Column(name = "bookingStatus")
	private boolean hallBookingStatus = false;
	@Min(value = 0)
	@Column(name = "hallRevenue")
	private double hallRevenue;

	// many to many relationship with vendor
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "halls_vendors", joinColumns = @JoinColumn(name = "hallId", referencedColumnName = "hallId"), inverseJoinColumns = @JoinColumn(name = "vendorId", referencedColumnName = "vendorId"))
	private List<Vendor> vendors;

	// HallOffers list
	@OneToMany(cascade = CascadeType.ALL)
	private List<HallOffers> hallOffers;

	// Default Constructor
	public Hall() {
		super();
	}

	// Parameterized constructor for Hall
	public Hall(int hallId, String hallName, @Min(5) int noOfRooms, @Min(100) long capacity, String location,
			String city, @Min(5000) double hallPrice, boolean bookingStatus, List<Vendor> vendors,
			List<HallOffers> hallOffers) {
		super();
		this.hallId = hallId;
		this.hallName = hallName;
		this.noOfRooms = noOfRooms;
		this.hallCapacity = capacity;
		this.hallLocation = location;
		this.hallCity = city;
		this.hallPrice = hallPrice;
		this.hallBookingStatus = bookingStatus;
		this.vendors = vendors;
		this.hallOffers = hallOffers;
	}

	// getters and setters
	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public long getHallCapacity() {
		return hallCapacity;
	}

	public void setHallCapacity(long capacity) {
		this.hallCapacity = capacity;
	}

	public String getHallLocation() {
		return hallLocation;
	}

	public void setHallLocation(String location) {
		this.hallLocation = location;
	}

	public String getHallCity() {
		return hallCity;
	}

	public void setHallCity(String city) {
		this.hallCity = city;
	}

	public double getHallPrice() {
		return hallPrice;
	}

	public void setPrice(double hallPrice) {
		this.hallPrice = hallPrice;
	}

	public Date getHallBookedFrom() {
		return hallBookedFrom;
	}

	public void setHallBookedFrom(Date hallBookedFrom) {
		this.hallBookedFrom = hallBookedFrom;
	}

	public Date getHallBookedTo() {
		return hallBookedTo;
	}

	public void setHallBookedTo(Date hallBookedTo) {
		this.hallBookedTo = hallBookedTo;
	}

	// function to check whether hall is booked or not
	public boolean isHallBookingStatus() {
		return hallBookingStatus;
	}

	public void setHallBookingStatus(boolean hallBookingStatus) {
		this.hallBookingStatus = hallBookingStatus;
	}

	public List<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public List<HallOffers> getHallOffers() {
		return hallOffers;
	}

	public void setHallOffers(List<HallOffers> hallOffers) {
		this.hallOffers = hallOffers;
	}

	public double getHallRevenue() {
		return hallRevenue;
	}

	public void setHallRevenue(double hallRevenue) {
		this.hallRevenue = hallRevenue;
	}

	public void addRevenue(double cost) {

		this.hallRevenue += cost;

	}

	@Override
	public String toString() {
		return "Hall [hallId=" + hallId + ", hallName=" + hallName + ", noOfRooms=" + noOfRooms + ", hallCapacity="
				+ hallCapacity + ", hallLocation=" + hallLocation + ", hallCity=" + hallCity + ", hallPrice="
				+ hallPrice + ", hallBookedFrom=" + hallBookedFrom + ", hallBookedTo=" + hallBookedTo
				+ ", hallBookingStatus=" + hallBookingStatus + ", hallRevenue=" + hallRevenue + ", vendor=" + vendors
				+ ", hallOffers=" + hallOffers + "]";
	}

}
