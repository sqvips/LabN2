import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) {

        int N = in.nextInt();
        int M = in.nextInt();

        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean uni = true;
                for (int k = 0; k < i; k++) {
                    for (int l = 0; l < M; l++) {
                        if (a[k][l] == a[i][j]) {
                            uni = false;
                            break;
                        }
                    }
                    if (!uni) {
                        break;
                    }
                }
                for (int l = 0; l < j; l++) {
                    if (a[i][l] == a[i][j]) {
                        uni = false;
                        break;
                    }
                }
                if (uni)
                    count++;
            }
        }

        out.println("Кол-во уник: " + count);

        int[] sums = new int[M];
        int[] unicount = new int[M];

        for (int j = 0; j < M; j++) {
            int sum = 0;
            int countJ = 0;

            for (int i = 0; i < N; i++) {
                sum += a[i][j];

                boolean isUnique = true;
                for (int k = 0; k < i; k++) {
                    if (a[k][j] == a[i][j]) {
                        isUnique = false;
                        break;
                    }
                }

                if (isUnique) {
                    countJ++;
                }
            }

            sums[j] = sum;
            unicount[j] = countJ;
        }

        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j< M - 1 - i; j++) {
                if (sums[j] > sums[j + 1] || (sums[j] == sums[j + 1] && unicount[j] < unicount[j + 1])) {

                    int temp = sums[j];
                    sums[j] = sums[j + 1];
                    sums[j + 1] = temp;

                    int tempus = unicount[j];
                    unicount[j] = unicount[j + 1];
                    unicount[j + 1] = tempus;

                    for (int d = 0; d < N; d++) {
                        int temp1 = a[d][j];
                        a[d][j] = a[d][j + 1];
                        a[d][j + 1] = temp1;
                    }
                }
            }
        }
        
        int n = a.length;
        int m = a[0].length;

        for (int col = 0; col < m; col++) {
            int x = 0, y = col;
            while (x < n && y >= 0) {

                int num = a[x][y];
                long factorial = 1;

                if (num < 0) {
                    System.out.print("Factorial: 0 ");
                } else {
                    for (int i = 1; i <= num; i++) {
                        factorial *= i;
                    }
                    System.out.print(factorial + " ");
                }

                x++;
                y--;
            }
            out.println();
        }

        for (int row = 1; row < n; row++) {
            int x = row, y = m - 1;
            while (x < n && y >= 0) {

                int num = a[x][y];
                long factorial = 1;

                for (int i = 1; i <= num; i++) {
                    factorial *= i;
                }
                System.out.print(factorial + " ");

                x++;
                y--;
            }
            out.println();
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                out.print(" " + a[i][j] + " ");
            }
            out.println();
        }
    }
}
