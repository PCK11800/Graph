import java.util.*;

public class Main{
    public static Main main = new Main();

    //GameArena
    public static GameArena mainArena = new GameArena(1000, 1000, true);

    //Node arrayList
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    /*To access node: nodes.get(i).methods() where i = index of element
    To add a node for example: nodes.get(i).addNode();*/

    //Arc
    public static Arc arc = new Arc();

    //Relationship 2d array
    public static String[][] relationArray;

    public void setNodes(){
        System.out.println("How many nodes in the graph?");

        Scanner scanner = new Scanner(System.in);
        int numofNodes = scanner.nextInt();
        
        //Set relationArray size
        relationArray = new String[numofNodes + 1][numofNodes + 1];
        //Fill array with zeroes
        for (String[] row : relationArray){
            Arrays.fill(row, "0");
        }

        relationArray[0][0] = "~";

        System.out.println("Set the name of your nodes.");
        //Add nodes to node Arraylist
        for (int i = 0; i < numofNodes; i++){
            String name = scanner.next();
            Node node = new Node(name, i);
            nodes.add(node);    //Add this specific node to arrayList

            //Add node to relation array
            relationArray[0][i+1] = name;
            relationArray[i+1][0] = name;
        }

        System.out.println("All Nodes Set!");
    }

    //Print the nodes to mainArena
    public void printNodes(){
        int numofNodesplusone = nodes.size();

        for (int i = 0; i < numofNodesplusone; i++){
            nodes.get(i).addNode();
        }
    }

    //Add arcs
    public void addArcs(){
        Scanner scanner = new Scanner(System.in);
        int numofNodesplusone = nodes.size();

        System.out.println("Do you want simple or directed graph?");
        String graphType = scanner.next();
        if (!graphType.equals("simple") && !graphType.equals("directed")){
            System.out.println("No graph type inputted. Shutting down.");
        }

        System.out.println();
        System.out.println("Set arcs. Enter starting node before end node.");
        System.out.println("Repeat until done. End by entering end twice.");

        System.out.println("Name, ID");

        //Show nodes and their ID in arrayList
        for (int i = 0; i < numofNodesplusone; i++){
            String nodeName = nodes.get(i).returnName();
            int nodeID = nodes.get(i).returnID();

            System.out.println(nodeName + ", " + nodeID);
        }
        System.out.println("");

        boolean endofInput = false;
        while(!endofInput){
            String nodeOne = scanner.next();
            System.out.println("to");
            String nodeTwo = scanner.next();
            System.out.println("");
        
            if(nodeOne.equals("end") && nodeTwo.equals("end")){
                mainArena.update();
                System.out.println("");
                System.out.println("Relation Table");
                System.out.println("The nodes on the left is directed to those on the top.");
                System.out.println("");
                //Print arcs
                System.out.println(Arrays.deepToString(relationArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            }
            else{
                try{
                    int nodeOneINT = Integer.parseInt(nodeOne);
                    int nodeTwoINT = Integer.parseInt(nodeTwo);

                    if(graphType.equals("simple")){
                        arc.addArc(nodeOneINT, nodeTwoINT);
                    }
                    else if(graphType.equals("directed")){
                        arc.addDirectedArc(nodeOneINT, nodeTwoINT);
                    }

                    //Add one to relationArray
                    relationArray[nodeOneINT + 1][nodeTwoINT + 1] = "1";

                }catch(NumberFormatException nfe){
                    scanner.close();
                    mainArena.update();
                }catch(IllegalStateException ise){
                    scanner.close();
                    mainArena.update();
                }
            }    
        }
    }
    
    public static void main(String[] args){
        main.setNodes();
        main.printNodes();
        main.addArcs();
        mainArena.update();
    }
}