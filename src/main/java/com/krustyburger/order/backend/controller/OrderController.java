package com.krustyburger.order.backend.controller;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.krustyburger.order.backend.dto.OrderDTO;
import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.model.OrderStatus;
import com.krustyburger.order.backend.service.OrderService;
import com.krustyburger.order.backend.translator.OrderTranslator;

@Controller
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderTranslator orderTranslator;
	
	@GET
	@Path("/findPendentAndInProgress")
	public List<OrderDTO> findPendentAndInProgress() {
		List<Order> orders = this.orderService.findPendentAndInProgress();
		return this.orderTranslator.entityToDTO(orders);
	}
	
	@GET
	@Path("/findFinalized")
	public List<OrderDTO> findFinalized() {
		List<Order> orders = this.orderService.findFinalized();
		return this.orderTranslator.entityToDTO(orders);
	}	
	
	@GET
	@Path("/findById/{id}")
	public OrderDTO findBy(@PathParam("id") Long id) {
		Order order = this.orderService.findBy(id);
		return this.orderTranslator.entityToDTO(order);
	}
	
	@POST
	@Path("/add")
	public Long add(@FormParam("item") Long[] items, @FormParam("address") String address) {
		return this.orderService.add(items, address);
	}
	
	@POST
	@Path("/updateStatus")
	public OrderDTO updateStatus(@FormParam("orderId") Long id, @FormParam("orderStatus") OrderStatus status) {
		Order order = this.orderService.updateStatus(id, status);
		return this.orderTranslator.entityToDTO(order);
	}
	
}
