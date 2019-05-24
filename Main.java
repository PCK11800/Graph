import java.util.*;

public class Main{
    
    //Initialization
    public static Main main = new Main();
    public static GameArena mainArena = new GameArena(1000, 1000, true);
    private static Scanner scanner = new Scanner(System.in);
    private static DFS dfs = new DFS();
    private static BFS bfs = new BFS();

    //Node arrayList 
    //To access node: nodes.get(i).methods();
    public static ArrayList<Node> nodes = new ArrayList<Node>();

    //Arrays
    public static String[][] directedrelationArray; //Adjacency matrix with margins showing only directed relations
    public static String[][] relationArray; //Adjacency matrix with margins showing all relations
    public static String[] connectedArray; //Array required to print connections for each node
    public static int[][] adjacencyMatrix; //Pure adjacency matrix

    //Variables
    private static int numNodes;
    private static String graphType;

    //MLF - Function to set(add) nodes to program.
    public void setNodes(){

        //Determine total amount of nodes in the graph
        System.out.println("Input node amount:");
        numNodes = scanner.nextInt();

        //Setup array size and its specifics
        arraySetup(numNodes);

        //Create, name and add the nodes to the arraylist
        System.out.println("Name the nodes. Single letter/digit only.");
        for(int i = 0; i < numNodes; i++){
            String name = scanner.next();
            Node node = new Node(name, i); //Set up a node and id in the arraylist
            nodes.add(node);

            //Add the node to the margins of drA and rA
            directedrelationArray[0][i+1] = nodes.get(i).returnName();
            directedrelationArray[i+1][0] = nodes.get(i).returnName();
            relationArray[0][i+1] = nodes.get(i).returnName();
            relationArray[i+1][0] = nodes.get(i).returnName();
        }

        //Confirm to User
        System.out.println("All nodes set.");
        initializeNodes();
    }

    //Function to initialize array to specific size and prepare them for future printing/usage in other functions
    private void arraySetup(int numNodes){

        //Initialize Array Size
        directedrelationArray = new String[numNodes + 1][numNodes + 1];
        relationArray = new String[numNodes + 1][numNodes + 1];
        adjacencyMatrix = new int[numNodes][numNodes];

        //Setup drA and rA
        for(String[] row : relationArray){
            Arrays.fill(row, "0");
        }

        for(String[] row : directedrelationArray){
            Arrays.fill(row, "0");
        }

        relationArray[0][0] = "~";
        directedrelationArray[0][0] = "~";
    }

    //Function to initialize the nodes to the mainArena
    private void initializeNodes(){
        for(int i = 0; i < numNodes; i++){
            nodes.get(i).addNode();
        }
    }

    //MLF - Function to set graph type.
    private void setGraphType(){

        //Choose between simple arcs or directed arcs
        System.out.println("Choose between simple or directed graph.");
        String inputType = scanner.next();

        //Incorrect input error handling
        if(!inputType.equals("simple") && !inputType.equals("directed")){
            System.out.println("No graph type inputted.");
            setGraphType(); //Return to the top.
        }
        else{
            graphType = inputType;
        }
    }

    //MLF - Function to set node relations
    //Determine fromNode and toNode (fromNode is directed towards toNode)
    //Does not matter if a simple graph, but use the same code regardless
    private void setNodeRelations(){

        //Commands
        System.out.println("");
        System.out.println("Set arcs. Enter starting node before end node.");
        System.out.println("Repeat until done. End by entering end twice.");
        System.out.println("");

        //Show all inputted nodes
        System.out.println("Inputted Nodes");
        for(int i = 0; i < numNodes; i++){
            String nodeName = nodes.get(i).returnName();
            System.out.print(nodeName + " "); 
        }

        //newline to make terminal look prettier
        System.out.println("");
        System.out.println("");
        
        while(true){

            String fromNode, toNode; //Name of node
            int fromNodeINT, toNodeINT; //ID of node

            fromNode = scanner.next();
            System.out.println("to");
            toNode = scanner.next();
            System.out.println("");

            //If completed...
            if(fromNode.equals("end") && toNode.equals("end")){
                break;
            }
            //else add connection to nodes.
            else{
                fromNodeINT = findIndex(fromNode);
                toNodeINT = findIndex(toNode);
            }

            //Add arcs - either simple or directed
            if(graphType.equals("simple")){
                Arc arc = new Arc();
                arc.addArc(fromNodeINT, toNodeINT);
            }
            else if (graphType.equals("directed")){
                Arc arc = new Arc();
                arc.addDirectedArc(fromNodeINT, toNodeINT);
            }

            //Add relation to drA, rA and adjacencyMatrix
            directedrelationArray[toNodeINT + 1][fromNodeINT + 1] = "1";
            relationArray[toNodeINT + 1][fromNodeINT + 1] = "1";
            relationArray[fromNodeINT + 1][toNodeINT + 1] = "1";
            adjacencyMatrix[fromNodeINT][toNodeINT] = 1;
            adjacencyMatrix[toNodeINT][fromNodeINT] = 1;

            //Add relation to this specific node.
            if(fromNodeINT == toNodeINT){
                nodes.get(fromNodeINT).addConnection(nodes.get(toNodeINT));
            }
            else{
                nodes.get(fromNodeINT).addConnection(nodes.get(toNodeINT));
                nodes.get(toNodeINT).addConnection(nodes.get(fromNodeINT));
            }
        }

        //Update mainArena
        mainArena.update();
    }

    //MLF - Function that prints out all the array, nodes connection and graph traversals
    //Oh, also ends the program.
    private void end(){
        mainArena.update();
        System.out.println("");

        //Print rA
        System.out.println("Relations Table");
        System.out.println("");
        System.out.println(Arrays.deepToString(relationArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        System.out.println("");

        //Print drA
        System.out.println("Directed Relations Table");
        System.out.println("The nodes on the left margins have a directed connection towards those on the top.");
        System.out.println("");
        System.out.println(Arrays.deepToString(directedrelationArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        System.out.println("");

        //Print all connections of each specific node
        printNodeConnections();
        System.out.println("");

        //Print DFT and BFT with a specific starting node.
        System.out.println("Choose a starting node for DFT and BFT.");
        String nodeName = scanner.next();
        //Code to do DFT and BFT
        int startingNodeID = findIndex(nodeName);

        System.out.println("DFT:");
        dfs.depthFirstTraversal(adjacencyMatrix, startingNodeID);

        System.out.println("");
        
        System.out.println("BFT");
        bfs.breadthFirstTraversal(adjacencyMatrix, startingNodeID);

        System.out.println("");

        //Do DFT or BFT VISUALLY
        System.out.println("Which one visually: DFT or BFT?");
        String visualChoice = scanner.next();
        if(visualChoice.equals("DFT")){
            dfs.depthFirstTraversalVisual(adjacencyMatrix, startingNodeID);
        }
        else if(visualChoice.equals("BFT")){
            bfs.breadthFirstTraversalVisual(adjacencyMatrix, startingNodeID);
        }

        //Close the scanner!
        scanner.close();
    }

    //Function to print all connections of all nodes
    private void printNodeConnections(){
        for(int i = 0; i < numNodes; i++){
            int connectedNum = nodes.get(i).returnConnectionsNumber();
            connectedArray = new String[connectedNum];

            nodes.get(i).returnConnections();
            String nodeName = nodes.get(i).returnName();

            System.out.println("Node " + nodeName + " is connected with:");
            System.out.println(Arrays.toString(connectedArray));
        }
    }

    //Function to return index in node arraylist by name
    public int findIndex(String nameInput){
        int arrayLength = numNodes;
        int i = 0;

        while (i < arrayLength){
            if((nodes.get(i).returnName()).equals(nameInput)){
                return i;
            }
            else{
                i++;
            }
        }
        return -1;
    }

    //MAIN LOGIC FUNCTION
    public void mainLogic(){
        setNodes();
        setGraphType();
        setNodeRelations();
        end();
    }

    public static void main(String[] args){
        main.mainLogic();
    }

}