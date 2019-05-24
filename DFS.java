import java.util.*;

public class DFS extends Main{

    //Function to perform Depth first traversal
    public void depthFirstTraversal(int[][] adjacencyMatrix, int startingNodeID){
        boolean[] nodeVisited = new boolean[adjacencyMatrix.length];
        nodeVisited[startingNodeID] = true;

        Stack<Integer> stack = new Stack<>();
        stack.push(startingNodeID);

        int i, x;
        System.out.println("Starting from " + (nodes.get(startingNodeID).returnName()));
        System.out.print(nodes.get(startingNodeID).returnName() + " ");

        while(!stack.isEmpty()){
            x = stack.pop();
            for(i = 0; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[x][i] == 1 && nodeVisited[i] == false){
                    stack.push(x);
                    nodeVisited[i] = true;
                    System.out.print((nodes.get(i).returnName()) + " ");
                    x = i++;
                    i = -1;
                }
            }
        }
        System.out.println("");
    }
}