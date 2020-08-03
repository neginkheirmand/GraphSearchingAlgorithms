public class Cell {
    public static boolean isNeighbor(int numCell, int neighbor, int n, int m){

        if(neighbor>n*m){
            return false;
        }

        //the counting of the vertex starts with 1

        //first we check if its upper neighbor
        if(numCell>m){
//            System.out.println(1);
            //might be the upper neighbor
            if(numCell-m==neighbor){
                return true;
            }
        }
        if(numCell<=(n-1)*m){
//            System.out.println(2);
            //might be the downSide neighbor
            if(numCell+m==neighbor){
                return true;
            }
        }
        if((numCell%m)!=1){
//            System.out.println(3);
            //might be the left side neighbor
            if(numCell-neighbor==1){
                return true;
            }
        }
        if(numCell%m!=0){
//            System.out.println(4);
            //might be the right side neighbor
            if(neighbor-numCell==1) {
                return true;
            }
        }
        return false;
    }

    private int getRow(int numCell, int m){
        //n or m are not zero
        return numCell/m;
    }

    private int getColumn(int numCell, int m){
        return numCell%m;
    }

    static void change(int x[]){
        x[0]=1;
        x[1]=1;
        x[2]=2;
    }

    public static void main(String[] args) {
//        int[] x = new int[]{0,0,0,0,0};
//        System.out.println("x before ");
//        for(int i=0; i<x.length; i++){
//            System.out.printf("%d", x[i]);
//        }
//        change(x);
//        System.out.println("x after ");
//        for(int i=0; i<x.length; i++){
//            System.out.printf("%d", x[i]);
//        }
    }
}
