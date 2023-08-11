package com.horsetrackapp.horsetrack.service;

import com.horsetrackapp.horsetrack.model.Inventory;
import com.horsetrackapp.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Value("${restock.amount}")
    private int restockAmount;

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean sufficientFunds(int amountWon) {
        List<Inventory> inventories = inventoryRepository.findAll();
        Integer result = inventories.stream().reduce(0,
                (total,inventory) -> total + (inventory.getDenomination() * inventory.getBillCount()), Integer::sum);
        if(result - amountWon >= 0) {
            return true;
        } else{
            return false;
        }
    }

    public void restock() {
        List<Inventory> inventories = inventoryRepository.findAll();

        inventories.stream()
                .forEach(inventory -> {
                inventory.setBillCount(restockAmount);
                inventoryRepository.save(inventory);
        });
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventory(int denomination){
        return inventoryRepository.findByDenominationEquals(denomination);
    }

    public void decrementInventory(int denomination, int amount) {
        Inventory inventory = inventoryRepository.findByDenominationEquals(denomination);

        int currentBillCount = inventory.getBillCount();
        if((currentBillCount - amount) >= 0){
            inventory.setBillCount(currentBillCount - amount);
            inventoryRepository.save(inventory);
        }
    }
}
