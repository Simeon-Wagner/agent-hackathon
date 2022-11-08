package org.example;


import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.stream.IntStream;

@Getter
@Setter
public class Mediator {
    Supplier mSupplier;
    Customer mCustomer;
    int mJobsAmount;
    int[] mContract;

    Mediator(Supplier supplier, Customer customer, int jobsAmount) {
        mCustomer = customer;
        mSupplier = supplier;
        mJobsAmount = jobsAmount;
        mContract = IntStream.range(0, jobsAmount).toArray();
    }

    int [] adjustContractRandomly() {
        Random rand = new Random();
        int[] newProposal = new int[mContract.length];
        System.arraycopy(mContract,0,newProposal,0,mContract.length);
        int job;
        int index = rand.nextInt(newProposal.length);
        if(index == newProposal.length - 1) {
            job = newProposal[index-1];
            newProposal[index-1] = newProposal[index];
            newProposal[index] = job;
        } else {
            job = newProposal[index+1];
            newProposal[index+1] = newProposal[index];
            newProposal[index] = job;
        }

        return newProposal;
    }
    boolean proposeContract( int [] proposal) {
        if(mCustomer.vote(mContract, proposal) && mSupplier.vote(mContract, proposal)) {
            mContract = proposal;
            return true;
        }
        return false;
    }

}
