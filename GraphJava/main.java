import java.util.*;

public class main{
    public static void main(String[] args){
        GameArena GameArena = new GameArena(500, 500, true);
        Ball Ball = new Ball(100, 100, 10, "AQUA");
        Text text = new Text("Hello World!", 100, 10, 50, "AQUA");

        GameArena.addBall(Ball);
        GameArena.addText(text);
        GameArena.update();
    }
}