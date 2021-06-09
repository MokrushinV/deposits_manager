package com.deposits.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deposits.entities.BankEntity;
import com.deposits.entities.ClientEntity;
import com.deposits.entities.DepositEntity;
import com.deposits.entities.enums.IncorporationForms;
import com.deposits.repositories.BankRepository;
import com.deposits.repositories.ClientRepository;
import com.deposits.repositories.DepositRepository;

/**
 * Configuration bean to preload some data to the in-memory database h2.
 *  So modifications (by CRUD operations) to database will only
 * exist for this very run of a program.
 * @author Mokrushin Vladimir
 *
 */
@Configuration
public class LoadDataBase {
	
	private static final Logger	log = LoggerFactory.getLogger(LoadDataBase.class);
	
	@Bean
	CommandLineRunner initClientDataBase (ClientRepository clientRepository) {
		
		return args -> {
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Andrew Lewis", 
																			 "Andrew", 
																			 "Perm, Lenin st., 45", 
																			 IncorporationForms.INDIVIDUAL)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Max Power", 
																			 "Max", 
																			 "Perm, Pushkina st., 76", 
																			 IncorporationForms.INDIVIDUAL)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Conan Barbarian", 
																			 "Conan", 
																			 "Berezniki, Vesyolaia st., 25", 
																			 IncorporationForms.INDIVIDUAL)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Ken Lewin", 
																			 "Ken", 
																			 "Krasnokamsk, Pervaya st., 3", 
																			 IncorporationForms.SOLE_PROPRIETORSHIP)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Todd Howard", 
																			 "Todd", 
																			 "Kazan, Beregovaya st., 65", 
																			 IncorporationForms.SOLE_PROPRIETORSHIP)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("LLC Vector", 
																			 "Vector", 
																			 "Moscow, Krasnaya st., 96", 
																			 IncorporationForms.LLC)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("LLC Horns and Hoofs", 
					 														 "Horns and Hoofs", 
					 														 "St. Petersburg, Svetlaya st., 108", 
					 														 IncorporationForms.LLC)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Tetriandoh Inc.", 
					 														 "Tetriandoh", 
					 														 "Moscow, Konechnaya st., 31", 
					 														 IncorporationForms.INCORPORATED)));
			log.info ("Preloading " + clientRepository.save(new ClientEntity("Umbrella Corp.", 
					 														 "Umbrella", 
					 														 "Samara, Nagornaya st., 10", 
					 														 IncorporationForms.INCORPORATED)));
		};
	}
	
	@Bean
	CommandLineRunner initBankDataBase (BankRepository bankRepository) {
		
		return args -> {
			log.info ("Preloading " + bankRepository.save(new BankEntity("Beta Bank", 
																		 "045396781")));
			log.info ("Preloading " + bankRepository.save(new BankEntity("SgrefBank", 
																		 "190475648")));
			log.info ("Preloading " + bankRepository.save(new BankEntity("Pinkoff Bank", 
					                                                     "548782740")));
			log.info ("Preloading " + bankRepository.save(new BankEntity("HouseCredit", 
					                                                     "065993846")));
		};
	}
	
	@Bean
	CommandLineRunner initDepositDataBase (DepositRepository depositRepository,
										   ClientRepository clientRepository, 
										   BankRepository bankRepository) {
		
		return args -> {
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Andrew Lewis"), 
																			   bankRepository.findByName("Beta Bank"), 
																			   "2018-05-15", 
																			   5.1, 
																			   36)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Max Power"), 
					   														   bankRepository.findByName("SgrefBank"), 
					   														   "2018-09-10", 
					   														   6.1, 
					   														   32)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Conan Barbarian"), 
					   														   bankRepository.findByName("Pinkoff Bank"), 
					   														   "2017-05-13", 
					   														   4.9, 
					   														   48)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Ken Lewin"), 
					   													       bankRepository.findByName("HouseCredit"), 
					   													       "2020-01-29", 
					   													       5.8, 
					   													       16)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Todd Howard"), 
					   														   bankRepository.findByName("Beta Bank"), 
					   														   "2015-10-05", 
					   														   4.2, 
					   														   67)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("LLC Vector"), 
					   														   bankRepository.findByName("SgrefBank"), 
					   														   "2021-04-15", 
					   														   5.5, 
					   														   1)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("LLC Horns and Hoofs"), 
					   														   bankRepository.findByName("Pinkoff Bank"), 
					   														   "2019-06-27", 
					   														   4.0, 
					   														   23)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Tetriandoh Inc."), 
					   														   bankRepository.findByName("HouseCredit"), 
					   														   "2020-05-15", 
					   														   3.9, 
					   														   12)));
			log.info ("Preloading " + depositRepository.save(new DepositEntity(clientRepository.findByName("Umbrella Corp."), 
					   														   bankRepository.findByName("Pinkoff Bank"), 
					   														   "2015-02-03", 
					   														   6.4, 
					   														   75)));
		};
	}
}
