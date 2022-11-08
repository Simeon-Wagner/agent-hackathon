package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.IndividualFactory.Individual;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Population {
    private List<Individual> mIndividuals = new ArrayList<>();
    private int amountGenes;

    Population(int amountGenes) {
        this.amountGenes = amountGenes;
    }

    public void addIndividual(Individual ind) {
        mIndividuals.add(ind);
    }

    public void setIndividuals(List<Individual> individuals) {
        mIndividuals = individuals;
    }

//    Population getParents() {
//        Population retPop = new Population(amountGenes);
//        retPop.setMIndividuals(mIndividuals.stream().filter(Individual::isMApproved).toList());
//
//        //wenn die Individuen nicht gerade sind, wird noch aufgefüllt
//        if (retPop.getMIndividuals().size() % 2 == 1) {
//            List<Integer> newGenes = Arrays.asList(IntStream.range(0, amountGenes).boxed().toArray(Integer[]::new));
//            Collections.shuffle(newGenes);
//            retPop.getMIndividuals().add(IndividualFactory.factory.createIndividual().swapGenes(newGenes.stream().mapToInt(Integer::intValue).toArray()));
//        }
//        return retPop;
//    }

    Population crossOver() {
        Population retPop = new Population(amountGenes);
        Population approved = new Population(amountGenes);
        Population rejected = new Population(amountGenes);
        approved.setIndividuals(mIndividuals.stream().filter(Individual::isMApproved).collect(Collectors.toList()));
        rejected.setIndividuals(mIndividuals.stream().filter(Individual::isNotApproved).collect(Collectors.toList()));


        if (approved.getMIndividuals().size() % 2 == 1) {
            List<Integer> newGenes = Arrays.asList(IntStream.range(0, amountGenes).boxed().toArray(Integer[]::new));
            Collections.shuffle(newGenes);
            approved.addIndividual(IndividualFactory.factory.createIndividual().swapGenes(newGenes.stream().mapToInt(Integer::intValue).toArray()));
        }

        int length = 0;
        for (int i = 0; i < approved.getMIndividuals().size(); i+=2) {
            int[] newGene1 = new int[amountGenes];
            int[] newGene2 = new int[amountGenes];
            length = amountGenes/2;
            System.arraycopy(approved.getMIndividuals().get(i).getMGenes(),0,newGene1, 0, length);
            System.arraycopy(approved.getMIndividuals().get(i+1).getMGenes(),length,newGene2, length, amountGenes-length);
            for(int j=0, k=0;j<amountGenes && k < length;j++) {
                int value= approved.getMIndividuals().get(i).getMGenes()[j];
                if(Arrays.stream(newGene2).noneMatch(x-> x==value)) {
                    newGene2[k] = value;
                    k++;
                }
            }
            for(int j=0, k=length;j<amountGenes && k < amountGenes;j++) {
                int value= approved.getMIndividuals().get(i+1).getMGenes()[j];
                if(Arrays.stream(newGene1).noneMatch(x-> x==value)) {
                    newGene1[k] = value;
                    k++;
                }
            }

            Individual child1 = IndividualFactory.factory.createIndividual();
            Individual child2 = IndividualFactory.factory.createIndividual();
            child1.setMGenes(newGene1);
            child2.setMGenes(newGene2);
            retPop.addIndividual(child1);
            retPop.addIndividual(child2);
        }
        int i=0;
        while (retPop.getMIndividuals().size() < mIndividuals.size()) {
            retPop.addIndividual(rejected.getMIndividuals().get(i));
            i++;
        }
        return retPop;
    }

    Population mutate() {
        Population retPop = new Population(amountGenes);
        Random rand = new Random();
        for(Individual ind : mIndividuals) {
            int[] newProposal = new int[amountGenes];
            System.arraycopy(ind.getMGenes(),0,newProposal,0,amountGenes);
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
            retPop.addIndividual(IndividualFactory.factory.createIndividual().swapGenes(newProposal));
        }

        return retPop;
    }

    void createPopulation(int size) {
        for (int i = 0; i < size; i++) {
            List<Integer> list = Arrays.asList(IntStream.range(0, amountGenes).boxed().toArray(Integer[]::new));
            Collections.shuffle(list);
            Individual ind = IndividualFactory.factory.createIndividual().swapGenes(list.stream().mapToInt(Integer::intValue).toArray());
            ind.setMApproved(true);
            mIndividuals.add(ind);
        }
    }
}
