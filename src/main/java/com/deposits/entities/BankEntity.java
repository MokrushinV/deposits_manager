package com.deposits.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * BankEntity is an entity that represents a bank that
 * may have deposits. One bank can have many deposits.
 * @author Mokrushin Vladimir
 *
 */
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
	
	@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL) //cascade - if entity is (for example)deleted then deposit will be deleted too
	//@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Set <DepositEntity> deposits;
	
	public BankEntity (String bankName, String bankBIC) {
		this.bankName = bankName;
		this.bankBIC = bankBIC;
	}
	
	public BankEntity () {
		
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

	@JsonManagedReference (value = "bank_reference")
	public Set<DepositEntity> getDeposits() {
		return deposits;
	}

	public void setDeposits(Set<DepositEntity> deposits) {
		this.deposits = deposits;
	}

	@Override
	public String toString () {
		return "BankEntity {Id=" + id + 
				", bankName=" + bankName + 
				", bankBIC=" + bankBIC + "}";
	}

}
