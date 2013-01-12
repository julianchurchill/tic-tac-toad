package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.BoardIterator;

public class NormalBoardIterator implements BoardIterator {
    private int width = 0;
    private int height = 0;
    private int currentIndex = 0;
    private Board.Piece[][] pieces;

    NormalBoardIterator( Board.Piece[][] p ) {
        this.pieces = p;
        this.width = p.length;
        this.height = p[0].length;
    }

    public void next() {
        currentIndex++;
    }

    public boolean hasNext() {
        return currentIndex != width*height;
    }

    public Board.Piece piece() {
        return pieces[x()][y()];
    }

    public Coord coord() {
        return new Coord( x(), y() );
    }

    private int x() {
        return currentIndex % width;
    }

    private int y() {
        return currentIndex / width;
    }
}

