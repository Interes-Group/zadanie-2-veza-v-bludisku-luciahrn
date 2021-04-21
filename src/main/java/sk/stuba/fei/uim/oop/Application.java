package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Stack;

public class Application implements MouseListener {
    private Tile[][] policka;
    private Stack<Tile> nextStack;



    public Application(Tile[][] policka, Stack<Tile> nextStack) {
        this.policka = policka;
        this.nextStack= nextStack;
    }



    public void kresli() {
            Border blackline = BorderFactory.createLineBorder(Color.black);
            JPanel bludisko=new JPanel();
            bludisko.setBorder(blackline);
            bludisko.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
            JPanel menu=new JPanel();
            //menu.setBorder( new MatteBorder(0, 0, 0, 2, Color.black));
            bludisko.setBackground(Color.red);
            menu.setBackground(Color.BLUE);
            bludisko.setBounds(0,0,300,300);
            menu.setBounds(300,0,200,300);
            //f.add(bludisko);

            MyCanvas canvas=new MyCanvas(policka,nextStack);
            MyButton f=new MyButton(canvas,policka);
            f.add(menu);
            f.add(canvas);
            //f.add(new MyMouse());


    }






    @Override
    public void mouseClicked(MouseEvent e) {
        System.err.println();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}




