package com.microservice.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.demo.model.Order;

public interface OrderDao extends JpaRepository<Order, Integer>
{

}
