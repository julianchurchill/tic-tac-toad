package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import org.junit.Test;

public class NormalBoardTests {

    @Test
    public void should_be_3_by_3() {
        final NormalBoard board = new NormalBoard();

        assertEquals( 3, board.width() );
        assertEquals( 3, board.height() );
    }

    @Test
    public void iterator_can_retrieve_correct_number_of_board_points() {
        final NormalBoard board = new NormalBoard();

        int count = 0;
        NormalBoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            count++;
        assertEquals( board.width()*board.height(), count );
    }

    @Test
    public void iterator_returns_correct_piece_for_board_points() {
        final NormalBoard board = new NormalBoard();

        int count = 0;
        NormalBoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() ) {
            Coord c = new Coord( count % board.width(),
                                 count / board.width() );
            assertEquals( board.getContentAt( c ),
                          iterator.piece() );
            count++;
        }
    }

    @Test
    public void iterator_returns_correct_coord_for_board_points() {
        final NormalBoard board = new NormalBoard();

        int count = 0;
        NormalBoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() ) {
            Coord c = new Coord( count % board.width(),
                                 count / board.width() );
            assertEquals( c, iterator.coord() );
            count++;
        }
    }

    @Test
    public void should_be_empty_on_construction() {
        final NormalBoard board = new NormalBoard();

        NormalBoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            assertEquals( Board.Piece.None, iterator.piece() );
    }

    @Test
    public void should_return_piece_that_was_set() {
        final NormalBoard board = new NormalBoard();

        board.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );

        assertEquals( Board.Piece.Nought, board.getContentAt( new Coord( 1, 0 ) ) );
    }

    @Test
    public void should_not_change_any_other_piece_when_one_is_set() {
        final NormalBoard board = new NormalBoard();

        final Coord test_coord = new Coord( 1, 0 );
        board.setContentAt( test_coord, Board.Piece.Nought );

        final NormalBoard emptyBoard = new NormalBoard();
        NormalBoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            if( iterator.coord().equals( test_coord ) == false )
                assertEquals( emptyBoard.getContentAt( iterator.coord() ),
                              iterator.piece() );
    }

    @Test
    public void two_boards_with_the_same_value_should_be_equal() {
        final NormalBoard board1 = new NormalBoard();
        board1.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        final NormalBoard board2 = new NormalBoard();
        board2.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );

        assertEquals( board1, board2 );
    }

    @Test
    public void two_boards_with_different_values_should_not_be_equal() {
        final NormalBoard board1 = new NormalBoard();
        board1.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        final NormalBoard board2 = new NormalBoard();

        assertNotEquals( board1, board2 );
    }

    @Test
    public void a_board_is_not_equal_to_a_non_board() {
        final NormalBoard board = new NormalBoard();
        Object notABoard = new Object();

        assertNotEquals( board, notABoard );
    }

    @Test
    public void two_boards_with_the_same_value_should_have_the_same_hashcode() {
        final NormalBoard board1 = new NormalBoard();
        board1.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        final NormalBoard board2 = new NormalBoard();
        board2.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );

        assertEquals( board1.hashCode(), board2.hashCode() );
    }

    @Test
    public void two_boards_with_different_values_should_have_different_hashcodes() {
        final NormalBoard board1 = new NormalBoard();
        board1.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        final NormalBoard board2 = new NormalBoard();

        assertNotEquals( board1.hashCode(), board2.hashCode() );
    }

    @Test
    public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board_right_edge() {
        final NormalBoard board = new NormalBoard();

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
        final NormalBoard board = new NormalBoard();

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
        final NormalBoard board = new NormalBoard();

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
        final NormalBoard board = new NormalBoard();

        try {
            board.getContentAt( new Coord( 0, board.height() ) );
            fail( "Why did you return the content of a board point outside " +
                  "of the board?!" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof ArrayIndexOutOfBoundsException );
        }
    }
}


