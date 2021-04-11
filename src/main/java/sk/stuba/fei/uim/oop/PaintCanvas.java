package sk.stuba.fei.uim.oop;

import java.awt.*;

public class PaintCanvas extends Canvas {

    private Game game=new Game();

    public void paint(Graphics g) {
        game.makeMaze(g);
        Tile[][] pole=game.getPolicka();
        Dimension d = getSize();
        //System.out.println(pole[0][0].isVisited());
        int x=0;
        int y=0;
        int i=0;
        int j=0;
        int w = d.width, h = d.height;
        Color C;
        for (x = 0; x < 13; x++) {
            i=i+20;

            //C = new Color(255, 0, 255);

            //g.setColor(C);


            if (i == 280) {
                g.drawString("\n", i + 20, i);
            }

            for (y = 0; y < 13; y++ ) {
                    j=j+20;
                    if (pole[x][y].isVisited()) {
                        g.fillRect(x,y, 10, 10);
                    }


                }

            }

        }
    }




