package com.chewielouie.tictactoad;

public interface Board {
    public enum Piece { None, Nought, Cross };

    public Piece getContent( Coord c );
}
