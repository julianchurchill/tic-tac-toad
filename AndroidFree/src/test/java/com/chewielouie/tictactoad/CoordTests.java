package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordTests {

    @Test
    public void two_coords_with_the_same_value_should_be_equal() {
        Coord c1 = new Coord( 4, 76 );
        Coord c2 = new Coord( 4, 76 );

        assertEquals( c1, c2 );
    }

    //public void two_coords_with_different_values_should_not_be_equal() {
    //public void two_coords_with_the_same_value_should_have_the_same_hashcode() {
    //public void two_coords_with_different_values_should_have_different_hashcodes() {
}


