package com.microservice.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.microservice.demo.common.Order;
import com.microservice.demo.dao.PaymentDao;
import com.microservice.demo.model.Payment;

@Controller
@RefreshScope
@RequestMapping("/payment")
public class PaymentController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
    private PaymentDao dao;
	
	@Value("${my.greeting}")
	String greeting;
	
	@Value("${thirdPartyPort}")
	String thirdPartyPort;
	
	 @GetMapping("/getPayments")
	 @ResponseBody
	 public List<Payment> getOrders(){
		 log.info("in Get Payments");
		 System.out.println("in get pay method");
		 return dao.findAll();
	 }
	 
	 @PostMapping("/pay")
	 @ResponseBody
	 public String addAlien(@RequestBody Order order) {
		 
		log.info("in post pay method");
		Payment payment = new Payment();
		payment.setOrderId(String.valueOf(order.getId()));
		payment.setStatus("Payment successful");
		dao.save(payment);
		return "Payment done";
	}
	
	@GetMapping("/greeting")
	@ResponseBody
	public String Response() {
		return greeting;
	}
	
	@GetMapping("/payThroughPaytm")
	@ResponseBody
	public String paySecure() {
		log.info("Payment through bank server");
		String response = (String) restTemplate.getForObject(thirdPartyPort, String.class);
		return response;
	}
	
	@GetMapping("/payThrougBank")
	@ResponseBody
	public String payWithOutSecure() {
		log.info("Payment through thirdpsrty server");
		return "payed with out Security";
	}
	
	 @RequestMapping("/findById")
	@ResponseBody
	public Optional<Payment> Test(@RequestBody int id) {
		log.info("in get find method");
		return dao.findById(id);
	}
}
