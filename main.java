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

    public void setNodes(){
        System.out.println("How many nodes in the graph?");

        Scanner scanner = new Scanner(System.in);
        int numofNodes = scanner.nextInt();

        System.out.println("Set the name of your nodes.");
        //Add nodes to node Arraylist
        for (int i = 0; i < numofNodes; i++){
            String name = scanner.next();
            Node node = new Node(name, i);
            nodes.add(node);    //Add this specific node to arrayList
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

        System.out.println("Set arcs. Enter starting node before end node.");
        System.out.println("Repeat until done. End by entering non-int twice.");

        System.out.println("Name, ID");

        //Show nodes and their ID in arrayList
        for (int i = 0; i < numofNodesplusone; i++){
            String nodeName = nodes.get(i).returnName();
            int nodeID = nodes.get(i).returnID();

            System.out.println(nodeName + ", " + nodeID);
        }

        boolean endofInput = false;
        while(!endofInput){
            String nodeOne = scanner.next();
            String nodeTwo = scanner.next();
        
            if(nodeOne.equals("end") && nodeTwo.equals("end")){
                scanner.close();

                //Print arcs
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