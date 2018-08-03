package com.example.projectfeignservice.exemplo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("project-order-service")
public interface OrderService {

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	Order getOrder(@PathVariable("id") String id);

}
