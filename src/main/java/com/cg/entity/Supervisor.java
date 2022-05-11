package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "supervisor")
public class Supervisor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int supervisorId;

	@NotBlank
	@Column(name = "supervisorName")
	private String supervisorName;

	@Email
	@Column(name = "supervisorEmail")
	private String supervisorEmail;

	@NotBlank
	@Column(name = "supervisorContact")
	private String supervisorContact;

	@ManyToOne
	@JoinColumn(name = "adminId")
	private Admin admin;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hall hall;

	public Supervisor() {
		super();
	}

	public Supervisor(int supervisorId, String supervisorName, String supervisorEmail, String supervisorContact,
			Hall hall) {
		super();
		this.supervisorId = supervisorId;
		this.supervisorName = supervisorName;
		this.supervisorEmail = supervisorEmail;
		this.supervisorContact = supervisorContact;
		this.hall = hall;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getSupervisorEmail() {
		return supervisorEmail;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	public String getSupervisorContact() {
		return supervisorContact;
	}

	public void setSupervisorContact(String supervisorContact) {
		this.supervisorContact = supervisorContact;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Supervisor [supervisorId=" + supervisorId + ", supervisorName=" + supervisorName + ", supervisorEmail="
				+ supervisorEmail + ", supervisorContact=" + supervisorContact + ", admin=" + admin + ", hall=" + hall
				+ "]";
	}

}
