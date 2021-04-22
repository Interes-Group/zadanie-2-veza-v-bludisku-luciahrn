package sk.stuba.fei.uim.oop;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton extends JFrame implements ActionListener  {
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5=new JButton();
    JButton button6=new JButton();
    private Tile[][] policka;
    private int i;
    private int j;
    private int startCounter;
    private MyCanvas myCanvas;

    MyButton(MyCanvas myCanvas,Tile[][] policka) {

        this.policka = policka;
        this.addKeyListener(myCanvas);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        button1=new JButton();
        button1.setText("U");
        button1.addActionListener(this);
        button1.setBounds(370,100,60,30);
        //button1.setText(String.valueOf(Character.toChars(24)));
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
        button5.setBounds(370,200,60,30);
        //button6.setBounds(370,30,60,30);
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
        //this.add(button6);


    }

    public void kresli (Graphics g,int i,int j) {
        Tile start=policka[0][0];
        g.setColor(Color.red);
        g.fillOval((i+1)*20,(j+1)*20,20,20);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("****************************** ctionPerf");

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


    }
}
