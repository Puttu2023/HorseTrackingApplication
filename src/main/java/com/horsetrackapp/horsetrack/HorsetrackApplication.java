package com.horsetrackapp.horsetrack;

import com.horsetrackapp.horsetrack.controller.AccessorModeImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;
@Profile("!test")
@SpringBootApplication
public class HorsetrackApplication implements CommandLineRunner {
    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    AccessorModeImplementation accessorModeImplementation;

    public static void main(String[] args) {
        SpringApplication.run(HorsetrackApplication.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        // initialise the data
        accessorModeImplementation.initialize();
        // print the start-up messages
        accessorModeImplementation.printStartupMessages();

        // Read from the command line
        Scanner commandString = new Scanner(System.in);
        while (!(accessorModeImplementation.quit())) {
            // Execute based on the given input command
            accessorModeImplementation.execute(commandString.nextLine());
            System.out.println();
        }
        System.exit(SpringApplication.exit(context));
    }
}
