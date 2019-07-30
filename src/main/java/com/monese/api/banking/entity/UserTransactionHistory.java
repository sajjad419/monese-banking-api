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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author C.SajjadHussain
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user_transaction_history")
public class UserTransactionHistory implements AbstractEntity {

	private static final long serialVersionUID = 7273677518576967400L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private BigInteger transactionId;

	@Column(name = "transaction_type")
	@NotNull
	private String transactionType;

	@Column(name = "reference_account")
	@NotNull
	private String referenceAccount;

	@Column(name = "transaction_amount")
	private Double transactionAmount;

	@Column(name = "transaction_status")
	private String transactionStatus;
	
	@Column(name = "current_balance")
	@Builder.Default
	private Double currentBalance = Double.valueOf(0);

	

	@Column(name = "user_id")
	@JsonIgnore
	private BigInteger userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@CreationTimestamp
	@JsonIgnore
	private Date createdAt;

	@Column(name = "created_by")
	@JsonIgnore
	private BigInteger createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@UpdateTimestamp
	@JsonIgnore
	private Date updatedAt;

	@Column(name = "updated_by")
	@JsonIgnore
	private BigInteger updatedBy;

	



	public BigInteger getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getReferenceAccount() {
		return referenceAccount;
	}

	public void setReferenceAccount(String referenceAccount) {
		this.referenceAccount = referenceAccount;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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