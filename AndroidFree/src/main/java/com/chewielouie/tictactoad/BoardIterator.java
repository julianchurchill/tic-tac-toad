package com.chewielouie.tictactoad;

public interface BoardIterator {
    public void next();
    public boolean hasNext();
    public Board.Piece piece();
    public Coord coord();
}

