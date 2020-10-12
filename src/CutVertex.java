import java.util.ArrayList;

public class CutVertex {
    //find the cut vertex on a square looking map

    //if -1 its a wall, else if 0 its a node of the graph
    int[][] graph;
    ArrayList<Integer> cutVertexes;

    public CutVertex(int[][]graph){
        this.graph=graph;
        cutVertexes=new ArrayList<>();

    }


}
