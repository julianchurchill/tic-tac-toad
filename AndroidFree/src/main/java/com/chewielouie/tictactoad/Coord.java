package com.chewielouie.tictactoad;

public class Coord {

    final private int x;
    final private int y;

    public Coord( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals( Object c ) {
        if( !(c instanceof Coord) )
            return false;
        Coord other = (Coord)c;
        return this.x == other.x && this.y == other.y;
    }

    //@Override
    //public int hashCode() {
    //}
}

