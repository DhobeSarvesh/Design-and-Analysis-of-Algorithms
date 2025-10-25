import java.util.*;

public class OBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of keys: ");
        int n = sc.nextInt();

        System.out.println("Enter the keys: ");
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();
        }

        System.out.println("Enter the probability of successful searches: ");
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextDouble();
        }

        System.out.println("Enter the probability of unsuccessful searches: ");
        double[] q = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            q[i] = sc.nextDouble();
        }

        double[][] cost = new double[n + 1][n + 1];
        double[][] weight = new double[n + 1][n + 1];

        // Base cases
        for (int i = 0; i <= n; i++) {
            cost[i][i] = q[i];
            weight[i][i] = q[i];
        }

        // OBST
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len;
                cost[i][j] = Double.MAX_VALUE;
                weight[i][j] = weight[i][j - 1] + p[j - 1] + q[j];

                for (int r = i; r < j; r++) {
                    double temp = cost[i][r] + cost[r + 1][j] + weight[i][j];
                    if (temp < cost[i][j]) {
                        cost[i][j] = temp;
                    }
                }
            }
        }

        System.out.printf("Minimum expected cost of OBST: %.4f\n", cost[0][n]);
    }
}
