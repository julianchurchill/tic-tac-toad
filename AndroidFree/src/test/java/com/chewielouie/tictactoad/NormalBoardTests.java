package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class NormalBoardTests extends BoardContract {

    private Mockery mockery = new Mockery();

    protected Board createBoard() {
        return new NormalBoard();
    }

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
        BoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            count++;
        assertEquals( board.width()*board.height(), count );
    }

    @Test
    public void iterator_returns_correct_piece_for_board_points() {
        final NormalBoard board = new NormalBoard();

        int count = 0;
        BoardIterator iterator = board.iterator();
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
        BoardIterator iterator = board.iterator();
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

        BoardIterator iterator = board.iterator();
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
        BoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            if( iterator.coord().equals( test_coord ) == false )
                assertEquals( emptyBoard.getContentAt( iterator.coord() ),
                              iterator.piece() );
    }

    @Test
    public void should_notify_listeners_on_content_change() {
        final NormalBoard board = new NormalBoard();
        final BoardListener listener = mockery.mock( BoardListener.class );
        board.addListener( listener );
        mockery.checking( new Expectations() {{
            oneOf( listener ).boardChanged( with( board ) );
        }});

        final Coord test_coord = new Coord( 1, 0 );
        board.setContentAt( test_coord, Board.Piece.Nought );
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
    public void recognises_horizontal_winning_board() {
        final NormalBoard top = new NormalBoard();
        top.setContentAt( new Coord( 0, 0 ), Board.Piece.Nought );
        top.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        top.setContentAt( new Coord( 2, 0 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, top.whoHasWon() );

        final NormalBoard middle = new NormalBoard();
        middle.setContentAt( new Coord( 0, 1 ), Board.Piece.Nought );
        middle.setContentAt( new Coord( 1, 1 ), Board.Piece.Nought );
        middle.setContentAt( new Coord( 2, 1 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, middle.whoHasWon() );

        final NormalBoard bottom = new NormalBoard();
        bottom.setContentAt( new Coord( 0, 2 ), Board.Piece.Nought );
        bottom.setContentAt( new Coord( 1, 2 ), Board.Piece.Nought );
        bottom.setContentAt( new Coord( 2, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, bottom.whoHasWon() );
    }

    @Test
    public void recognises_vertical_winning_board() {
        final NormalBoard left = new NormalBoard();
        left.setContentAt( new Coord( 0, 0 ), Board.Piece.Nought );
        left.setContentAt( new Coord( 0, 1 ), Board.Piece.Nought );
        left.setContentAt( new Coord( 0, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, left.whoHasWon() );

        final NormalBoard middle = new NormalBoard();
        middle.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        middle.setContentAt( new Coord( 1, 1 ), Board.Piece.Nought );
        middle.setContentAt( new Coord( 1, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, middle.whoHasWon() );

        final NormalBoard right = new NormalBoard();
        right.setContentAt( new Coord( 2, 0 ), Board.Piece.Nought );
        right.setContentAt( new Coord( 2, 1 ), Board.Piece.Nought );
        right.setContentAt( new Coord( 2, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, right.whoHasWon() );
    }

    @Test
    public void recognises_diagonal_winning_board() {
        final NormalBoard leftToRight = new NormalBoard();
        leftToRight.setContentAt( new Coord( 0, 0 ), Board.Piece.Nought );
        leftToRight.setContentAt( new Coord( 1, 1 ), Board.Piece.Nought );
        leftToRight.setContentAt( new Coord( 2, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, leftToRight.whoHasWon() );

        final NormalBoard rightToLeft = new NormalBoard();
        rightToLeft.setContentAt( new Coord( 2, 0 ), Board.Piece.Nought );
        rightToLeft.setContentAt( new Coord( 1, 1 ), Board.Piece.Nought );
        rightToLeft.setContentAt( new Coord( 0, 2 ), Board.Piece.Nought );
        assertEquals( Board.Piece.Nought, rightToLeft.whoHasWon() );
    }

    @Test
    public void recognises_winning_board_for_crosses() {
        final NormalBoard board = new NormalBoard();
        board.setContentAt( new Coord( 0, 0 ), Board.Piece.Cross );
        board.setContentAt( new Coord( 1, 1 ), Board.Piece.Cross );
        board.setContentAt( new Coord( 2, 2 ), Board.Piece.Cross );
        assertEquals( Board.Piece.Cross, board.whoHasWon() );
    }

    @Test
    public void recognises_losing_board() {
        final NormalBoard board = new NormalBoard();
        assertEquals( Board.Piece.None, board.whoHasWon() );

        board.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        assertEquals( Board.Piece.None, board.whoHasWon() );
    }

    @Test
    public void locking_the_board_stops_content_being_added() {
        final NormalBoard board = new NormalBoard();

        board.lock();
        board.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );

        assertEquals( Board.Piece.None,
                      board.getContentAt( new Coord( 1, 0 ) ) );
    }

    @Test
    public void unlocking_the_board_allows_content_to_be_added_again() {
        final NormalBoard board = new NormalBoard();

        board.lock();
        board.unlock();
        board.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );

        assertEquals( Board.Piece.Nought,
                      board.getContentAt( new Coord( 1, 0 ) ) );
    }
}

