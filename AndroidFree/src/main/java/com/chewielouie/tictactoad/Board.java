package com.chewielouie.tictactoad;

public interface Board {
    public enum Piece { None, Nought, Cross };

    public int width();
    public int height();
    public Piece getContentAt( Coord c );
    public void setContentAt( Coord c, Board.Piece p );
    public BoardIterator iterator();
    public void addListener( BoardListener l );
}
