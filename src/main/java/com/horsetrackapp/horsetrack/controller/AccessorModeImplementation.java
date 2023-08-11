package com.horsetrackapp.horsetrack.controller;

import com.horsetrackapp.horsetrack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AccessorModeImplementation implements AccessorMode {

    private boolean quit = false;

    public AccessorModeImplementation(){
    }

    @Autowired
    HorseService horseService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    CommandService commandService;

    @Autowired
    ConfigService configService;

    @Autowired
    WagerService wagerService;

    @Autowired
    ReporterService reporterService;

    @Override
    public boolean quit() {
        return quit;
    }

    @Override
    public void restock() {
        inventoryService.restock();
        reporterService.printInventory();
    }

    @Override
    public void wager(int horseNumber, int wagerAmount) {
        if(!(horseService.isValidHorseNumber(horseNumber))) {
            reporterService.printInvalidHorse(horseNumber);
            return;
        }

        if(!(horseService.isHorseWinner(horseNumber))) {
            reporterService.printNoPayout(horseService.getHorseName(horseNumber));
            return;
        }

        int amountWon = wagerService.calculateAmountWon(wagerAmount, horseService.getHorseOdds(horseNumber));

        if(inventoryService.sufficientFunds(amountWon)) {
            reporterService.printPayout(horseService.getHorseName(horseNumber), amountWon);
            reporterService.printDispense(wagerService.dispenseWinnings(amountWon));
        } else{
            reporterService.printInsufficientFunds(amountWon);
        }
        reporterService.printInventory();
        reporterService.printHorses();
    }

    @Override
    public void winner(int horseNumber) {
        if(horseService.isValidHorseNumber(horseNumber)) {
            horseService.setRaceWinner(horseNumber);
            reporterService.printInventory();
            reporterService.printHorses();
        } else{
            reporterService.printInvalidHorse(horseNumber);
        }
    }

    @Override
    public void printStartupMessages() {
        reporterService.startup();
    }

    @Override
    public void initialize() {
        configService.startup();
    }

    @Override
    public void execute(String commandLine) {
        System.out.println("Command issued: "+commandLine);
        if(commandLine.length() > 0){
            if((commandService.parseCommand(commandLine).equalsIgnoreCase("invalid"))) {
                reporterService.printInvalidCommand(commandLine);
                reporterService.printInventory();
                reporterService.printHorses();
            } else {
                commandFactory(commandLine);
            }
        }
    }

    private void commandFactory(String commandLine) {
        String command = commandService.parseCommand(commandLine);
        switch(command){
            case "quit":
                quit = true;
                break;
            case "restock":
                restock();
                break;
            case "winner":
                winner(commandService.getWinningHorseNumber());
                break;
            case "wager":
                wager(commandService.getBetHorseNumber(), commandService.getWagerAmount());
                break;
            case "error":
                reporterService.printErrorMessage(commandService.getErrorMessage());
                break;
            default:
        }
    }
}
