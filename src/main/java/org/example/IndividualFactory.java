package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualFactory {
    static IndividualFactory factory;
    private static int geneLength;

    private IndividualFactory(int jobsAmount) {
        geneLength = jobsAmount;
    }

    public static void createFactory(int jobsAmount) {
        factory = new IndividualFactory(jobsAmount);
    }
    public Individual createIndividual() {
        return new Individual(geneLength);
    }

    @Getter
    @Setter
    static class Individual {
        private static int id = 0;
        private int[] mGenes;
        private int mId;
        private boolean mApproved;

        Individual(int geneLength) {
            mId = id;
            id++;
            mApproved = false;
            mGenes = new int[geneLength];
        }
        Individual swapGenes(int[] genes) {
            System.arraycopy(genes,0,mGenes, 0, genes.length);
            mApproved = false;
            return this;
        }
        boolean isNotApproved() {
            return !mApproved ;
        }
    }
}
