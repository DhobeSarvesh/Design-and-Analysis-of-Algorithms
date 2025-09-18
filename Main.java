import java.util.*;

public class Main {

    static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] a = new int[m + 1][n + 1];
        char[][] d = new char[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = '-';
        }
        for (int j = 0; j <= n; j++) {
            d[0][j] = '-';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    a[i][j] = a[i - 1][j - 1] + 1;
                    d[i][j] = 'D';
                } else if (a[i - 1][j] >= a[i][j - 1]) {
                    a[i][j] = a[i - 1][j];
                    d[i][j] = 'U';
                } else {
                    a[i][j] = a[i][j - 1];
                    d[i][j] = 'H';
                }
            }
        }

        System.out.println("---------------------Directional Matrix---------------------");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(a[i][j] + "." + d[i][j] + " ");
            }
            System.out.println();
        }

        String lcs = "";
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (d[i][j] == 'D') {
                lcs = s1.charAt(i - 1) + lcs;
                i--;
                j--;
            } else if (d[i][j] == 'U') {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("The LCS is: " + lcs);
        System.out.println("The length of LCS is: " + lcs.length());

        return lcs;
    }

    static String lrs(String s) {
        int m = s.length();
        int[][] dp = new int[m + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        String lrs = "";
        int i = m, j = m;
        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                lrs = s.charAt(i - 1) + lrs;
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("The Longest Repeating Subsequence (LRS) is: " + lrs);
        System.out.println("The length of LRS is: " + lrs.length());
        return lrs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter string X for LCS: ");
        String s1 = sc.nextLine();
        System.out.println("Enter string Y for LCS: ");
        String s2 = sc.nextLine();

        lcs(s1, s2);

        System.out.println("\nEnter a string for LRS: ");
        String s = sc.nextLine();
        lrs(s);
    }
}