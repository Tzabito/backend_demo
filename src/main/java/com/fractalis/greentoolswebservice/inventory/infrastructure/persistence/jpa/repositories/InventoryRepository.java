package com.fractalis.greentoolswebservice.inventory.infrastructure.persistence.jpa.repositories;

import com.fractalis.greentoolswebservice.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}