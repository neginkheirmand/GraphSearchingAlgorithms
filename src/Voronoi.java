public class Voronoi {
    private final int redAgent = 1;
    private final int blueAgent = -1;
    private final int equal = 0;
    private int[][] voronoiDiagram;

    public Voronoi(int n, int m, int xRed, int yRed, int xBlue, int yBlue) {
        voronoiDiagram = new int[n][m];
        //every house of this bidimensional array is started to 0
        if (xRed == xBlue) {
            System.out.println("with equal x-axes");
            int up = 0;
            int down = 0;
            if(yBlue>yRed) {
                up = blueAgent;
                down = redAgent;
            }else {
                up = redAgent;
                down = blueAgent;

            }
            int min;
            int max;
            if((yBlue+yRed)%2==0) {
                min = ((yBlue + yRed) / 2) -1;
                max = ((yBlue + yRed) / 2);
            }else{
                min = ((yBlue + yRed) / 2);
                max = ((yBlue + yRed) / 2)-1;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i <= min) {
                        voronoiDiagram[i][j]=down;
                    } else if (i > max) {
                        voronoiDiagram[i][j]=up;
                    }
                }
            }
            voronoiDiagram[yRed][xRed] = redAgent+1;
            voronoiDiagram[yBlue][xBlue] = blueAgent-1;

            return;
        }

        float a = ((float)(yRed - yBlue) )/ (xRed - xBlue);
        System.out.println("a is"+a);
        //the slope

        float xMid = (xRed + xBlue) / 2;
        float yMid = (yRed + yBlue) / 2;

        if (a != 0) {
            float aPrime = -1 / a;

            //the vertical intercept
            float yintercept = yMid - (aPrime * xMid);

            int firstColor = 0;
            int lastColor = 0;

            if(yRed-((aPrime*xBlue)+yintercept)>0){
                firstColor=blueAgent;
                lastColor=redAgent;
            }else if(yRed-((aPrime*xBlue)+yintercept)<0){
                firstColor=redAgent;
                lastColor=blueAgent;
            }
            for (int i = 0; i < m; i++) {
                int minY = 0;
                int maxY = 0;
                if(aPrime>0) {
                    minY = (int) ((aPrime * (i + 0.01)) + yintercept);
                    maxY = (int) ((aPrime * (i + 0.99)) + yintercept);
                }else{
                    minY = (int) ((aPrime * (i + 0.01)) + yintercept)+1;
                    maxY = (int) ((aPrime * (i + 0.99)) + yintercept);
                }
                if(minY>maxY){
                    System.out.println(i);
                    int con = minY;
                    minY=maxY;
                    maxY=con;
                }
                System.out.println("in column"+i+" the min of line is"+minY+" and the max of line is"+maxY);
                for (int j = 0; j < n; j++) {
                    if (j <= minY) {
                        voronoiDiagram[j][i]=firstColor;
                    } else if (j > maxY) {
                        voronoiDiagram[j][i]=lastColor;
                    }else{
                        int disRed=getDistance(xRed, yRed, i, j);
                        int disBlue=getDistance(xBlue, yBlue, i, j);
                        if(disBlue==disRed){
                            continue;
                        }else if(disBlue<disRed){
                            voronoiDiagram[j][i]=blueAgent;
                        }else{
                            voronoiDiagram[j][i]=redAgent;
                        }
                    }
                }
            }
            voronoiDiagram[yRed][xRed] = redAgent+1;
            voronoiDiagram[yBlue][xBlue] = blueAgent-1;
            return;
        } else {
            System.out.println("with equal y-axes");
            //vertical line
            int close = 0;
            int far = 0;
            if (xRed < xBlue) {
                close = redAgent;
                far = blueAgent;
            } else {
                close = blueAgent;
                far = redAgent;
            }
            int min, max;
            if((xRed+xBlue)%2==0){
                min = ( (xRed+xBlue)/2 )-1;
                max = (xRed+xBlue)/2;
            }else{
                min = (xRed+xBlue)/2;
                max = ((xRed+xBlue)/2)-1;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j <= min) {
                        voronoiDiagram[i][j] = close;
                    } else if (j > max) {
                        voronoiDiagram[i][j] = far;
                    }
                }
            }
            voronoiDiagram[yRed][xRed] = redAgent+1;
            voronoiDiagram[yBlue][xBlue] = blueAgent-1;
            return;
        }
    }

    private int getDistance(int x1, int y1, int x2, int y2){
        return ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2));
    }

    public int[][] getVoronoiDiagram() {
        return voronoiDiagram;
    }

    public static void main(String[] args) {
        Voronoi v = new Voronoi(7, 7, 1, 5, 4, 0);
        int[][] diagram = v.getVoronoiDiagram();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (diagram[i][j] < 0) {
                    System.out.printf(" %d", diagram[i][j]);
                } else {
                    System.out.printf("  %d", diagram[i][j]);
                }
            }
            System.out.println();
        }
    }

}
