import java.util.*;

public class Arc extends Main{

    //Constructor
    public void addArc(int idOne, int idTwo){
        double one_x_pos = nodes.get(idOne).returnXPosition();
        double one_y_pos = nodes.get(idOne).returnYPosition();

        double two_x_pos = nodes.get(idTwo).returnXPosition();
        double two_y_pos = nodes.get(idTwo).returnYPosition();

        if((one_x_pos == two_x_pos) && (one_y_pos == two_y_pos)){
            //If identical nodes
            Line arc1 = new Line(one_x_pos, one_y_pos, one_x_pos, one_y_pos + 50, 2, "RED");
            Line arc2 = new Line(one_x_pos, one_y_pos + 50, one_x_pos + 50, one_y_pos + 50, 2, "RED");
            Line arc3 = new Line(one_x_pos + 50, one_y_pos + 50, one_x_pos, one_y_pos, 2, "RED");
            mainArena.addLine(arc1);
            mainArena.addLine(arc2);
            mainArena.addLine(arc3);
        }else{
            Line arc = new Line(one_x_pos, one_y_pos, two_x_pos, two_y_pos, 2, "RED");
            mainArena.addLine(arc);
        }

    }

    public void addDirectedArc(int idOne, int idTwo){
        double one_x_pos = nodes.get(idOne).returnXPosition();
        double one_y_pos = nodes.get(idOne).returnYPosition();

        double two_x_pos = nodes.get(idTwo).returnXPosition();
        double two_y_pos = nodes.get(idTwo).returnYPosition();

        if((one_x_pos == two_x_pos) && (one_y_pos == two_y_pos)){
            //If identical nodes
            Line arc1 = new Line(one_x_pos, one_y_pos, one_x_pos, one_y_pos + 50, 2, "RED");
            Line arc2 = new Line(one_x_pos, one_y_pos + 50, one_x_pos + 50, one_y_pos + 50, 2, "RED");
            mainArena.addLine(arc1);
            mainArena.addLine(arc2);
            Arrow arc = new Arrow(one_x_pos + 50, one_y_pos + 50, one_x_pos, one_y_pos, 1, "RED", mainArena);
        }else{
            Arrow arc = new Arrow(one_x_pos, one_y_pos, two_x_pos, two_y_pos, 1, "RED", mainArena);
        }
    }
}