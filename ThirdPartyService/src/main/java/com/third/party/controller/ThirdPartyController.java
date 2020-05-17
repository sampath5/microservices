package com.third.party.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RefreshScope
//@RequestMapping("/thirdparty")
public class ThirdPartyController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/thirdparty")
	@ResponseBody
	public String getOrders(){
		try {
			Thread.sleep(2000);
		} catch (Exception ex) {

		}
		log.info("Payment through third party method");
		return "Payment done through third party server";
	}

}
