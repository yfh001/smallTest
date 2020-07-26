package com.example.demo.controllerTest;

import com.alibaba.fastjson.JSON;
import com.example.demo.TransactionManageApplication;
import com.example.demo.common.ResultData;
import com.example.demo.controller.TransactionController;
import com.example.demo.pojo.Transactions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionManageApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TransactionController transactionController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
	}

	/**
	 * Transactional can do nothing to the DB
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void testOpersForTransactionsRecord() throws Exception {
		Transactions transac = insertParamInit();
		mockMvc.perform(MockMvcRequestBuilders.post("/transaction/opersForCUR").content(JSON.toJSONString(transac)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	@Transactional
	public void testOpersForTransactionsRecordForUpdate() throws Exception {
		Transactions transac = updateParamInit();
		mockMvc.perform(MockMvcRequestBuilders.post("/transaction/opersForCUR").content(JSON.toJSONString(transac)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testOpersForTransactionsRecordForCancel() throws Exception {
		Transactions transac = cacelParamInit();
		mockMvc.perform(MockMvcRequestBuilders.post("/transaction/opersForCUR").content(JSON.toJSONString(transac)))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	public  Transactions insertParamInit(){
		Transactions transactions = new Transactions();
		transactions.setOperType("INSERT");
		transactions.setTadeType("BUY");
		transactions.setQuantity(50l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}
	public  Transactions insertParamInit02(){
		Transactions transactions = new Transactions();
		transactions.setOperType("INSERT");
		transactions.setQuantity(50l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}

	public  Transactions insertParamInit03(){
		Transactions transactions = new Transactions();
		transactions.setTadeType("BUY");
		transactions.setQuantity(50l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}


	public  Transactions insertParamInit04(){
		Transactions transactions = new Transactions();
		transactions.setOperType("INSERT");
		transactions.setTadeType("BUY");
		transactions.setQuantity(0l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}
	public  Transactions updateParamInit(){
		Transactions transactions = new Transactions();
		transactions.setOperType("UPDATE");
		transactions.setTadeType("BUY");
		transactions.setQuantity(50l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}

	public  Transactions cacelParamInit(){
		Transactions transactions = new Transactions();
		transactions.setOperType("CANCEL");
		transactions.setTadeType("BUY");
		transactions.setQuantity(50l);
		transactions.setSecurityCode("REL");
		transactions.setTradeId(1+"");
		return transactions;
	}



	@Test
	public void testControllerInsert(){
		Transactions transac = insertParamInit();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(1+"",data.getCode()+"");
	}

	@Test
	public void testControllerUpdate(){
		Transactions transac = updateParamInit();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(0+"",data.getCode()+"");
	}
	@Test
	public void testControllerCancel(){
		Transactions transac = cacelParamInit();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(1+"",data.getCode()+"");
	}

	@Test
	public void testControllerCancel02(){
		Transactions transac = insertParamInit02();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(0+"",data.getCode()+"");
	}

	@Test
	public void testControllerCancel03(){
		Transactions transac = insertParamInit03();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(0+"",data.getCode()+"");
	}

	@Test
	public void testControllerCancel04(){
		Transactions transac = insertParamInit04();
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(0+"",data.getCode()+"");
	}

	@Test
	public void testControllerInsert02(){
		Transactions transac = insertParamInit();
		transac.setTradeId(2+"");
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(1+"",data.getCode()+"");
	}

	@Test
	public void testControllerUpdate02(){
		Transactions transac = updateParamInit();
		transac.setTadeType("SELL");
		transac.setTradeId(2+"");
		String params = JSON.toJSONString(transac);
		ResultData data = transactionController.opersForTransactionsRecord(params);
		Assert.assertEquals(1+"",data.getCode()+"");
	}

}
