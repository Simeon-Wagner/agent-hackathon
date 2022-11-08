package org.example;

public class Supplier {


    public Supplier(int[][] costMatrix) {
        this.costMatrix = costMatrix;
    }
    public void setCostMatrix(int[][] costMatrix) {
        this.costMatrix = costMatrix ;
    }
    private int [][]costMatrix = {{0, 6, 8, 2}, {4, 0, 2, 3},{3, 7, 0, 6},{2, 8, 4, 0}};
    public  int getTime(int [] proposal) {
        int time = 0;
        for(int i = 0; i< proposal.length-1; i++){
            time += (costMatrix[proposal[i]][proposal[i+1]]);
        }
        return time;
    }
    public boolean vote (int [] contract, int[] proposal){
        return getTime(proposal) < getTime(contract);
    }
}
