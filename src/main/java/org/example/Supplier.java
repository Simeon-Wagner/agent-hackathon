package org.example;

public class Supplier {


    public Supplier(int[][] costMatrix, int rounds, double mustAccept) {
        this.costMatrix = costMatrix;
        this.rounds = rounds;
        this.mustAccept = mustAccept;
        this.roundNr = 0;
        this.roundsAccepted = 0;
    }
    public Supplier(int[][] costMatrix) {
        this.costMatrix = costMatrix;
    }
    public void setCostMatrix(int[][] costMatrix) {
        this.costMatrix = costMatrix ;
    }
    private int [][]costMatrix;
    int rounds;
    int roundNr;
    int roundsAccepted;
    double mustAccept;
    public  int getTime(int [] proposal) {
        int time = 0;
        for(int i = 0; i< proposal.length-1; i++){
            time += (costMatrix[proposal[i]][proposal[i+1]]);
        }
        return time;
    }
    public boolean vote(int [] contract, int[] proposal){
        roundNr++;
        int timeP = getTime(proposal);
        int timeC = getTime(contract);
        double relDif = (timeP-timeC)/timeC;
        if(timeP<timeC) {
            roundsAccepted++;
            return true;
        } else if (getAcceptanceRate()<mustAccept&&(relDif<0.1)) {
            roundsAccepted++;
            return true;
        }else if((rounds-roundNr)==(rounds*mustAccept-roundsAccepted)) {
            roundsAccepted++;
            return true;
        }else {
            return false;
        }
    }
    double getAcceptanceRate (){
        return roundsAccepted/roundNr;
    }
  /*  public boolean vote (int [] contract, int[] proposal){
        return getTime(proposal) < getTime(contract);
    }*/
}
