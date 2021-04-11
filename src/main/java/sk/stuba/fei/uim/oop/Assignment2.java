package sk.stuba.fei.uim.oop;
import javax.swing.*;
import java.awt.*;




public class Assignment2 {



    public static void vytvorOkno()    {


        JFrame F=new JFrame();

        F.setSize(350,350);

        F.add("Center",new PaintCanvas());

        //Game board=new Game();
        //board.makeMaze();

        F.setVisible(true);


    }



    public static void main(String[] args) {
        vytvorOkno();
    }
}
