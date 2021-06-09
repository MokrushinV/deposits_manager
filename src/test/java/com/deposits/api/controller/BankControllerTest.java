/*package com.deposits.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;

import com.deposits.api.assembler.BankModelAssembler;
import com.deposits.api.assembler.DepositModelAssembler;
import com.deposits.entities.BankEntity;
import com.deposits.entities.DepositEntity;
import com.deposits.services.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@EnableSpringDataWebSupport
public class BankControllerTest {
	
	private static final String URL = "/banks";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private BankService bankService;
	
	@MockBean
	private BankModelAssembler bankModelAssembler;
	
	@MockBean
	private DepositModelAssembler depositModelAssembler;
	
	@Test
	public void testGetBank () throws Exception {
		BankEntity bank = new BankEntity ();
		bank.setBankBIC ("bic1");
		bank.setBankName ("bank1");
		bank.setId (1);
		bank.setDeposits(null);
		bankService.addBank(bank);
		
		Mockito.when (bankService.addBank(Mockito.any(BankEntity.class))).thenReturn(bank);
		
		mockMvc.perform(
				get (URL + "/{id}", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.bankName").value("bank1"))
				.andExpect(jsonPath("$.bankBIC").value("bic1"))
				.andExpect(jsonPath("$.deposits").value(null));
		/*static imports MockMvcResultMatchers.*,MockMvcRequestBuilders.*
		mockMvc.perform(get(URL + "/1"))
			   .andDo(MockMvcResultHandlers.print())
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.jsonPath(("$.bankName").valueOf("bank1")))
			   .andExpect(MockMvcResultMatchers.jsonPath(("$.bankBIC").valueOf("bic1")))
			   .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href", "http://localhost/banks/1"));*/
/*	}

}
*/