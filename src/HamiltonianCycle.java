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
                if(j>9){
                    System.out.printf(" ");
                }
            }
            System.out.println();
        }

    }

    public void hamiltonian(int k){

    }

    public void nextVertex(int k, int[] x){
        //k >= 1 surely
        do{
            x[k]=(x[k]+1)%((n*m)+1);
            if(x[k]==0){
                return;
            }
//            if(referenceMatrix[x[k-1]][x[]])

        }while(true);
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
        HamiltonianCycle ham = new HamiltonianCycle(4, 5);

    }
}
