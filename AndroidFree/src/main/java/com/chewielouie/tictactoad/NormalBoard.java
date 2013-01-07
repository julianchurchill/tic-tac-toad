package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.Board;

//public class NormalBoard implements Board {
public class NormalBoard {
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private Board.Piece piece = Board.Piece.None;
    private Board.Piece[][] pieces;

    public NormalBoard() {
        pieces = new Board.Piece[WIDTH][HEIGHT];
        for( int x = 0; x < WIDTH; ++x )
            for( int y = 0; y < HEIGHT; ++y )
                pieces[x][y] = Board.Piece.None;
    }

    public NormalBoardIterator iterator() {
        return new NormalBoardIterator( pieces );
    }

    public int width() {
        return WIDTH;
    }

    public int height() {
        return HEIGHT;
    }

    public void setContentAt( Coord c, Board.Piece p ) {
        pieces[c.x()][c.y()] = p;
    }

    public Board.Piece getContentAt( Coord c ) {
        return pieces[c.x()][c.y()];
    }
}
