package com.horsetrackapp.horsetrack.service;

import com.horsetrackapp.horsetrack.model.Horse;
import com.horsetrackapp.horsetrack.model.Inventory;
import com.horsetrackapp.horsetrack.model.Wager;
import com.horsetrackapp.horsetrack.repository.HorseRepository;
import com.horsetrackapp.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporterService {
    @Autowired
    private HorseRepository horseRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Value("${currency.symbol}")
    private String currencySymbol;
    //Messages
    @Value("${message.horses}")
    private String messageHorses;
    @Value("${message.inventory}")
    private String messageInventory;
    @Value("${message.payout}")
    private String messagePayout;
    @Value("${message.no.payout}")
    private String messageNoPayout;
    @Value("${message.dispensing}")
    private String messageDispensing;
    //Error Messages
    @Value("${error.message.invalid.command}")
    private String errorMessageInvalidCommand;
    @Value("${error.message.invalid.bet}")
    private String errorMessageInvalidBet;
    @Value("${error.message.insufficient.funds}")
    private String errorMessageInsufficientFunds;

    @Value("${error.message.invalid.horse.number}")
    private String errorMessageInvalidHorseNumber;

    public void printInvalidHorse(int horseNumber) {
        System.out.println(errorMessageInvalidHorseNumber + " " + horseNumber);
    }

    public void printNoPayout(String horseName) {
        System.out.println(messageNoPayout + " " + horseName);
        printInventory();
        printHorses();
    }

    public void startup() {
        printInventory();
        printHorses();
    }

    public void printInvalidCommand(String commandLine) {
        System.out.println(errorMessageInvalidCommand + " "+ commandLine);
    }

    public void printInvalidBet(String invalidBet) {
        System.out.println(errorMessageInvalidBet + " "+invalidBet);
        printInventory();
        printHorses();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        printInventory();
        printHorses();
    }

    public void printPayout(String horseName, int amountWon) {
        System.out.println(messagePayout + " " + horseName + "," + currencySymbol + amountWon);
    }

    public void printDispense(List<Wager> dispense) {
        System.out.println(messageDispensing);
        dispense.forEach(wager -> {
            System.out.println(currencySymbol
                    + wager.getDenomination()
                    + ","
                    + wager.getBillCount()
            );
        });

    }

    public void printInsufficientFunds(int amountWon) {
        System.out.println(errorMessageInsufficientFunds + " " + currencySymbol + amountWon);

    }

    public void printInventory() {
        Iterable<Inventory> inventories = inventoryRepository.findAll();
        System.out.println(messageInventory);
        inventories.forEach((inventory) -> {
            System.out.println(currencySymbol
                    + inventory.getDenomination()
                    + "," + inventory.getBillCount());
        });
    }

    public void printHorses() {
        Iterable<Horse> horses = horseRepository.findAll();
        System.out.println(messageHorses);
        horses.forEach((horse) -> {
            System.out.println(horse.getHorseNumber()
                    + "," + horse.getHorseName()
                    + "," + horse.getOdds()
                    + "," + horse.getRaceStatus().toString().toLowerCase());
        });
    }
}
