package com.microservice.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.demo.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer>{

}
