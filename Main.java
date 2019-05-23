import java.util.*;

public class Main{
    public static Main main = new Main();

    //GameArena
    public static GameArena mainArena = new GameArena(1000, 1000, true);

    //Node and Arc ArrayList
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public static ArrayList<Arc> arcs = new ArrayList<Arc>();
    /* To access node: nodes.get(i).methods(); */

    //Node Relations array
    public static String[][] relationArray;
    public static String[] connectedArray;

    //Variables
    public static int arc_id = 0;

    //Take user input and add nodes
    public void setNodes(){

        //Determine total amount of nodes in graph
        System.out.println("How many nodes in graph?");
        Scanner scanner = new Scanner(System.in);
        int numNodes = scanner.nextInt();

        //Set array size
        relationArray = new String[numNodes + 1][numNodes + 1];
        //Setup relationArray
        for(String[] row : relationArray){
            Arrays.fill(row, "0");
        }
        relationArray[0][0] = "~";

        //Name nodes and add them to arrayList
        System.out.println("Set the name of your nodes. Single letter/digit only.");
        for(int i = 0; i < numNodes; i++){
            String name = scanner.next();
            Node node = new Node(name, i);
            nodes.add(node);

            //Add node to relationArray
            relationArray[0][i+1] = name;
            relationArray[i+1][0] = name;
        }
        System.out.println("All Nodes Set!");
    }

    //Print nodes to mainArena
    public void printNodes(){
        int numNodes = nodes.size();
        for(int i = 0; i < numNodes; i++){
            nodes.get(i).addNode();
        }
    }

    //Add arcs
    public void addArcs(){
        Scanner scanner = new Scanner(System.in);
        int numNodes = nodes.size();

        //Choose between simple and directed graph
        System.out.println("Do you want simple or directed graph?");
        String graphType = scanner.next();
        if(!graphType.equals("simple") && !graphType.equals("directed")){
            System.out.println("No graph type inputted.");
        }

        //Commands to user
        System.out.println();
        System.out.println("Set arcs. Enter starting node before end node.");
        System.out.println("Repeat until done. End by entering end twice.");
        System.out.println("Name, ID");

        //Show nodes and their ID from arrayList
        for(int i = 0; i < numNodes; i++){
            String nodeName = nodes.get(i).returnName();
            int nodeID = nodes.get(i).returnID();

            System.out.println(nodeName + ", " + nodeID);
        }
        System.out.println("");

        //Add connection between nodes
        boolean endInput = false;
        while(!endInput){

            //Get input for the first node and second node
            String fromNode = scanner.next();
            System.out.println("to");
            String toNode = scanner.next();
            System.out.println("");

            //When completed
            if(fromNode.equals("end") && toNode.equals("end")){
                end();
            }
            else{
                //Add connection to nodes
                try{
                    int fromNodeINT = Integer.parseInt(fromNode);
                    int toNodeINT = Integer.parseInt(toNode);

                    //Add simple arc or directed arc
                    if(graphType.equals("simple")){
                        Arc arc = new Arc("simple", arc_id);
                        arc_id++;
                        arc.addArc(fromNodeINT, toNodeINT);
                    }
                    else if(graphType.equals("directed")){
                        Arc arc = new Arc("directed", arc_id);
                        arc_id++;
                        arc.addDirectedArc(fromNodeINT, toNodeINT);
                    }

                    //Modifiy relationArray
                    relationArray[toNodeINT + 1][fromNodeINT + 1] = "1";

                    //Modify connectedWith ArrayList
                    if(fromNodeINT == toNodeINT){
                        nodes.get(fromNodeINT).addConnection(nodes.get(toNodeINT));
                    }
                    else{
                        nodes.get(fromNodeINT).addConnection(nodes.get(toNodeINT));
                        nodes.get(toNodeINT).addConnection(nodes.get(fromNodeINT));
                    }

                }catch(NumberFormatException nfe){
                    //Do Nothing
                }catch(IllegalStateException ise){
                    //Do Nothing
                }
                mainArena.update();
            }
        }
        scanner.close();
    }

    //Print node connections
    public void printNodeConnections(){
        int numNodes = nodes.size();
        for(int i = 0; i < numNodes; i++){
            
            int connectedNum = nodes.get(i).returnConnectionsNumber();
            connectedArray = new String[connectedNum];

            nodes.get(i).returnConnections();
            String nodeName = nodes.get(i).returnName(); 

            System.out.println("Node " + nodeName + " is connected with: ");
            System.out.println(Arrays.toString(connectedArray));
        }
    }

    //End function
    private void end(){
        mainArena.update();
        System.out.println("");
        System.out.println("Directed Relation Table");
        System.out.println("The nodes on the left is directed to those on the top.");
        System.out.println("");

        //Print relationArray
        System.out.println(Arrays.deepToString(relationArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println("");

        //Print connections
        printNodeConnections();
    }

    public static void main(String[] args){
        main.setNodes();
        main.printNodes();
        main.addArcs();
        main.printNodeConnections();
        mainArena.update();
    }
}