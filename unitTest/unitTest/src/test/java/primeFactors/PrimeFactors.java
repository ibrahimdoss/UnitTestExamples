package primeFactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeFactors {

    public static List<Integer> generate(int i) {
        if (i <= 1) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> primeFactors = new ArrayList<>();
        for (int candidate = 2; i > 1; candidate++) {
            for (; i % candidate == 0; i /= candidate) {
                primeFactors.add(candidate);
            }
        }
        return primeFactors;
    }
}
