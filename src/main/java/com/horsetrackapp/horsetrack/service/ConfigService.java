package com.horsetrackapp.horsetrack.service;

import com.horsetrackapp.horsetrack.RaceStatus;
import com.horsetrackapp.horsetrack.model.Horse;
import com.horsetrackapp.horsetrack.model.Inventory;
import com.horsetrackapp.horsetrack.repository.HorseRepository;
import com.horsetrackapp.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    @Value("${max.horses}")
    private int MaxHorses;

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public void loadHorses(){
        //loading horses
        horseRepository.save(new Horse(1, "That Darn Gray Cat", 5, RaceStatus.WON));
        horseRepository.save(new Horse(2, "Fort Utopia", 10, RaceStatus.LOST));
        horseRepository.save(new Horse(3, "Count Sheep", 9, RaceStatus.LOST));
        horseRepository.save(new Horse(4, "Ms Traitour", 4, RaceStatus.LOST));
        horseRepository.save(new Horse(5, "Real Princess", 3, RaceStatus.LOST));
        horseRepository.save(new Horse(6, "Pa Kettle", 5, RaceStatus.LOST));
        horseRepository.save(new Horse(7, "Gin Stinger", 6, RaceStatus.LOST));
    }

    public void loadInventory(){
        //loading inventory
        inventoryRepository.save(new Inventory(1, 10));
        inventoryRepository.save(new Inventory(5, 10));
        inventoryRepository.save(new Inventory(10, 10));
        inventoryRepository.save(new Inventory(20, 10));
        inventoryRepository.save(new Inventory(100, 10));
    }

    public void startup(){
        //load inventory and horses
        loadHorses();
        loadInventory();
    }

}
