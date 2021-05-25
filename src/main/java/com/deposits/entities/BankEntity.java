package com.deposits.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "bank_table")
public class BankEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_BIC")
	private String bankBIC;
	
	@OneToMany(mappedBy = "bank", targetEntity = DepositEntity.class, cascade = CascadeType.ALL,
			   fetch = FetchType.EAGER, orphanRemoval = true)
	//@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private List <DepositEntity> deposits = new ArrayList <DepositEntity>();
	
	public BankEntity (String bankName, String bankBIC) {
		this.bankName = bankName;
		this.bankBIC = bankBIC;
	}
	
	protected BankEntity () {
		
	}

	public String getBankName () {
		return bankName;
	}

	public void setBankName (String bankName) {
		this.bankName = bankName;
	}

	public String getBankBIC () {
		return bankBIC;
	}

	public void setBankBIC (String bankBIC) {
		this.bankBIC = bankBIC;
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public List <DepositEntity> getDeposits () {
		return deposits;
	}

	public void setDeposits (List <DepositEntity> deposits) {
		this.deposits = deposits;
	}

	@Override
	public String toString () {
		return "BankEntity {Id=" + id + 
				", bankName=" + bankName + 
				", bankBIC=" + bankBIC + "}";
	}

}
