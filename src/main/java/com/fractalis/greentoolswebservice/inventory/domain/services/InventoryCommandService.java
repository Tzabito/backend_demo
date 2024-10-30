package com.fractalis.greentoolswebservice.inventory.domain.services;

import com.fractalis.greentoolswebservice.account.domain.model.aggregates.User;
import com.fractalis.greentoolswebservice.inventory.domain.model.aggregates.Inventory;

public interface InventoryCommandService {
    Inventory createInventory(Long user_id,String stationName,String plant, double temperature, double uvSolar, double humidity);
    void updateInventory(Long inventoryId, String stationName , String plant, double temperature, double uvSolar, double humidity);
    void deleteInventory(Long inventoryId);
}
