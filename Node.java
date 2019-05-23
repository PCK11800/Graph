import java.util.*;

public class Node extends Main{

    public Node(String input_name, int id){
        name = input_name;
        ID = id;
    }

    //Colour
    private Random random = new Random();
    private String[] colourArray = {"AQUA", "GOLD", "CYAN", "GRAY", "RED", "BLUE", "SILVER", "YELLOW"};
    private int colorInt = random.nextInt(colourArray.length);
    private String colour = colourArray[colorInt];

    //Node
    Ball node;
    private int ID;
    private String name;
    private ArrayList<Node> connectedWith = new ArrayList<Node>();

    //Variables
    private double x_location;
    private double y_location;
    private double diameter = 25;

    //Create Node
    public void addNode(){
        Random rand = new Random();
        x_location = rand.nextInt(900) + 50;
        y_location = rand.nextInt(900) + 50;

        node = new Ball(x_location, y_location, diameter, colour);
        mainArena.addBall(node);

        //Overlay name on node
        Text nodeName = new Text(name, x_location - 8, y_location + 8, diameter, "BLACK");
        mainArena.addText(nodeName);
    }

    //Return location
    public double returnXPosition(){
        double x_pos = node.getXPosition();
        return x_pos;
    }

    public double returnYPosition(){
        double y_pos = node.getYPosition();
        return y_pos;
    }

    //Return name, ID
    public int returnID(){
        return ID;
    }

    public String returnName(){
        return name;
    }

    //List Operations
    public void addConnection(Node n){
        connectedWith.add(n);
    }

    public void removeConnection(Node n){
        connectedWith.remove(n);
    }

    //Return connected nodes names to an connectedArray
    public void returnConnections(){
        int connectionNum = connectedWith.size();
        for(int i = 0; i < connectionNum; i++){
            String name = connectedWith.get(i).returnName();
            connectedArray[i] = name;
        }
    }

    //Return total amount of connections
    public int returnConnectionsNumber(){
        int connectionNum = connectedWith.size();
        return connectionNum;
    }

    //Check if connection exist with another node
    public boolean connectionExist(Node n){
        if (connectedWith.contains(n)){
            return true;
        }
        else{
            return false;
        }
    }

}