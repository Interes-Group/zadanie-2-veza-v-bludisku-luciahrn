package sk.stuba.fei.uim.oop;

import javax.swing.*;


import java.awt.*;

import java.util.Stack;

public class Application  {
    private Tile[][] policka;
    private Stack<Tile> nextStack;



    public Application(Tile[][] policka, Stack<Tile> nextStack) {
        this.policka = policka;
        this.nextStack= nextStack;
    }





    public void kresli() {

            JPanel menu=new JPanel();
            menu.setBackground(Color.BLUE);
            menu.setBounds(300,60,200,240);
            MyCanvas canvas=new MyCanvas(policka,nextStack);
            MyButton f=new MyButton(canvas,policka);
            f.add(menu);
            f.add(canvas);


    }
}




