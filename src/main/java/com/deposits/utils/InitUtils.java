package com.deposits.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class InitUtils implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println ("run");
	}

}
