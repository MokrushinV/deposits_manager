package com.deposits.entities;

import java.sql.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "deposit_table")
public class DepositEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	/*
	 * experimenting with mapping
	 * trying to make hibernate to refer
	 * to a field instead of a table 
	 * 
	 * */
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private ClientEntity client;
	
	@ManyToOne()
	@JoinColumn(name = "bank_id")
	private BankEntity bank;
	
	@Column(name = "deposit_open_date")
	private Date openDate;
	
	@Column(name = "deposit_interest_percent")
	private double interestRate;
	
	@Column(name = "deposit_active_months")
	private long monthsSinceOpen;

	public DepositEntity (ClientEntity client, BankEntity bank, String openDate, double interestRate, long monthSinceOpen) {
		this.client = client;
		this.bank = bank;
		this.openDate = Date.valueOf(openDate);
		this.interestRate = interestRate;
		this.monthsSinceOpen = monthSinceOpen;
	}
	
	protected DepositEntity () {
		
	}
	
	@JsonBackReference (value = "client_reference")
	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	@JsonBackReference (value = "bank_reference")
	public BankEntity getBank() {
		return bank;
	}

	public void setBank(BankEntity bank) {
		this.bank = bank;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public long getMonthsSinceOpen() {
		return monthsSinceOpen;
	}

	public void setMonthsSinceOpen(long monthsSinceOpen) {
		this.monthsSinceOpen = monthsSinceOpen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DepositEntity {id=" + id + 
							  ", client=" + client.getName() + 
							  ", bank=" + bank.getBankName() + 
							  ", openDate=" + openDate + 
							  ", interestRate=" + interestRate + 
							  ", monthsSinceOpen=" + monthsSinceOpen + "}";
	}
	

}
