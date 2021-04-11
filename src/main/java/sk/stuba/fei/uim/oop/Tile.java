package sk.stuba.fei.uim.oop;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public  class Tile {

    private boolean visited;

    private List<Tile> neighbours=new ArrayList<>();
    private int x=0;
    private int y=0;


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

