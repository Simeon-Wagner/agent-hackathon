package geneticalgorithm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Individual {

    int [] genome;
    int fitness;

    public Individual(int[] genome) {
        this.genome = genome;
        fitness = calculate_fitness();
    }

    private int calculate_fitness() {
        //your code
        return 0;
    }
}
