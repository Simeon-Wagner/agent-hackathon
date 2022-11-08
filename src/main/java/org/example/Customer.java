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
    public Customer(int[][] jobSequence) {
        this.jobSequence = jobSequence;
        time_contract = 0;
    }


    private int time_contract;

    public void set_contract(int [] contract){
        time_contract = get_time(contract);
    }
    public boolean vote (int [] contract, int [] proposal){
        set_contract(contract);
        if( get_time(proposal) < time_contract){
            return true;
        }
        return false;
    }
    public int get_time( int [] proposal){
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
