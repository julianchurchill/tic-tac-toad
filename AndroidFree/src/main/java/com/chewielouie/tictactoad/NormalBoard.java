package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.BoardModel;
import com.chewielouie.tictactoad.ProgrammerMistake;

public class NormalBoard implements PlayGameModel, Board {
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private Board.Piece piece = Board.Piece.None;
    private Board.Piece[][] pieces;

    public Board retrieveBoard() {
        return this;
    }

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
        if( c.x() >= width() ||
            c.y() >= height() ||
            c.x() < 0 ||
            c.y() < 0 )
            throw new ProgrammerMistake( new ArrayIndexOutOfBoundsException() );
        return pieces[c.x()][c.y()];
    }

    @Override
    public boolean equals( Object o ) {
        if( !(o instanceof NormalBoard) )
            return false;
        NormalBoard other = (NormalBoard)o;
        for( int x = 0; x < width(); ++x )
            for( int y = 0; y < height(); ++y )
                if( pieces[x][y] != other.pieces[x][y] )
                    return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for( int x = 0; x < width(); ++x )
            for( int y = 0; y < height(); ++y )
                result = prime * result + pieces[x][y].hashCode();
        return result;
    }
}
