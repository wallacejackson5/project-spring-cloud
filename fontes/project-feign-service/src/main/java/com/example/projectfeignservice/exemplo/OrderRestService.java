package com.example.projectfeignservice.exemplo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/order")
public class OrderRestService {

	private final OrderService orderService;

	public OrderRestService(OrderService orderService) {
		this.orderService = orderService;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable String id) {
		return orderService.getOrder(id);
	}
}
