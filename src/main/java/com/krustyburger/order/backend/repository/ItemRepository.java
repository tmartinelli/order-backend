package com.krustyburger.order.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krustyburger.order.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
