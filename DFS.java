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

    //Function to perform Depth first traversal VISUALLY :o
    public void depthFirstTraversalVisual(int[][] adjacencyMatrix, int startingNodeID){
        boolean[] nodeVisited = new boolean[adjacencyMatrix.length];
        nodeVisited[startingNodeID] = true;

        Stack<Integer> stack = new Stack<>();
        stack.push(startingNodeID);

        //Change starting node colour
        nodes.get(startingNodeID).setColour("RED");
        refresh();

        int i, x;

        while(!stack.isEmpty()){
            x = stack.pop();
            for(i = 0; i < adjacencyMatrix.length; i++){
                if(adjacencyMatrix[x][i] == 1 && nodeVisited[i] == false){
                    stack.push(x);
                    nodeVisited[i] = true;
                    //Change node colour
                    nodes.get(i).setColour("RED");
                    refresh();
                    x = i++;
                    i = -1;
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