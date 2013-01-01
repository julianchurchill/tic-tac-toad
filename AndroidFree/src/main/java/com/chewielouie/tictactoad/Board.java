package com.chewielouie.tictactoad;

public interface Board {
    public enum Piece { None, Nought, Cross };

    public Piece getContentAt( Coord c );
}
