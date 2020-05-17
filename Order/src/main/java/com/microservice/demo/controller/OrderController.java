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

import com.microservice.demo.dao.OrderDao;
import com.microservice.demo.model.Order;

@Controller
@RefreshScope
@RequestMapping("/order")
public class OrderController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
    private OrderDao dao;
	
	@Value("${method}")
	String method;
	
	@Value("${paymentPort}")
	String paymentPort;
	
	 @GetMapping("/dynamicMethod")
	 @ResponseBody
		public String PayMethod() {
		 log.info("in dynamic Method");
			String response = (String) restTemplate.getForObject(paymentPort + method, String.class);
			return response;
	 }
	
	 @GetMapping("/getOrders")
	 @ResponseBody
	 public List<Order> getOrders(){
		 log.info("in get order method");
		 return dao.findAll();
	 }
	 
	 @PostMapping("/Order")
	 @ResponseBody
		public String addAlien(@RequestBody Order order) {
		 log.info("in create order");
			System.out.println(order);
			dao.save(order);
			return "Order sent to cart";
	 }
	 
	 @PostMapping("/payOrder")
	 @ResponseBody
		public String payOrder(@RequestBody Order order) {
		 log.info("in payment order");
		 	System.out.println("in post payorder method");
		 	String response = (String) restTemplate.postForObject("http://localhost:8082/pay", order, String.class);
		 	return response;
	}
	
	 @RequestMapping("/findById")
		@ResponseBody
		public Optional<Order> Test(@RequestBody int id) {
		 log.info("find all method");
			return dao.findById(id);
	}
}

