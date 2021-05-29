package com.deposits.entities;

import com.deposits.entities.enums.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "client_table")
public class ClientEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "client_name")
	private String name;
	
	@Column(name = "client_short_name")
	private String shortName;
	
	@Column(name = "client_address")
	private String address;
	
	@Column(name = "client_incorporation_form")
	@Enumerated(EnumType.STRING)
	private IncorporationForms incorpForm;
	
	@OneToMany(mappedBy = "client" ,targetEntity = DepositEntity.class, cascade = CascadeType.ALL, //cascade - if entity is (for example)deleted then deposit will be deleted too
			   fetch = FetchType.EAGER, orphanRemoval = true)
	//@JoinColumn(name = "client_id",referencedColumnName = "id")
	private Set <DepositEntity> deposits;
	
	protected ClientEntity () {
		
	}
	
	public ClientEntity (String name, String shortName, String address, IncorporationForms incorpForm) {
		this.name = name;
		this.shortName = shortName;
		this.address = address;
		this.incorpForm = incorpForm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public IncorporationForms getIncorpForm() {
		return incorpForm;
	}

	public void setIncorpForm(IncorporationForms incorpForm) {
		this.incorpForm = incorpForm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonManagedReference (value = "client_reference")
	public Set<DepositEntity> getDeposits() {
		return deposits;
	}

	public void setDeposits(Set<DepositEntity> deposits) {
		this.deposits = deposits;
	}

	@Override
	public String toString() {
		return "ClientEntity {id=" + id + 
							", name=" + name + 
							", shortName=" + shortName + 
							", address=" + address + 
							", incorpForm="+ incorpForm + "}";
	}
	
}
