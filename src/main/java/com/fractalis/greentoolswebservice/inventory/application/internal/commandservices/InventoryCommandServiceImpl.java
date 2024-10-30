package com.fractalis.greentoolswebservice.inventory.application.internal.commandservices;

import com.fractalis.greentoolswebservice.account.domain.model.aggregates.User;
import com.fractalis.greentoolswebservice.inventory.domain.model.aggregates.Inventory;
import com.fractalis.greentoolswebservice.inventory.domain.services.InventoryCommandService;
import com.fractalis.greentoolswebservice.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory createInventory(Long user, String stationName,String plant, double temperature, double uvSolar, double humidity) {
        Inventory inventory = new Inventory(user,stationName, plant, temperature, uvSolar, humidity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public void updateInventory(Long inventoryId, String stationName,  String plant, double temperature, double uvSolar, double humidity) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.updateInventory(plant,stationName , temperature, uvSolar, humidity);
            inventoryRepository.save(inventory);
        } else {
            throw new IllegalArgumentException("Inventory not found with ID: " + inventoryId);
        }
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        if (inventoryRepository.existsById(inventoryId)) {
            inventoryRepository.deleteById(inventoryId);
        } else {
            throw new IllegalArgumentException("Inventory not found with ID: " + inventoryId);
        }
    }
}
