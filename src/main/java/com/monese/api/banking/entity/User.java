package com.monese.api.banking.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author C.SajjadHussain
 *
 */
@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements AbstractEntity {

	private static final long serialVersionUID = -6548505005550734588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name = "current_balance")
	@Builder.Default
	private Double currentBalance= Double.valueOf(0);

	@Column(name = "account_number")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String accountNumber;

	@Column(name = "email")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String email;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "first_name")
	private String firstName;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "last_name")
	private String lastName;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "current_location")
	private String currentLocation;

	@Builder.Default
	@JsonIgnore
	private Boolean status = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@CreationTimestamp
	@JsonIgnore
	private Date createdAt;

	@Column(name = "created_by")
	@JsonIgnore
	@Builder.Default
	private BigInteger createdBy = BigInteger.valueOf(1);

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@UpdateTimestamp
	@JsonIgnore
	private Date updatedAt;

	@Column(name = "updated_by")
	@JsonIgnore
	@Builder.Default
	private BigInteger updatedBy = BigInteger.valueOf(1);

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public BigInteger getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigInteger updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	
	
	
}