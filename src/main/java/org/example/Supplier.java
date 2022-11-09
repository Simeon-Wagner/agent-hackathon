package org.example;

public class Supplier {


    public Supplier(int[][] costMatrix, int rounds, double mustAccept) {
        this.costMatrix = costMatrix;
        this.rounds = rounds;
        this.mustAccept = mustAccept;
        this.roundNr = 0;
        this.roundsAccepted = 0;
        this.variable = 1;
        this.canDeny = 1-mustAccept;
        this.decrement = 1/(rounds*canDeny);
    }
    public Supplier(int[][] costMatrix) {
        this.costMatrix = costMatrix;
    }

    private int [][]costMatrix;
    int rounds;
    int roundNr;
    int roundsAccepted;
    double mustAccept;

    double variable;
    double canDeny;
    double decrement;

    public  double getTime(Integer [] proposal) {
        double time = 0;
        for(int i = 0; i< proposal.length-1; i++){
            time += (costMatrix[proposal[i]][proposal[i+1]]);
        }
        return time;
    }
    public boolean vote(Integer [] contract, Integer[] proposal){
        roundNr++;
        double quotient_x = (getTime(proposal)- getTime(contract))/getTime(contract);
        double exponent =(-quotient_x*variable)*20; //*6.5
        double result = Math.pow(Math.E,exponent);
        double random = Math.random();
        if(random <= result){
            roundsAccepted++;
            return true;
        }else{
            variable = variable - decrement;
            return false;
        }
    }
    double getAcceptanceRate (){
        return roundsAccepted/roundNr;
    }

}
