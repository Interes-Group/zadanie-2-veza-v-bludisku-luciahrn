package sk.stuba.fei.uim.oop;
import lombok.SneakyThrows;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class MyCanvas extends Canvas  implements MouseListener, KeyListener,MouseMotionListener {
    private int counter;
    private int counterStart;
    private int i = 0;
    private int j = 0;
    private int startCounter = 0;
    private int counterWins = 0;
    private List<Point> vykreslene = new ArrayList();


    private Tile[][] policka;;
    private Stack<Tile> nextStack;
    private Stack<Tile> availableTiles;
    private Tile current=new Tile();


    public MyCanvas(Tile[][] policka, Stack<Tile> nextStack) {
        this.policka = policka;
        this.nextStack = nextStack;
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);


    }

    public void paint(Graphics g) {
        // policka[12][12].setWin(counterWins);
        Iterator<Tile> itr = nextStack.iterator();
        g.setColor(Color.white);
        g.fillRect(0, 0, 300, 300);
        g.setColor(Color.gray);
        g.fillRect(300, 0, 200, 60);
        g.setColor(Color.black);
        g.setFont(new Font("BoldFont", Font.BOLD, 14));
        g.drawString("Counter:  " + policka[12][12].getWin(), 370, 35);


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

        currentPosition(g, 0, 0);
        kresliEnd(g);


    }

    public void makeAvailable(Tile tile, boolean o) {
        for (Tile t : tile.getAvailableTiles()) {
            t.setAvailable(o);
        }

    }


    public Tile paintAvailable(Tile tile, Graphics g) {
        for (Tile t : tile.getAvailableTiles()) {
            g.setColor(Color.blue);

            g.fillOval((t.getX() + 1) * 20, (t.getY() + 1) * 20, 20, 20);

        }

        g.setColor(Color.red);
        g.fillOval((tile.getX() + 1) * 20, (tile.getY() + 1) * 20, 20, 20);


        return tile;
    }

    public void refresh() {
        repaint();
        i=0;
        j=0;
        current=policka[0][0];
    }



    public Tile currentPosition(Graphics g, int x, int y) {
        g.setColor(Color.red);
        g.fillOval((x + 1) * 20, (y + 1) * 20, 20, 20);
        return policka[x][y];
    }

    public void paintAll(Graphics g) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                Point p = null;
                if (!policka[i][j].isAvailable() || !policka[i][j].isMouseclicked() || !policka[i][j].isStart() || !policka[i][j].isEnd()) {
                    g.setColor(Color.white);
                    g.fillOval((int) ((i + 1) * 20 + 20), (int) ((j + 1) * 20 + 20), 20, 20);

                }
                if (policka[i][j].isAvailable()) {
                    g.setColor(Color.BLUE);
                    g.fillOval((int) ((i + 1) * 20), (int) ((j + 1) * 20), 20, 20);
                    p = new Point((int) ((i + 1) * 20 + 20), (int) ((j + 1) * 20 + 20));
                }
                if (policka[i][j].isMouseclicked()) {
                    g.setColor(Color.red);
                    g.fillOval((int) ((i + 1) * 20), (int) ((j + 1) * 20), 20, 20);

                }

                if(p != null){
                    vykreslene.add(p);
                }
            }

        }


    }

    public void paintAllWhite(Graphics g) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {

                g.setColor(Color.white);
                g.fillOval((int) ((i + 1) * 20 ), (int) ((j + 1) * 20 ), 20, 20);


            }

        }


    }

    public void pressUp() {
        Tile t = policka[i][j];
        current=policka[i][j];
        for (Tile available : t.getAvailableTiles()) {

            if (t.getX() == available.getX() && t.getY() - 1 == available.getY()) {

                kresli(getGraphics(), i, j - 1, i, j);
                j = j - 1;
                t = available;
                current=t;
                if (t.getX() == 12 && t.getY() == 12) {

                    counterWins++;
                    policka[12][12].setWin(counterWins);
                    repaint();
                    i = 0;
                    j = 0;
                    current=policka[0][0];
                }
                break;

            }

        }
    }

    public void pressDown() {
        Tile t;
        t = policka[i][j];
        current=policka[i][j];

        for (Tile available : t.getAvailableTiles()) {

            if (t.getX() == available.getX() && t.getY() + 1 == available.getY()) {


                kresli(this.getGraphics(), i, j + 1, i, j);
                j = j + 1;
                t = available;
                current=t;
                if (t.getX() == 12 && t.getY() == 12) {
                    counterWins++;
                    policka[12][12].setWin(counterWins);
                    i = 0;
                    j = 0;
                    current=policka[0][0];
                    repaint();
                }
                break;

            }

        }


    }

    public void pressLeft() {
        Tile t = policka[i][j];
        current=policka[i][j];

        for (Tile available : t.getAvailableTiles()) {

            if (t.getX() - 1 == available.getX() && t.getY() == available.getY()) {


                kresli(getGraphics(), i - 1, j, i, j);

                i = i - 1;
                t = available;
                current=t;
                if (t.getX() == 12 && t.getY() == 12) {
                    counterWins++;
                    policka[12][12].setWin(counterWins);
                    i = 0;
                    j = 0;
                    current=policka[0][0];
                    repaint();
                }
                break;

            }

        }

    }
    public void pressRight() {
        Tile t = policka[i][j];
        current=policka[i][j];
        for (Tile available : t.getAvailableTiles()) {

            if (t.getX() + 1 == available.getX() && t.getY() == available.getY()) {


                kresli(getGraphics(), i + 1, j, i, j);

                i = i + 1;
                t = available;
                current=t;
                if (t.getX() == 12 && t.getY() == 12) {
                    counterWins++;
                    policka[12][12].setWin(counterWins);
                    i = 0;
                    j = 0;
                    current=policka[0][0];
                    repaint();
                }
                break;

            }

        }
    }
    public void kresliEnd(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((12 + 1) * 20, (12 + 1) * 20, 20, 20);
    }

    public void kresli(Graphics g, int x, int y, int oldI, int oldJ) {
        g.setColor(Color.white);
        g.fillOval((oldI + 1) * 20, (oldJ + 1) * 20, 20, 20);


        g.setColor(Color.red);
        g.fillOval((x + 1) * 20, (y + 1) * 20, 20, 20);


    }

    public void paintRedPoint(Tile tile,Graphics g) {
        g.setColor(Color.red);
        g.fillOval((tile.getX() + 1) * 20, (tile.getY() + 1) * 20, 20, 20);

    }


    @Override
    public void mouseClicked(MouseEvent e) {


        Point p = e.getPoint();
        //Tile current = new Tile();
        Tile start=policka[i][j];

        if (counter == 0 && counterStart == 0 && p.getX() <= ((current.getX() + 1) * 20 + 20) && p.getX() >= (current.getX() + 1) * 20 && p.getY() <= (current.getY() + 1) * 20 + 20 && p.getY() >= (current.getY() + 1) * 20) {


            current = start;
            current = currentPosition(this.getGraphics(), current.getX(), current.getY());

            makeAvailable(current, true);
            counterStart++;
            counter++;
            paintAvailable(current,this.getGraphics());
        }





        if (counter > 1 && policka[(int) (p.getX() - 20) / 20][(int) (p.getY() - 20) / 20].isAvailable() || counter >= 1 && p.getX() <= ((current.getX() + 1) * 20 + 20) && p.getX() >= (current.getX() + 1) * 20 && p.getY() <= (current.getY() + 1) * 20 + 20 && p.getY() >= (current.getY() + 1) * 20) {
            makeAvailable(current, false);
            current = policka[(int) (p.getX() - 20) / 20][(int) (p.getY() - 20) / 20];
            i=(int) (p.getX() - 20) / 20;
            j=(int) (p.getY() - 20) / 20;
            System.out.println(current.getX()+"  "+ current.getY());

            makeAvailable(current, true);

            paintAllWhite(this.getGraphics());
            kresliEnd(this.getGraphics());
            //current = currentPosition(this.getGraphics(), current.getX(), current.getY());

            paintAvailable(current,this.getGraphics());
            paintRedPoint(current,this.getGraphics());
            counter++;
        }
        if (policka[12][12].equals(current)) {
            counterWins++;
            policka[12][12].setWin(counterWins);
            current=policka[0][0];
            i=0;
            j=0;

            repaint();

        }
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            pressRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressLeft();

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {

            pressUp();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
           pressDown();

        }


    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        Tile tile = new Tile();
        if ((p.getX() - 20) / 20 >= 0 && (p.getX() - 20) / 20 <= 12 && (p.getY() - 20) / 20 >= 0 && (p.getY() - 20) / 20 <= 12) {

            if (policka[(int) (p.getX() - 20) / 20][(int) (p.getY() - 20) / 20].isAvailable()) {

                tile = policka[(int) (p.getX() - 20) / 20][(int) (p.getY() - 20) / 20];
                kresliMozne(tile, this.getGraphics());
            }
            if (!policka[(int) (p.getX() - 20) / 20][(int) (p.getY() - 20) / 20].isAvailable()) {
                System.out.println("+tu som");

                paintAvailable(current, this.getGraphics());
            }


        }

         }





        public  void kresliMozne(Tile tile,Graphics g) {
            for (Tile t : current.getAvailableTiles()) {
                if (tile.equals(t)) {
                    g.setColor(Color.green);

                    g.fillOval((t.getX() + 1) * 20, (t.getY() + 1) * 20, 20, 20);
                }

            }


        }

    }










