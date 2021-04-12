package sk.stuba.fei.uim.oop;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Game extends Canvas {

    private Tile[][] policka;
    private Stack<Tile> backtrackStack=new Stack();
    private Stack<Tile> helpStack=new Stack();

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


    public void makeMaze(Graphics g) {

        Tile start=this.policka[0][0];
        addTileNeighbours();
        randomizedDFS(start);

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

        Random rand = new Random();
        List <Tile> susedia=tile.getNeighbours();
        Tile randomSused=susedia.get(rand.nextInt(susedia.size()));
        if (!randomSused.isVisited()) {
            return randomSused;
        }
        int pomocna_pocet=0;
        for (Tile pole: tile.getNeighbours()) {

            if (!pole.isVisited()) {
                pomocna_pocet++;
            }
        }
         if (pomocna_pocet==0) {

             backtrack();
         }

        randomUnvisitedNeigbour(tile);
        return tile;
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


        System.out.println("spajam tile:"+tile.getX()+tile.getY()+" s next: "+next.getX()+next.getY());
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

}
