package sk.stuba.fei.uim.oop;
import lombok.SneakyThrows;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.Border;

public class MyCanvas extends Canvas  implements MouseListener, KeyListener {
    private int counter;
    private int counterStart;



    private Tile[][] policka;
    private Stack<Tile> nextStack;
    private Stack<Tile> availableTiles;


    public MyCanvas(Tile[][] policka, Stack<Tile> nextStack) {
        this.policka = policka;
        this.nextStack = nextStack;
        this.addMouseListener(this);



    }

    public void paint(Graphics g) {
        Iterator<Tile> itr = nextStack.iterator();
        g.setColor(Color.white);
        g.fillRect(0, 0, 300, 300);

        g.setColor(Color.black);

        for (Tile i : nextStack) {
            //Thread.sleep(1000);
            if (i.isDolna()) {


                g.drawLine(((i.getX() + 1) * 20), (i.getY() + 1) * 20 + 20, ((i.getX() + 1) * 20 + 20), (i.getY() + 1) * 20 + 20); //dolna

            }
            if (i.isHorna()) {
                g.drawLine((i.getX() + 1) * 20, (i.getY() + 1) * 20, (i.getX() + 1) * 20 + 20, (i.getY() + 1) * 20); //horna
            }
            if (i.isLava()) {
                g.drawLine((i.getX() + 1) * 20, (i.getY() + 1) * 20, (i.getX() + 1) * 20, (i.getY() + 1) * 20 + 20); //prava
            }
            if (i.isPrava()) {
                g.drawLine((i.getX() + 1) * 20 + 20, (i.getY() + 1) * 20, (i.getX() + 1) * 20 + 20, (i.getY() + 1) * 20 + 20); //lava
            }


        }
        g.setColor(Color.red);
        //g.fillOval(20, 20, 20, 20);
        //paintPosition(g,20,20);
       // findAvailable();
        currentPosition(g,0,0);






    }

    public void makeAvailable(Tile tile,boolean o) {
        for (Tile t:tile.getAvailableTiles()) {
            t.setAvailable(o);
        }

    }



        public Tile paintAvailable(Tile tile,Graphics g) {
        for (Tile t:tile.getAvailableTiles()) {
            g.setColor(Color.blue);

            g.fillOval((t.getX() + 1) * 20, (t.getY() + 1) * 20, 20, 20);

        }

            g.setColor(Color.red);
            g.fillOval((tile.getX() + 1) * 20, (tile.getY() + 1) * 20, 20, 20);




        return tile;
        }

    public void paintAvailableWhite(Tile tile,Graphics g) {
        for (Tile t:tile.getAvailableTiles()) {
            g.setColor(Color.white);

            g.fillOval((t.getX() + 1) * 20, (t.getY() + 1) * 20, 20, 20);

        }

        g.setColor(Color.white);
        g.fillOval((tile.getX() + 1) * 20, (tile.getY() + 1) * 20, 20, 20);



    }




        public Tile currentPosition(Graphics g,int x,int y) {
            g.setColor(Color.red);
            g.fillOval((x+1)*20,(y+1)*20,20,20);
            return policka[x][y];
        }

        public void paintAll(Graphics g) {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 13; j++) {
                    if (!policka[i][j].isAvailable() || !policka[i][j].isMouseclicked() || !policka[i][j].isStart() || !policka[i][j].isEnd()) {
                        g.setColor(Color.white);
                        g.fillOval((int)((i+1)*20+20),(int)((j+1)*20+20),20,20);
                    }
                    if (policka[i][j].isAvailable()) {
                        g.setColor(Color.BLUE);
                        g.fillOval((int)((i+1)*20),(int)((j+1)*20),20,20);
                    }
                    if (policka[i][j].isMouseclicked()) {
                        g.setColor(Color.red);
                        g.fillOval((int)((i+1)*20),(int)((j+1)*20),20,20);
                    }
                }

                }


        }

    @Override
    public void mouseClicked(MouseEvent e) {


        Point p = e.getPoint();
        Tile start=policka[0][0];
        start.setStart(true);
        Tile end=policka[12][12];
        end.setEnd(true);
        Tile current=new Tile();
        System.out.println("som tuuuuuuuuuuuuuuu2"+current.getX()+"  "+current.getY());
        if (counter==0 && counterStart==0 ) {


            System.out.println("som tuuuuuuuuuuuuuuu1");
             current=start;
             current=currentPosition(this.getGraphics(), current.getX(), current.getY());
             current.setMouseclicked(true);

             counterStart++;
             counter++;



        }
        current.setMouseclicked(true);
        System.out.println("COunter"+counter);
        System.out.println("som tuuuuuuuuuuuuuuu2"+current.getX()+"  "+current.getY());
        if (  p.getX() <= ((current.getX()+1)*20+20) && p.getX() >= (current.getX()+1)*20 && p.getY() <= (current.getY()+1)*20 + 20 && p.getY() >= (current.getY()+1)*20) {
            current.setMouseclicked(true);
            System.out.println("tu sooom000000");
            //current=currentPosition(getGraphics(), current.getX(), current.getY());
            makeAvailable(current,true);

            //paintAllWhite(this.getGraphics(), predosly.getX(), predosly.getY());
            //paintAvailable(tile,this.getGraphics(),predosly);

            counter++;



        }
        if (counter>1 && p.getX() <= ((current.getX()+1)*20+20) && p.getX() >= (current.getX()+1)*20 && p.getY() <= (current.getY()+1)*20 + 20 && p.getY() >= (current.getY()+1)*20) {
            paintAll(this.getGraphics());


        }

        if ((counter==2) && policka[(int)(p.getX()-20)/20][(int)(p.getY()-20)/20].isAvailable()) {

            current.setMouseclicked(false);
            makeAvailable(current,false);
            current=policka[(int)(p.getX()-20)/20][(int)(p.getY()-20)/20];
            current.setMouseclicked(true);
            current=currentPosition(this.getGraphics(), current.getX(), current.getY());
            paintAll(this.getGraphics());

            counter=0;

        }
        System.out.println(current.getX()+"   "+current.getY());






/*
        //ak je slaceny nejaky z available
        if (counter==2 ) {
            paintAllWhite(this.getGraphics(), predosly.getX(), predosly.getY());
            //tile.setX((int)(p.getX()-20)/20);
            //tile.setY((int)(p.getY()-20)/20);
            tile=currentPosition(this.getGraphics(),(int)(p.getX()-20)/20,(int)(p.getY()-20)/20);
            paintAllWhite(this.getGraphics(), predosly.getX(), predosly.getY());
            //paintAvailable(tile,this.getGraphics(),predosly);
            counter=0;
        }
        paintAvailable(tile,this.getGraphics(),predosly);

        counter++;*/








    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.err.println("****************************** mouswe clicked2");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.err.println("****************************** mouswe clicked2");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.err.println("****************************** mouswe clicked2");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}







