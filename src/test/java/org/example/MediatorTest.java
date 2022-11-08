package org.example;

import org.example.IndividualFactory.Individual;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MediatorTest {
    static int populationSize;


    @Test
    public void testCreatePopulation() {
        setup();
        assertEquals(populationSize, Main.mediator.mPopulation.getMIndividuals().size());
        for(Individual i : Main.mediator.mPopulation.getMIndividuals()) {
            assertEquals(i.getMGenes().length, Main.mediator.mJobsAmount);
        }
    }
    

    @Test
    public void testParentsSize() {
        assertEquals(0, Main.mediator.getMPopulation().getMIndividuals().size() % 2);
    }

    static void setup() {
        populationSize = 10;
        Main.supplier = new Supplier(Main.loadSupplierMatrix("src/main/resources/daten5ASupplier_200.txt"));
        Main.customer = new Customer(Main.loadCustomerMatrix("src/main/resources/daten4ACustomer_200_5.txt"));
        IndividualFactory.createFactory(Main.amountJobs);
        Main.mediator = new Mediator(Main.supplier, Main.customer, Main.amountJobs, populationSize);
    }


}
