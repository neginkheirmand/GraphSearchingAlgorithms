import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HamiltonianCycle {
    private int n, m;
    int[][] referenceMatrix;

    public HamiltonianCycle(int n, int m){
        this.n=n;
        this.m=m;
        makeGraph();
    }

    public void printHamiltonianCycles(){
        ArrayList<Integer[]> hamiltonianCycles = new ArrayList<>();
        Integer[] cylce = new Integer[n*m];

    }

    public void makeGraph(){
        referenceMatrix = new int[n*m][n*m];
        for(int i=1; i<=n*m; i++){
            for(int j=1; j<=n*m ; j++){
                if(i==j){
                    referenceMatrix[i-1][j-1]=0;
                }else{
                    if(Cell.isNeighbor(i,j, n, m)){
                        referenceMatrix[i-1][j-1]=1;
                    }else {
                        referenceMatrix[i-1][j-1]=0;
                    }
                }
            }
        }

        for(int i=0; i<n*m; i++){
            if(i==0){
                System.out.println("\033[0;31m"+"   1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20   agar ke n=4 va m=5"+"\033[0m");
            }
            if(i<9){
                System.out.printf(" ");
            }
            System.out.printf("\033[0;31m%d \033[0m", i+1);
            for(int j=0; j<n*m; j++) {
                System.out.printf("%d ", referenceMatrix[i][j]);
                if(j>=9){
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }

    }

    public void hamiltonian(int k, int[] x){
        //since i dont know where the start point will be, just let it be set in the function calling hamiltonian method
        do{
            nextVertex(k, x);
            if(x[k]==0){
                return;
            }else if(k==(n*m)-1){
                for(int i=0; i<n*m; i++) {
                    System.out.printf("%d ", x[i]);
                }
                System.out.println();
                return;
            }else {
                hamiltonian(k+1, x);
            }
        }while(true);
    }

    public void nextVertex(int k, int[] x) {
        //k >= 1 surely
        System.out.println("came here for k = "+k);
        System.out.println("enter with x:");
        for(int i=0; i<n*m; i++) {
            System.out.printf("%d ", x[i]);
        }
        System.out.println();
        int sizePath = n * m;
        do {
            x[k] = (x[k] + 1) % (sizePath + 1);
//            System.out.println("gonna try with"+x[k]);
            if (x[k] == 0) {
                System.out.println("invalid because x[k] is 0 :");
                for(int i=0; i<n*m; i++) {
                    System.out.printf("%d ", x[i]);
                }
                System.out.println();
                return;
            }
            //there is an edge between this vertex and the one before this one
            if (referenceMatrix[x[k - 1] - 1][x[k] - 1] == 1) {
//                System.out.println("its edge");
                //no repetitive values in the array
                boolean repet = false;
                for (int i = 0; i < k; i++) {
                    if (x[i] == x[k]) {
                        repet = true;
//                        System.out.println("repeted");
                        break;
                    }
                }
                if (!repet) {
                    if (k + 1 < sizePath || (k == sizePath - 1 && referenceMatrix[x[k]][x[0]] == 1)) {
                        System.out.println("come out with x:");
                        for(int i=0; i<n*m; i++) {
                            System.out.printf("%d ", x[i]);
                        }
                        System.out.println();
                        return;
                    }
                }
//            }else{
//                System.out.println("not edge");
            }
        } while (true);
    }

    public void visualize(int[] cycle){

    }

    public static void drawMap(){
        JFrame map = new JFrame("Game Map");
        map.setPreferredSize(new Dimension(100, 100));
        map.setMinimumSize(new Dimension(100, 100));
        map.setMaximumSize(new Dimension(100, 100));
        map.setVisible(true);
        map.setLocationRelativeTo(null);
        map.setBackground(new java.awt.Color(11, 11, 11));
        map.setForeground(new java.awt.Color(11, 11, 11));
        map.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
//        HamiltonianCycle ham = new HamiltonianCycle(4, 4);
//        System.out.println("gonna call the method for the hamiltonian graph");
//        int[]x = new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
////        int[]x = new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        ham.hamiltonian(1, x);
//        System.out.println("print in main");
//        for(int i=0; i<16; i++) {
//            System.out.printf("%d ", x[i]);
//        }

        System.out.println("\n15 and 16 are neighbors?"+Cell.isNeighbor(15, 16, 4, 4));
    }
}
