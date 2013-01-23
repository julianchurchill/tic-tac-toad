package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.ProgrammerMistake;
import java.util.ArrayList;
import java.util.List;

public class NormalBoard implements Board {
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private Board.Piece piece = Board.Piece.None;
    private Board.Piece[][] pieces;
    private List<BoardListener> listeners = new ArrayList<BoardListener>();
    private boolean locked = false;

    public NormalBoard() {
        pieces = new Board.Piece[WIDTH][HEIGHT];
        clear();
    }

    public BoardIterator iterator() {
        return new NormalBoardIterator( pieces );
    }

    public int width() {
        return WIDTH;
    }

    public int height() {
        return HEIGHT;
    }

    public void setContentAt( Coord c, Board.Piece p ) {
        if( locked )
            return;
        pieces[c.x()][c.y()] = p;
        for( BoardListener l : listeners )
            l.boardChanged( this );
    }

    public Board.Piece getContentAt( Coord c ) {
        if( c.x() >= width() ||
            c.y() >= height() ||
            c.x() < 0 ||
            c.y() < 0 )
            throw new ProgrammerMistake( new ArrayIndexOutOfBoundsException() );
        return pieces[c.x()][c.y()];
    }

    public void addListener( BoardListener l ) {
        listeners.add( l );
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

    public Board.Piece whoHasWon() {
        if( new WinChecker( Piece.Nought ).hasWon() )
            return Piece.Nought;
        if( new WinChecker( Piece.Cross ).hasWon() )
            return Piece.Cross;
        return Piece.None;
    }

    class WinChecker {
        final Piece candidate;
        public WinChecker( Piece p ) {
            candidate = p;
        }

        public boolean hasWon() {
            return hasCompleteRow() ||
                   hasCompleteColumn() ||
                   hasCompleteDiagonal();
        }

        private boolean hasCompleteDiagonal() {
            return hasCompleteLeftToRightDiagonal() ||
                   hasCompleteRightToLeftDiagonal();
        }

        private boolean hasCompleteLeftToRightDiagonal() {
            for( int step = 0; step < width(); ++step )
                if( pieces[step][step] != candidate )
                    return false;
            return true;
        }

        private boolean hasCompleteRightToLeftDiagonal() {
            for( int step = 0; step < height(); ++step )
                if( pieces[width()-1-step][step] != candidate )
                    return false;
            return true;
        }

        private boolean hasCompleteRow() {
            for( int row = 0; row < height(); ++row )
                if( rowIsComplete( row ) )
                    return true;
            return false;
        }

        private boolean rowIsComplete( int row ) {
            for( int column = 0; column < width(); ++column )
                if( pieces[column][row] != candidate )
                    return false;
            return true;
        }

        private boolean hasCompleteColumn() {
            for( int column = 0; column < width(); ++column )
                if( columnIsComplete( column ) )
                    return true;
            return false;
        }

        private boolean columnIsComplete( int column ) {
            for( int row = 0; row < height(); ++row )
                if( pieces[column][row] != candidate )
                    return false;
            return true;
        }
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public void clear() {
        for( int x = 0; x < WIDTH; ++x )
            for( int y = 0; y < HEIGHT; ++y )
                pieces[x][y] = Board.Piece.None;
    }
}
