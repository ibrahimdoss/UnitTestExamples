package fibonacciProblem;

public class FibonacciNumber {

    public int find(int order) {

        if (order <= 0) {
            throw new IllegalArgumentException();
        }

        if (order == 1 || order == 2) {
            return 1;
        }
        return find(order - 2) + find(order - 1);
    }
}
