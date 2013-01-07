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

        for( int x = 0; x < board.width(); ++x )
            for( int y = 0; y < board.height(); ++y )
                assertEquals( Board.Piece.None,
                              board.getContentAt( new Coord( x, y ) ) );

        //BoardIterator iter = board.start();
        //for( ; iter != board.end(); ++iter )
            //assertEquals( Board.Piece.None, iter.piece() );
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

        final int test_x = 1;
        final int test_y = 0;
        board.setContentAt( new Coord( test_x, test_y ), Board.Piece.Nought );

        final NormalBoard emptyBoard = new NormalBoard();
        for( int x = 0; x < board.width(); ++x )
            for( int y = 0; y < board.height(); ++y )
                if( x != test_x && y != test_y )
                    assertEquals( emptyBoard.getContentAt( new Coord( x, y ) ),
                                  board.getContentAt( new Coord( x, y ) ) );

        //final Coord test_coord = new Coord( 1, 0 );
        //BoardIterator iter = board.start();
        //for( ; iter != board.end(); ++iter )
            //if( iter.coord() != test_coord )
                //assertEquals( emptyBoard.getContentAt( iter.coord() ),
                              //iter.piece() );
    }

    //public void is_a_value_object() {
    // Contract test?
    //public void should_throw_an_exception_when_asked_for_a_piece_outside_of_the_board() {
}


