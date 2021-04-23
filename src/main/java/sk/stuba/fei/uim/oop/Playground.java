package sk.stuba.fei.uim.oop;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playground extends JFrame implements ActionListener  {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5=new JButton();
    private Tile[][] policka;
    private MyCanvas myCanvas;

    Playground(MyCanvas myCanvas, Tile[][] policka) {

        this.policka = policka;
        this.addKeyListener(myCanvas);

        button1=new JButton();
        button1.setText("U");
        button1.addActionListener(this);
        button1.setBounds(370,100,60,30);
        button2=new JButton();
        button2.addActionListener(this);
        button2.setBounds(310,130,60,30);
        button2.setText("L");
        button3=new JButton();
        button3.setBounds(430,130,60,30);
        button3.addActionListener(this);
        button3.setText("R");
        button4=new JButton();
        button4.setBounds(370,130,60,30);
        button4.addActionListener(this);
        button4.setText("D");
        this.myCanvas = myCanvas;
        button5.addActionListener(this);
        button5.setText("Restart");
        button5.setBounds(360,200,80,30);

        this.setTitle("Bludisko");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        //this.setLayout(null);
        this.setSize(700,500);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(button1); //UP
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);



    }




    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource()==button1) { //Up
            myCanvas.pressUp();

        }

        if (e.getSource()==button2) { //Left
            myCanvas.pressLeft();

        }
        if (e.getSource()==button3) { //Right
        myCanvas.pressRight();

        }
        if (e.getSource()==button4) { //Down
            myCanvas.pressDown();

        }
        if (e.getSource()==button5) { //Down
            this.setVisible(false);
            Game game=new Game();
            game.makeMaze();
            myCanvas.refresh();
            policka[12][12].setWin(0);
            this.setFocusable(true);

        }


    }
}
