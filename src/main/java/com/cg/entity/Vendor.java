package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

//Vendor Class

@Entity
@Table(name = "Vendor")    //table with name Vendor will get stored in the database.
public class Vendor {

	@Id                                                    // to make vendorId as primary key of the table Vendor.
	@GeneratedValue(strategy = GenerationType.AUTO)        // to generate its value automatically on its own.
	@Column(name = "vendorId", nullable = false)           // defining a column for the table and adding a not null constraint. 
	private int vendorId;

	@Column(name = "firstName" , nullable =false)
	@Pattern(regexp = "^[A-Za-z]+$", message = "First name is invalid, it must contain only alphabets.") // check if f_name is valid
    private String vendorFirstName;

	@Column(name = "lastName" , nullable=false)
	@Pattern(regexp = "^[A-Za-z]+$", message = "Last name is invalid, it must contain only alphabets.") // check if l_name is valid
    private String vendorLastName;
    	
	@Pattern(regexp = "[0-9]{10}", message = "Contact number is invalid, must contain only 10 digits.") // check if contact is valid
    @Column(name = "vendorContact" , nullable=false)
	private String vendorContact;

	@Column(name = "flowerVendor")       // All 4 vendor services defined as boolean whether providing a particular service or not.
	private boolean flower;              

	@Column(name = "musicVendor")
	private boolean music;

	@Column(name = "cateringVendor")
	private boolean catering;

	@Column(name = "videoVendor")
	private boolean video;

	@Column(name = "status")
	private boolean isVendorAvailable;  // to check whether vendor is available for booking or not.

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)   
	private Hall hall;                 
  
	@ManyToOne                     // an admin can add multiple vendors .
	@JoinColumn(name = "adminId")  // to add adminId as foreign key in Vendor table. 
	private Admin admin;

	private Date bookVendorFrom;   // starting date from which the vendor is booked

	private Date bookVendorTo;     // ending date upto which the vendor is booked

	//default constructor
	public Vendor() {
		super();
	}
	
    //parameterized constructor
	public Vendor(int vendorId, String vendorFirstName, String vendorLastName, String vendorContact, boolean flower,
			boolean music,
			boolean catering, boolean video, boolean isVendorAvailable) {
		super();
		this.vendorId = vendorId;
		this.vendorFirstName = vendorFirstName;
		this.vendorLastName = vendorLastName;
		this.vendorContact = vendorContact;
		this.flower = flower;
		this.music = music;
		this.catering = catering;
		this.video = video;
		this.isVendorAvailable = isVendorAvailable;

	}

	//getters and setters
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorFirstName() {
		return vendorFirstName;
	}

	public void setVendorFirstName(String firstName) {
		this.vendorFirstName = firstName;
	}

	public String getVendorLastName() {
		return vendorLastName;
	}

	public void setVendorLastName(String lastName) {
		this.vendorLastName = lastName;
	}

	public String getVendorContact() {
		return vendorContact;
	}

	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}

	public boolean isFlower() {
		return flower;
	}

	public void setFlower(boolean flower) {
		this.flower = flower;
	}

	public boolean isMusic() {
		return music;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}

	public boolean isCatering() {
		return catering;
	}

	public void setCatering(boolean catering) {
		this.catering = catering;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public boolean getIsVendorAvailable() {
		return isVendorAvailable;
	}

	public void setIsVendorAvailable(boolean isVendorAvailable) {
		this.isVendorAvailable = isVendorAvailable;
	}

	//this method generates the cost of vendor services required.
	public double getVendorCost() {

		double vendorCost = 0;

		if (flower)
			vendorCost += 3000;
		if (music)
			vendorCost += 4000;
		if (catering)
			vendorCost += 10000;
		if (video)
			vendorCost += 6000;

		return vendorCost;
	}
	
    //to fetch the hall details.
	public Hall getHall() {
		return hall;
	}

	// to assign a hall with vendor to provide the services.
	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Date getBookVendorFrom() {
		return bookVendorFrom;
	}

	public void setBookVendorFrom(Date bookVendorFrom) {
		this.bookVendorFrom = bookVendorFrom;
	}

	public Date getBookVendorTo() {
		return bookVendorTo;
	}

	public void setBookVendorTo(Date bookVendorTo) {
		this.bookVendorTo = bookVendorTo;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	// to string method.
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorFirstName=" + vendorFirstName + ", vendorLastName="
				+ vendorLastName + ", vendorContact=" + vendorContact + ", flower=" + flower + ", music=" + music
				+ ", catering=" + catering + ", video=" + video + ", isVendorAvailable=" + isVendorAvailable
				+ ", hall=" + hall + ", admin=" + admin + ", bookVendorFrom="
				+ bookVendorFrom + ", bookVendorTo=" + bookVendorTo + "]";
	}

}
