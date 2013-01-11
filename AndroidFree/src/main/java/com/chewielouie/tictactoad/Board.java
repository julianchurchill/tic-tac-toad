package com.chewielouie.tictactoad;

public interface Board {
    public enum Piece { None, Nought, Cross };

    public int width();
    public int height();
    public Piece getContentAt( Coord c );
}
