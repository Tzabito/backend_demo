package com.fractalis.greentoolswebservice.inventory.interfaces.rest;


import com.fractalis.greentoolswebservice.account.domain.model.aggregates.User;
import com.fractalis.greentoolswebservice.account.domain.services.UserQueryService;
import com.fractalis.greentoolswebservice.inventory.domain.model.aggregates.Inventory;
import com.fractalis.greentoolswebservice.inventory.domain.services.InventoryCommandService;
import com.fractalis.greentoolswebservice.inventory.domain.services.InventoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;
    private final UserQueryService userQueryService;

    @Autowired
    public InventoryController(InventoryCommandService inventoryCommandService, InventoryQueryService inventoryQueryService, UserQueryService userQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
        this.userQueryService = userQueryService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryQueryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryQueryService.getInventoryById(id);
        return inventory.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/client")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventoryRequest, @RequestParam Long userId) {
        Optional<User> user = userQueryService.getUserById(userId);
        if (user.isPresent()) {
            Inventory inventory = inventoryCommandService.createInventory(
                    userId,
                    inventoryRequest.getStationName(),
                    inventoryRequest.getPlant(),
                    inventoryRequest.getTemperature(),
                    inventoryRequest.getUvSolar(),
                    inventoryRequest.getHumidity()
            );
            return new ResponseEntity<>(inventory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT: Actualizar un inventario existente
    @PutMapping("/client/{id}")
    public ResponseEntity<Void> updateInventory(@PathVariable Long id,
                                                @RequestParam String plant,
                                                @RequestParam Double temperature,
                                                @RequestParam Double uvSolar,
                                                @RequestParam Double humidity,
                                                @RequestParam String stationName) {
        try {
            inventoryCommandService.updateInventory(id, stationName, plant, temperature, uvSolar, humidity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Eliminar un inventario por su ID
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        try {
            inventoryCommandService.deleteInventory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}