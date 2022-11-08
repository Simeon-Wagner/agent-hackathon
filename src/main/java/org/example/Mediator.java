package org.example;

import org.example.IndividualFactory.Individual;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;

@Getter
@Setter
public class Mediator {
    Supplier mSupplier;
    Customer mCustomer;
    Population mPopulation;
    int mPopulationSize;
    int mJobsAmount;
    Individual mContract;

    Mediator(Supplier supplier, Customer customer, int jobsAmount, int populationSize) {
        mCustomer = customer;
        mSupplier = supplier;
        mJobsAmount = jobsAmount;
        mContract = IndividualFactory.factory.createIndividual().swapGenes(IntStream.range(0, jobsAmount).toArray());
        mPopulationSize = populationSize;
        mPopulation = new Population(jobsAmount);
        mPopulation.createPopulation(populationSize);
    }

    boolean proposeContract( Population proposal) {
        boolean approve = false;
        for(Individual i : proposal.getMIndividuals()) {
            if(mCustomer.vote(mContract.getMGenes(), i.getMGenes()) && mSupplier.vote(mContract.getMGenes(), i.getMGenes())) {
                i.setMApproved(true);
                mContract.swapGenes(i.getMGenes());
                System.out.printf("%10d%10d%n", mCustomer.getTime_contract(), mSupplier.getTime(mContract.getMGenes()));
                approve = true;
            }
        }
        mPopulation = proposal;
        return approve;
    }


}
