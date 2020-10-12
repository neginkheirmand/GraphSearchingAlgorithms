import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HamiltonianCycle {
    private int n, m;
    int[][] referenceMatrix;
    ArrayList<Integer[]> hamiltonianCycleContainer;

    public HamiltonianCycle(int n, int m) {
        this.n = n;
        this.m = m;
        hamiltonianCycleContainer = new ArrayList<>();
        makeGraph();
    }


    public void makeGraph() {
        referenceMatrix = new int[n * m][n * m];
        for (int i = 1; i <= n * m; i++) {
            for (int j = 1; j <= n * m; j++) {
                if (i == j) {
                    referenceMatrix[i - 1][j - 1] = 0;
                } else {
                    if (Cell.isNeighbor(i, j, n, m)) {
                        referenceMatrix[i - 1][j - 1] = 1;
                    } else {
                        referenceMatrix[i - 1][j - 1] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            if (i == 0) {
                System.out.printf("\033[0;31m" + "   ");
                for(int j=0; j<n*m; j++){
                    System.out.printf(""+(j+1)+" ");
                }
                System.out.println("\033[0m");
            }
            if (i < 9) {
                System.out.printf(" ");
            }
            System.out.printf("\033[0;31m%d \033[0m", i + 1);
            for (int j = 0; j < n * m; j++) {
                System.out.printf("%d ", referenceMatrix[i][j]);
                if (j >= 9) {
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }

    }

    public void hamiltonian(int k, int[] x) {
        //since i dont know where the start point will be, just let it be set in the function calling hamiltonian method
        do {
            nextVertex(k, x);
            if (x[k] == 0) {
                return;
            } else if (k == (n * m) - 1) {
                System.out.println("ended");
                for (int i = 0; i < n * m; i++) {
                    System.out.printf("\033[0;31m" + "%d " + "\033[0m", x[i]);
                }
                System.out.println();

                Integer[] container = new Integer[n * m];
                for (int y = 0; y < n * m; y++) {
                    container[y] = x[y];
                }
                hamiltonianCycleContainer.add(container);
                return;
            } else {
                hamiltonian(k + 1, x);
            }
        } while (true);
    }

    public void nextVertex(int k, int[] x) {
        //k >= 1 surely
        int sizePath = n * m;
        do {
            x[k] = (x[k] + 1) % (sizePath + 1);
            if (x[k] == 0) {
                return;
            }
            //there is an edge between this vertex and the one before this one
            if (referenceMatrix[x[k - 1] - 1][x[k] - 1] == 1) {
                //no repetitive values in the array
                boolean repet = false;
                for (int i = 0; i < k; i++) {
                    if (x[i] == x[k]) {
                        repet = true;
                        break;
                    }
                }
                if (!repet) {
                    if (k + 1 < sizePath || (k == sizePath - 1/* && referenceMatrix[x[k] - 1][x[0] - 1] == 1*/) ) {
                        return;
                    }
                }
            }
        } while (true);
    }

    public static void main(String[] args) {
        HamiltonianCycle ham = new HamiltonianCycle(4, 5);
        int[] x = new int[20];
        x[0]=1;
        for(int i=1; i<20; i++){
            x[i]=0;
        }
        Date bef = new Date();
        ham.hamiltonian(1, x);
        Date aft = new Date();
        System.out.println("How much did it take? : " + (aft.getTime() - bef.getTime())+"ms");
        System.out.println("print in main");

        for (int i = 0; i < ham.hamiltonianCycleContainer.size(); i++) {
            for (int j = 0; j < ham.n * ham.m; j++) {
                System.out.printf("%d ", ham.hamiltonianCycleContainer.get(i)[j]);
            }
//            System.out.printf( "%d\n",i+1);
            System.out.println();
        }
    }
}
