import java.util.*;

public class Node extends Main{

    public Node(String input_name, int id){
        name = input_name;
        ID = id;
    }

    //Node
    Ball node;

    //Node ID for arrayList
    int ID;

    //RNG
    Random rand = new Random();

    //Variables of each node
    private double x_location;        
    private double y_location;
    private String name;
    private double diameter = 25;
    private String colour = "AQUA";

    //Constructor
    public void addNode(){
        x_location = rand.nextInt(900) + 50;
        y_location = rand.nextInt(900) + 50;

        //Create a node
        node = new Ball(x_location, y_location, diameter, colour);
        mainArena.addBall(node);

        //Overlay name on node
        Text nodeName = new Text(name, x_location - 8, y_location + 8, diameter, "BLACK");
        mainArena.addText(nodeName);
    }

    //Return xloc and yloc
    public double returnXPosition(){
        double x_pos = node.getXPosition();
        return x_pos;
    }

    public double returnYPosition(){
        double y_pos = node.getYPosition();
        return y_pos;
    }

    //Return ID
    public int returnID(){
        return ID;
    }

    public String returnName(){
        return name;
    }
}
