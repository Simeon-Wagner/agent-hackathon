package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    int [][] jobSequence;
    double mustAccept;
    int time_contract;
    int roundNr;
    int rounds;
    int roundsAccepted;

    public Customer(int[][] jobSequence) {
        this.jobSequence = jobSequence;
        time_contract = 0;
    }
    public Customer (int [][] jobSequence, int rounds, double mustAccept){
        this.jobSequence = jobSequence;
        time_contract = 0 ;
        this.rounds = rounds;
        this.mustAccept = mustAccept;
        this.roundNr = 0;

    }

    public void set_contract(Integer [] contract){
        time_contract = get_time(contract);
    }
    public boolean vote (Integer [] contract, Integer [] proposal){
        set_contract(contract);
        roundNr++;
        int timeP = get_time(proposal);
        int timeC = get_time(contract);
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
    private double getAcceptanceRate (){
        return roundsAccepted/roundNr;
    }
    public int get_time( Integer [] proposal){
        //Zeilen sind Jobs

        int [] end_point = calculate_end_point(jobSequence[proposal[0]]);

        for (int i = 1; i < proposal.length; i++) {
            int [] temp = calculate_temp(end_point, jobSequence[proposal[i]]);
           while(! approve(temp, end_point)) {
                for(int j = 0; j< temp.length; j++){
                    temp[j]++;
                }
            }
            end_point = temp;
        }

        return end_point[end_point.length-1];
    }

    private int[] calculate_temp(int[] end_point, int[] jobs) {

        int [] temp = new int[end_point.length];
        temp[0] = jobs[0]+end_point[0];
        for (int i = 1; i < end_point.length; i++) {
            temp [i] = temp[i-1] + jobs[i];
        }
        return temp;
    }

    private int [] calculate_end_point(int [] sequence ){
        int [] end_points = new int[sequence.length];
        int zahl = 0;
        for (int i = 0; i < sequence.length; i++) {
            zahl = zahl + sequence[i];
            end_points[i]= zahl;
        }

        return end_points;
    }
    private boolean approve(int [] temp, int [] end_points){
        for(int i = 0; i < temp.length; i++){
            if(i < temp.length-1 && temp[i] < end_points[i+1]){
                return false;
            }
        }
        return true;
    }
}
