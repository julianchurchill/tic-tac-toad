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

    @Test
    public void two_coords_with_different_values_should_not_be_equal() {
        Coord c1 = new Coord( 4, 76 );
        Coord c2 = new Coord( 82, 3 );

        assertNotEquals( c1, c2 );
    }

    @Test
    public void a_coord_is_not_equal_to_a_non_coord() {
        Coord c1 = new Coord( 4, 76 );
        Object c2 = new Object();

        assertNotEquals( c1, c2 );
    }

    @Test
    public void two_coords_with_the_same_value_should_have_the_same_hashcode() {
        Coord c1 = new Coord( 4, 76 );
        Coord c2 = new Coord( 4, 76 );

        assertEquals( c1.hashCode(), c2.hashCode() );
    }

    @Test
    public void two_coords_with_different_values_should_have_different_hashcodes() {
        Coord c1 = new Coord( 4, 76 );
        Coord c2 = new Coord( 82, 3 );

        assertNotEquals( c1.hashCode(), c2.hashCode() );
    }

    @Test
    public void to_string() {
        Coord c1 = new Coord( 4, 76 );

        assertEquals( "(4,76)", c1.toString() );
    }
}


