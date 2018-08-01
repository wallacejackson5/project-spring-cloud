package com.example.projectorderservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projectorderservice.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
