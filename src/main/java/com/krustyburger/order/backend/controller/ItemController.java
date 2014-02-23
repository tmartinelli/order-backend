package com.krustyburger.order.backend.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.krustyburger.order.backend.dto.ItemDTO;
import com.krustyburger.order.backend.model.Item;
import com.krustyburger.order.backend.service.ItemService;
import com.krustyburger.order.backend.translator.ItemTranslator;

@Controller
@Path("/kburger")
@Produces(MediaType.APPLICATION_JSON)
public class ItemController {

	private ItemService itemService;
	private ItemTranslator itemTranslator;

	@Autowired
	public ItemController(ItemService itemService, ItemTranslator itemTranslator) {
		this.itemService = itemService;
		this.itemTranslator = itemTranslator;
	}
	
	@GET
	@Path("/items")
	public List<ItemDTO> find() {
		List<Item> items = this.itemService.findAll();
		return this.itemTranslator.entityToDTO(items);
	}
	
}
