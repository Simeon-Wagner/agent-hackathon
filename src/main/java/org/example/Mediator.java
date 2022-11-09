package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
public class Mediator {
    Customer customer;
    Supplier supplier;
    int jobsAmount;
    int rounds;
    double canAccept;
    Integer [] contract;



    public Mediator(Customer customer, Supplier supplier, int jobsAmount, int rounds, double canAccept) {
        this.customer = customer;
        this.supplier = supplier;
        this.jobsAmount = jobsAmount;
        this.rounds = rounds;
        this.canAccept = canAccept;
        this.contract = IntStream.range(0,jobsAmount).boxed().toArray(Integer[] :: new);
    }

    public Integer [] mutate_contract(Integer [] contract){
        Integer [] proposal = Arrays.copyOf(contract, contract.length);
        double mutational_constant = 0.1;
        int index = 0;
        for (int i = 0; i < proposal.length; i++) {
            if(Math.random() <= mutational_constant){
                do {
                    index = (int) (Math.random() * contract.length);
                }while(index == i);
                Integer temp = proposal[i];
                proposal[i]= proposal[index];
                proposal[index]= temp;
            }
        }
        return proposal;
    }

    public boolean accepted(){
        Integer [] proposal = mutate_contract(contract);
        if(customer.vote(proposal,contract) && supplier.vote(proposal,contract) ){
            this.contract = proposal;
            return true;
        }
        return false;
    }
}
