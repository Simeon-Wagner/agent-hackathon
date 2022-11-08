package geneticalgorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Population {
    Individual [] individuals;
    public Population() {

        individuals = create_population();
    }

    private Individual[] create_population() {
        //your code
        return null;
    }


}
