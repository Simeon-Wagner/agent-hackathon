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
    int [] mProposal;

    Mediator(Supplier supplier, Customer customer, int jobsAmount) {
        mCustomer = customer;
        mSupplier = supplier;
        mJobsAmount = jobsAmount;
        mContract = IntStream.range(0, jobsAmount).toArray();
    }

    void adjustContractRandomly() {
        Random rand = new Random();
        int[] newProposal = mContract;
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
        mProposal=newProposal;
    }
    void proposeContract() {
        if(mCustomer.vote(mContract, mProposal) && mSupplier.vote(mContract, mProposal)) {
            mContract = mProposal;
        }
    }

}
