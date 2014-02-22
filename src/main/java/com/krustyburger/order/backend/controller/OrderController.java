package com.krustyburger.order.backend.controller;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.krustyburger.order.backend.dto.OrderDTO;
import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.service.OrderService;
import com.krustyburger.order.backend.translator.OrderTranslator;

@Controller
@Path("/kburger")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderTranslator orderTranslator;
	
	@GET
	@Path("/orders")
	public List<OrderDTO> find() {
		List<Order> orders = this.orderService.find();
		return this.orderTranslator.entityToDTO(orders);
	}
	
	@GET
	@Path("/order/{id}")
	public OrderDTO findBy(@PathParam("id") Long id) {
		Order order = this.orderService.findBy(id);
		return this.orderTranslator.entityToDTO(order);
	}

	
	@POST
	@Path("/order")
	public Long add(@FormParam("item") Long[] items, @FormParam("address") String address) {
		return this.orderService.add(items, address);
	}

	@PUT
	@Path("/order/{id}")
	public OrderDTO updateStatus(@PathParam("id") Long id) {
		Order order = this.orderService.updateStatus(id);
		return this.orderTranslator.entityToDTO(order);
	}		
	
}
