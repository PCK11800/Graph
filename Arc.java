import java.util.*;

public class Arc extends Main{

    //Arc name, ID and type
    static int ID;
    static String type;

    //Variables
    static double slope_var_x;
    static double slope_var_y;

    //Colour
    private Random random = new Random();
    private String[] colourArray = {"AQUA", "GOLD", "CYAN", "GRAY", "RED", "BLUE", "SILVER", "YELLOW"};
    private int colorInt = random.nextInt(colourArray.length);
    private String colour = colourArray[colorInt];

    //Create simple non-directed arc
    private void createArc(double x1_pos, double y1_pos, double x2_pos, double y2_pos){
        Line arc = new Line(x1_pos, y1_pos, x2_pos, y2_pos, 2, colour);
        mainArena.addLine(arc);
    }

    //Create a directed arc from 1_pos towards 2_pos
    private void createDirectedArc(double x1_pos, double y1_pos, double x2_pos, double y2_pos){
        set_slope_var(x1_pos, y1_pos, x2_pos, y2_pos);
        Line arc_body = new Line(x1_pos, y1_pos, x2_pos - slope_var_x, y2_pos - slope_var_y, 2, colour);
        Arrow arc_head = new Arrow(x2_pos - slope_var_x, y2_pos - slope_var_y, x2_pos, y2_pos, 2, colour, mainArena);
        mainArena.addLine(arc_body);
    }

    private void createSelfArc(double x_pos, double y_pos){
        //Set off in a random direction
        Random rand = new Random();
        int direction_first = (rand.nextInt(30) + 20) * (rand.nextBoolean() ? -1 : 1); 
        int direction_second = (rand.nextInt(30) + 10) * (rand.nextBoolean() ? -1 : 1);
        double direction_double_first = 1.0 * direction_first; //convert to double
        double direction_double_second = 1.0 * direction_second;

        Line arc_part1 = new Line(x_pos, y_pos, x_pos + direction_double_first, y_pos + direction_double_first, 2, "RED");
        Line arc_part2 = new Line(x_pos + direction_double_first, y_pos + direction_double_first, x_pos - direction_double_second, y_pos + direction_double_second, 2, "RED");
        Line arc_part3 = new Line(x_pos - direction_double_second, y_pos + direction_double_second, x_pos, y_pos, 2, "RED");

        mainArena.addLine(arc_part1);
        mainArena.addLine(arc_part2);
        mainArena.addLine(arc_part3);
    }

    private void createSelfDirectedArc(double x_pos, double y_pos){
        //Set off in a random direction
        Random rand = new Random();
        int direction_first = (rand.nextInt(30) + 20) * (rand.nextBoolean() ? -1 : 1); 
        int direction_second = (rand.nextInt(30) + 10) * (rand.nextBoolean() ? -1 : 1);
        double direction_double_first = 1.0 * direction_first; //convert to double
        double direction_double_second = 1.0 * direction_second;

        Line arc_part1 = new Line(x_pos, y_pos, x_pos + direction_double_first, y_pos + direction_double_first, 2, "RED");
        Line arc_part2 = new Line(x_pos + direction_double_first, y_pos + direction_double_first, x_pos - direction_double_second, y_pos + direction_double_second, 2, "RED");
        createDirectedArc(x_pos - direction_double_second, y_pos + direction_double_second, x_pos, y_pos);

        mainArena.addLine(arc_part1);
        mainArena.addLine(arc_part2);
    }

    //This set the arrowhead direction
    private void set_slope_var(double x1_pos, double y1_pos, double x2_pos, double y2_pos){
        double size = 0.25;

        //towards bottom-right
        if((x2_pos > x1_pos) && (y2_pos > y1_pos)){
            slope_var_x = size * (x2_pos - x1_pos);
            slope_var_y = size * (y2_pos - y1_pos);
        }
        //towards bottom-left
        else if((x2_pos < x1_pos) && (y2_pos > y1_pos)){
            slope_var_x = -size * (x1_pos - x2_pos);
            slope_var_y = size * (y2_pos - y1_pos);
        }
        //towards top-right
        else if((x2_pos > x1_pos) && (y2_pos < y1_pos)){
            slope_var_x = size * (x2_pos - x1_pos);
            slope_var_y = -size * (y1_pos - y2_pos);
        }
        //towards top-left
        else if((x2_pos < x1_pos) && (y2_pos < y1_pos)){
            slope_var_x = -size * (x1_pos - x2_pos);
            slope_var_y = -size * (y1_pos - y2_pos);
        }
    }

    //Add Simple arc function
    public void addArc(int idOne, int idTwo){

        //Return node positions
        double one_x_pos = nodes.get(idOne).returnXPosition();
        double one_y_pos = nodes.get(idOne).returnYPosition();
        double two_x_pos = nodes.get(idTwo).returnXPosition();
        double two_y_pos = nodes.get(idTwo).returnYPosition();

        //create Arcs
        if((one_x_pos == two_x_pos) && (one_y_pos == two_y_pos)){
            //SelfArc
            createSelfArc(one_x_pos, one_y_pos);
        }
        else{
            createArc(one_x_pos, one_y_pos, two_x_pos, two_y_pos);
        }
    }

    //Add directed arc function
    public void addDirectedArc(int idOne, int idTwo){
        
        //Return node positions
        double one_x_pos = nodes.get(idOne).returnXPosition();
        double one_y_pos = nodes.get(idOne).returnYPosition();
        double two_x_pos = nodes.get(idTwo).returnXPosition();
        double two_y_pos = nodes.get(idTwo).returnYPosition();

        if((one_x_pos == two_x_pos) && (one_y_pos == two_y_pos)){
            //SelfDirectedArc
            createSelfDirectedArc(one_x_pos, one_y_pos);
        }
        else{
            createDirectedArc(one_x_pos, one_y_pos, two_x_pos, two_y_pos);
        }
    }
}