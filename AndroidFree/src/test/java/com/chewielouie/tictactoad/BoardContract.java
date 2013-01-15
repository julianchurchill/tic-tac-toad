package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class BoardContract {

    protected abstract Board createBoard();

    @Test
    public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board_right_edge() {
        Board board = createBoard();

        try {
            board.getContentAt( new Coord( board.width(), 0 ) );
            fail( "Why did you return the content of a board point outside " +
                  "of the board?!" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof ArrayIndexOutOfBoundsException );
        }
    }

    @Test
    public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board_left_edge() {
        Board board = createBoard();

        try {
            board.getContentAt( new Coord( -1, 0 ) );
            fail( "Why did you return the content of a board point outside " +
                  "of the board?!" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof ArrayIndexOutOfBoundsException );
        }
    }

    @Test
    public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board_top_edge() {
        Board board = createBoard();

        try {
            board.getContentAt( new Coord( 0, -1 ) );
            fail( "Why did you return the content of a board point outside " +
                  "of the board?!" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof ArrayIndexOutOfBoundsException );
        }
    }

    @Test
    public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board_bottom_edge() {
        Board board = createBoard();

        try {
            board.getContentAt( new Coord( 0, board.height() ) );
            fail( "Why did you return the content of a board point outside " +
                  "of the board?!" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof ArrayIndexOutOfBoundsException );
        }
    }
}



