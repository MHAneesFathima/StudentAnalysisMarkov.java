import java.util.Arrays;

public class StudentActivityMarkov {

    static String[] activities = {
        "Class", "Study", "Assignment", "Break"
    };

    // Transition probability matrix
    static double[][] P = {
        {0.10, 0.40, 0.30, 0.20},
        {0.25, 0.35, 0.25, 0.15},
        {0.20, 0.40, 0.20, 0.20},
        {0.30, 0.25, 0.15, 0.30}
    };

    // Initial state vector
    static double[] initialState = {
        0.25, 0.35, 0.25, 0.15
    };

    public static void main(String[] args) {

        System.out.println("STUDENT ACTIVITY MARKOV ANALYSIS SYSTEM");

        double[] state = initialState.clone();

        printMatrix();

        printVector("Initial State", state);

        // Calculate future states
        for (int step = 1; step <= 20; step++) {

            state = multiply(state, P);

            if (step == 1 || step == 5 ||
                step == 10 || step == 20) {

                printVector("After Step " + step, state);
            }
        }

        // Calculate steady state
        double[] steady = findSteadyState();

        printVector("Steady State Distribution", steady);

        // Verify steady state
        double[] verification = multiply(steady, P);

        printVector("Verification (Steady State * P)",
                    verification);

        System.out.println("\nMaximum verification error: "
                + difference(steady, verification));
    }

    // Matrix multiplication: Vector * Matrix
    public static double[] multiply(
            double[] vector, double[][] matrix) {

        int n = vector.length;
        double[] result = new double[n];

        for (int j = 0; j < n; j++) {

            for (int i = 0; i < n; i++) {

                result[j] += vector[i] * matrix[i][j];
            }
        }

        return result;
    }

    // Find steady state using repeated multiplication
    public static double[] findSteadyState() {

        double[] state = {
            0.25, 0.25, 0.25, 0.25
        };

        for (int i = 0; i < 10000; i++) {

            double[] next = multiply(state, P);

            if (difference(state, next) < 1e-12) {
                return next;
            }

            state = next;
        }

        return state;
    }

    // Calculate maximum difference between vectors
    public static double difference(double[] a, double[] b) {

        double max = 0;

        for (int i = 0; i < a.length; i++) {

            max = Math.max(max, Math.abs(a[i] - b[i]));
        }

        return max;
    }

    // Display transition matrix
    public static void printMatrix() {

        System.out.println("\nTRANSITION MATRIX:");

        for (double[] row : P) {

            for (double value : row) {
                System.out.printf("%8.2f", value);
            }

            System.out.println();
        }
    }

    // Display state vector
    public static void printVector(
            String title, double[] vector) {

        System.out.println("\n" + title);

        for (int i = 0; i < vector.length; i++) {

            System.out.printf(
                "%-12s : %.4f (%6.2f%%)%n",
                activities[i],
                vector[i],
                vector[i] * 100
            );
        }
    }
}