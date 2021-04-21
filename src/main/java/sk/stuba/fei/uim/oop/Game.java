package sk.stuba.fei.uim.oop;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Game extends Canvas {

    private Tile[][] policka;
    private Stack<Tile> backtrackStack=new Stack();
    private Stack<Tile> helpStack=new Stack();
    private Stack <Tile>nextStack=new Stack<>();

    public static final int BOARD_SIZE = 13;
    public Game() {
        this.policka = new Tile[BOARD_SIZE][BOARD_SIZE];
        fillBoard();
    }

    public Tile[][] getPolicka() {
        return policka;
    }

    private void fillBoard() {
        for ( int  i = 0; i < BOARD_SIZE; i++) {
            for ( int  j = 0; j < BOARD_SIZE; j++) {
                this.policka[i][j] = new Tile();
                this.policka[i][j].setX(i);
                this.policka[i][j].setY(j);

            }
        }
    }


    public void makeMaze() {

        Tile start=this.policka[0][0];
        nextStack.add(start);
        addTileNeighbours();
        randomizedDFS(start);
        Application canvas=new Application(policka,nextStack);
        canvas.kresli();
        findAvailable ();

    }

    public void randomizedDFS (Tile tile) {
        tile.setVisited(true);
        Tile next=randomUnvisitedNeigbour(tile);
        while (Objects.nonNull(next) ) {
            helpStack.add(tile);
            if (!helpStack.contains(next)) {

                makeStack(tile);

                connectCells(tile,next);
            }
            randomizedDFS(next);
            next=randomUnvisitedNeigbour(tile);

        }
    }

    public Tile randomUnvisitedNeigbour(Tile tile) {
        if (nextStack.size()<169) {
            Random rand = new Random();
            List<Tile> susedia = tile.getNeighbours();
            Tile randomSused = susedia.get(rand.nextInt(susedia.size()));
            if (!randomSused.isVisited()) {
                return randomSused;
            }
            int pomocna_pocet = 0;
            for (Tile pole : tile.getNeighbours()) {

                if (!pole.isVisited()) {
                    pomocna_pocet++;
                }
            }
            if (pomocna_pocet == 0) {

                backtrack();
            }

            randomUnvisitedNeigbour(tile);
            return tile;
        }
        return null;
    }



    private void addTileNeighbours() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                    Tile pole = (Tile) this.policka[i][j];
                    List<Tile> neighbours = new ArrayList<>();
                    if (i - 1 >= 0) {

                        neighbours.add(this.policka[i - 1][j]);

                    }
                    if (i + 1 < BOARD_SIZE) {
                        neighbours.add(this.policka[i + 1][j]);
                    }
                    if (j - 1 >= 0) {
                        neighbours.add(this.policka[i][j - 1]);
                    }
                    if (j + 1 < BOARD_SIZE) {
                        neighbours.add(this.policka[i][j + 1]);
                    }
                    pole.addNeighbours(neighbours);

            }
        }
    }

    public void connectCells(Tile tile,Tile next) {


        nextStack.add(next);

        //steny
        if (next.getY()==tile.getY()-1) {
            tile.setHorna(false);
            next.setDolna(false);


        }
        if (next.getY()==tile.getY()+1) {
            tile.setDolna(false);
            next.setHorna(false);




        }
        if (next.getX()==tile.getX()-1) {
            tile.setLava(false);
            next.setPrava(false);




        }
        if (next.getX()==tile.getX()+1) {
            tile.setPrava(false);
            next.setLava(false);




        }


    }



    public Stack makeStack(Tile tile) {

        backtrackStack.add(tile);
        return backtrackStack;
    }

    public void backtrack () {
        if (!backtrackStack.isEmpty()) {
            Tile posledny = backtrackStack.pop();
            int pomocna_pocet = 0;
            for (Tile pole : posledny.getNeighbours()) {

                if (!pole.isVisited()) {

                    randomizedDFS(posledny);
                    pomocna_pocet++;
                }
            }
            if (pomocna_pocet == 0) {

                backtrack();
            }
        }


    }

    public void findAvailable () {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                chceckIfAvailable(policka[i][j], i, j);




            }
        }


    }

    public void chceckIfAvailable(Tile tile,int i,int j) {
        int k=0;
        int l=0;
        int counter=0;
        //System.err.println("Tu som");

        if (!tile.isDolna() ) {

            counter=0;
                k=i;
                for (l = j; l < 13; l++) {
                    //System.err.println("Tu som1");

                    if (!policka[k][l].isHorna() && counter==0) {
                        //System.err.println("Tu som2");
                        tile.addAvailableTiles(policka[k][l]);
                        //policka[k][l].addAvailableTiles(tile);
                        //System.err.println("TILE:"+tile.getX()+tile.getY()+" ma suseda  "+policka[k][l].getX()+policka[k][l].getY());

                    }
                    if (policka[k][l].isHorna() && tile.getY()!=l) {
                        //System.err.println("Tu som3");
                        counter++;
                    }
                }

        }

        if (!tile.isHorna() ) {

            counter=0;
            k=i;
            for (l = j; l >= 0;l-- ) {

                if (!policka[k][l].isDolna() && counter==0) {
                    tile.addAvailableTiles(policka[k][l]);
                    //policka[k][l].addAvailableTiles(tile);
                    //System.err.println("TILE:"+tile.getX()+tile.getY()+" ma suseda  "+policka[k][l].getX()+policka[k][l].getY());
                }
                if (policka[k][l].isDolna() && tile.getY()!=l) {
                    counter++;
                }
            }

        }

        if (!tile.isLava() ) {
            //System.err.println("Tu som5");
            counter=0;
            l=j;
            for (k = i; k >= 0;k-- ) {
                //System.err.println("Tu som6");
                if (!policka[k][l].isPrava() && counter==0) {
                    tile.addAvailableTiles(policka[k][l]);
                    //policka[k][l].addAvailableTiles(tile);
                    //System.err.println("TILE:"+tile.getX()+tile.getY()+" ma suseda  "+policka[k][l].getX()+policka[k][l].getY());
                }
                if (policka[k][l].isPrava() &&tile.getX()!=k) {
                    counter++;
                }
            }

        }

        if (!tile.isPrava() ) {
            //System.err.println("Tu som7");
            counter=0;
            l=j;
            for (k = i; k <= 12;k++ ) {
                //System.err.println("Tu som8");
                if (!policka[k][l].isLava()&& counter==0) {
                    tile.addAvailableTiles(policka[k][l]);
                    //policka[k][l].addAvailableTiles(tile);
                    //System.err.println("TILE:"+tile.getX()+tile.getY()+" ma suseda  "+policka[k][l].getX()+policka[k][l].getY());
                }
                if (policka[k][l].isLava() && tile.getX()!=k) {
                    counter++;
                }
            }

        }






    }

    /*public void chceckIfAvailable(Tile tile,int i,int j) {
        for (Tile t: tile.getNeighbours()) {
            while (!tile.isDolna()) {
                if (!t.isHorna()) {
                    policka[i][j].addAvailableTiles(t);
                        chceckIfAvailable(t, i, j);


                }

            }
            while (!tile.isHorna()) {
                if (!t.isDolna()) {
                    policka[i][j].addAvailableTiles(t);
                    chceckIfAvailable(t, i, j);


                }

            }
             while(!tile.isLava()) {
                if (!t.isPrava()) {
                    policka[i][j].addAvailableTiles(t);
                    chceckIfAvailable(t, i, j);


                }

            }
            while (!tile.isPrava()) {
                if (!t.isLava()) {
                    policka[i][j].addAvailableTiles(t);
                    chceckIfAvailable(t, i, j);


                }

            }
        }
    }*/

    public Stack<Tile> getNextStack() {
        return nextStack;
    }
}
