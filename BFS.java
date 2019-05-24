import java.util.*;

public class BFS extends Main{

    //Function to perform Breadth first traversal
    public void breadthFirstTraversal(int[][] adjacencyMatrix, int startingNodeID){
        boolean[] nodeVisited = new boolean[adjacencyMatrix.length];
        nodeVisited[startingNodeID] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNodeID);

        System.out.println("Starting from " + (nodes.get(startingNodeID).returnName()));

        while(!queue.isEmpty()){
            System.out.print(nodes.get(queue.peek()).returnName() + " ");
            int x = queue.poll();
            int i;
            for( i = 0; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[x][i] == 1 && nodeVisited[i] == false){
                    queue.add(i);
                    nodeVisited[i] = true;
                }
            }
        }
        System.out.println("");
    }
}