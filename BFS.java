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

    //Function to perform Breadth first traversal VISUALLY :o
    public void breadthFirstTraversalVisual(int[][] adjacencyMatrix, int startingNodeID){
        boolean[] nodeVisited = new boolean[adjacencyMatrix.length];
        nodeVisited[startingNodeID] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNodeID);

        //Change starting node colour
        nodes.get(startingNodeID).setColour("RED");
        refresh();

        while(!queue.isEmpty()){
            int x = queue.poll();
            int i;
            for( i = 0; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[x][i] == 1 && nodeVisited[i] == false){
                    queue.add(i);
                    nodeVisited[i] = true;

                    //Change node colour
                    nodes.get(i).setColour("RED");
                    refresh();
                }
            }
        }
    }

    //Refresh mainArena after one second
    private void refresh(){
        try{
            Thread.sleep(1000);
            mainArena.update();
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}