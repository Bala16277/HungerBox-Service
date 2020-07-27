package com.hcl.hungerbox.dto;


public class AccountDto {

	private Integer accountId;
	
	private int userId;
	
	public int acountNumber;
	
	private int availableBallance;
	
	private int phoneNumber;

	private String accountName;

	private String accountType;

	private int balance;

	
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(int acountNumber) {
		this.acountNumber = acountNumber;
	}

	public int getAvailableBallance() {
		return availableBallance;
	}

	
	

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAvailableBallance(int availableBallance) {
		this.availableBallance = availableBallance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
}
