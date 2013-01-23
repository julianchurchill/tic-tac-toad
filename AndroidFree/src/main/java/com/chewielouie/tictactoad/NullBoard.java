package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.Board;

public class NullBoard implements Board {
    public int width() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public Piece getContentAt( Coord c ) {
        return Board.Piece.None;
    }

    public void setContentAt( Coord c, Board.Piece p ) {
    }

    public BoardIterator iterator() {
        return new BoardIterator() {
            public void next() {}
            public boolean hasNext() { return false; }
            public Board.Piece piece() { return Board.Piece.None; }
            public Coord coord() { return new Coord( 0, 0 ); }
        };
    }

    public void addListener( BoardListener l ) {
    }

    public Piece whoHasWon() {
        return Piece.None;
    }

    public void lock() {
    }

    public void unlock() {
    }

    public void clear() {
    }
}

