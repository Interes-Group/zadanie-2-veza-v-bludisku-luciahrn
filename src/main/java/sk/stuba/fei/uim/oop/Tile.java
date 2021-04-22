package sk.stuba.fei.uim.oop;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public  class Tile {

    private boolean visited;

    private List<Tile> neighbours=new ArrayList<>();
    private Stack<Tile> availableTiles=new Stack<>();
    private int x=0;
    private int y=0;
    private boolean horna=true;
    private boolean dolna=true;
    private boolean prava=true;
    private boolean lava=true;
    private boolean start,end,available,mouseclicked;
    private int win;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public Stack<Tile> getAvailableTiles() {
        return availableTiles;
    }

    public void setAvailableTiles(Stack<Tile> availableTiles) {
        this.availableTiles = availableTiles;
    }

    public void addAvailableTiles(Tile t) {
        availableTiles.add(t);
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isMouseclicked() {
        return mouseclicked;
    }

    public void setMouseclicked(boolean mouseclicked) {
        this.mouseclicked = mouseclicked;
    }

    public boolean isHorna() {
        return horna;
    }

    public boolean isDolna() {
        return dolna;
    }

    public boolean isPrava() {
        return prava;
    }

    public boolean isLava() {
        return lava;
    }

    public void setHorna(boolean horna) {
        this.horna = horna;
    }

    public void setDolna(boolean dolna) {
        this.dolna = dolna;
    }

    public void setPrava(boolean prava) {
        this.prava = prava;
    }

    public void setLava(boolean lava) {
        this.lava = lava;
    }



    public Tile() {
        this.visited = false;
    }


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean jeNavstiveny() {
        if(visited==true) {
            return true;

        }
        return false;
    }

    public void addNeighbours(List<Tile> neighbours) {
        this.neighbours.addAll(neighbours);

    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }



    public void setNeighbours(List<Tile> neighbours) {
        this.neighbours = neighbours;
    }

    public void clearNeighbours() {
        this.neighbours.clear();


    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

