package util;

import constant.GameMap;
import pane.BoardPane;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    private final int row;
    private final int col;
    private final GameMap gameMap;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
        gameMap=new GameMap();
    }
    public Coordinate getUpward() {
        return new Coordinate(row - 1, col);
    }

    public Coordinate getDownward() {
        return new Coordinate(row + 1, col);
    }

    public Coordinate getLHS() {
        return new Coordinate(row, col - 1);
    }

    public Coordinate getRHS() {
        return new Coordinate(row, col + 1);
    }

    public Coordinate getTopLeft() {
        return this.getUpward().getLHS();
    }

    public Coordinate getTopRight() {
        return this.getUpward().getRHS();
    }

    public Coordinate getBottomLeft() {
        return this.getDownward().getLHS();
    }

    public Coordinate getBottomRight() {
        return this.getDownward().getRHS();
    }

    @Override
    public boolean equals(Object coord) {
        if (coord instanceof Coordinate) {
            Coordinate c = (Coordinate) coord;
            return (c.getCol() == col && c.getRow() == row);
        }
        throw new IllegalArgumentException();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isCoordinateExist(int x, int y) {
        if ((x < 0 || x > gameMap.getWidth()) || (y < 0 || y > gameMap.getHeight())) {
            return false;
        }else{
            return gameMap.getCoordinate(y, x) != 1;
        }
    }

    @Override
    public String toString() {
        String infoRow = String.valueOf(8 - row);
        String infoCol = String.valueOf((char) ((col + 1) + 96));
        return infoCol + infoRow;
    }

}
