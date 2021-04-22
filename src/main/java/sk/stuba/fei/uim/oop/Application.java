package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Stack;

public class Application  {
    private Tile[][] policka;
    private Stack<Tile> nextStack;



    public Application(Tile[][] policka, Stack<Tile> nextStack) {
        this.policka = policka;
        this.nextStack= nextStack;
    }



    public void kresli() {
            JLabel l1 = new JLabel("COUNTER:   "+policka[12][12].getWin());
            JPanel counter=new JPanel();
            JPanel menu=new JPanel();
            counter.add(l1);
            counter.setBackground(Color.gray);
            menu.setBackground(Color.BLUE);
            counter.setBounds(300,0,200,60);
            menu.setBounds(300,60,200,240);
            MyCanvas canvas=new MyCanvas(policka,nextStack);
            MyButton f=new MyButton(canvas,policka);
            f.add(menu);
            f.add(canvas);




    }
}




