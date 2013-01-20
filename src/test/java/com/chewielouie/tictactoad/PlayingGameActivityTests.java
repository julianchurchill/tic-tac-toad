package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import com.chewielouie.tictactoad.ProgrammerMistake;
import com.chewielouie.tictactoad.PlayGameViewContract;
import com.chewielouie.tictactoad.RendersView;
import android.widget.ImageView;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.Robolectric;
import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class PlayingGameActivityTests
        extends PlayGameViewContract {

    private Mockery mockery = new Mockery();

    protected PlayGameView createView() {
        return createActivity();
    }

    PlayingGameActivity createActivity() {
        PlayingGameActivity p = new PlayingGameActivity();
        p.onCreate( null );
        return p;
    }

    @Test
    public void throwsARuntimeExceptionIfDisplayBoardIsPassedNull() {
        PlayingGameActivity activity = createActivity();

        try {
            activity.displayBoard( null );
            fail( "Expected exception to be thrown" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof IllegalArgumentException );
        }
    }

    @Test
    public void drawsAnEmptyBoard() {
        PlayingGameActivity activity = createActivity();
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( any( Coord.class ) ) );
            will( returnValue( Board.Piece.None ) );
            allowing( board ).width();
            will( returnValue( 3 ) );
            allowing( board ).height();
            will( returnValue( 3 ) );
        }});

        activity.displayBoard( board );

        final TextView t = (TextView)activity.findViewById( R.id.board );
        assertEquals( ".|.|.\n.|.|.\n.|.|.", t.getText().toString() );
    }

    @Test
    public void drawsABoardFullOfNoughtsCorrectly() {
        PlayingGameActivity activity = createActivity();
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( any( Coord.class ) ) );
            will( returnValue( Board.Piece.Nought ) );
            allowing( board ).width();
            will( returnValue( 3 ) );
            allowing( board ).height();
            will( returnValue( 3 ) );
        }});

        activity.displayBoard( board );

        final TextView t = (TextView)activity.findViewById( R.id.board );
        assertEquals( "O|O|O\nO|O|O\nO|O|O", t.getText().toString() );
    }

    @Test
    public void drawsABoardFullOfCrossesCorrectly() {
        PlayingGameActivity activity = createActivity();
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( any( Coord.class ) ) );
            will( returnValue( Board.Piece.Cross ) );
            allowing( board ).width();
            will( returnValue( 3 ) );
            allowing( board ).height();
            will( returnValue( 3 ) );
        }});

        activity.displayBoard( board );

        final TextView t = (TextView)activity.findViewById( R.id.board );
        assertEquals( "X|X|X\nX|X|X\nX|X|X", t.getText().toString() );
    }

    @Test
    public void showsTurnPrompt() {
        PlayingGameActivity activity = createActivity();

        final TextView t = (TextView)activity.findViewById( R.id.turn_prompt );
        assertEquals( "Your turn...", t.getText().toString() );
    }

    @Test
    public void rendersThePresenterOnResume() {
        final RendersView rendersView = mockery.mock( RendersView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            oneOf( rendersView ).render();
            ignoring( board );
        }});

        new PlayingGameActivity( rendersView, board ) {
            // Override to make onResume() accessible for testing
            @Override
            public void onResume() {
                super.onResume();
            }
        }.onResume();

        mockery.assertIsSatisfied();
    }

    @Test
    public void updates_board_when_touch_received_from_grid() {
        final RendersView rendersView = mockery.mock( RendersView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( any( Coord.class )));
            will( returnValue( Board.Piece.None ) );
            oneOf( board ).setContentAt( with( equal( new Coord( 1, 1 ) ) ),
                                         with( any( Board.Piece.class ) ) );
        }});
        PlayingGameActivity p = new PlayingGameActivity( rendersView, board );

        p.boardTouchEvent( new Coord( 1, 1 ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void updates_board_when_touch_received_from_grid_with_alternating_pieces() {
        final RendersView rendersView = mockery.mock( RendersView.class );
        final Board board = mockery.mock( Board.class );
        final Sequence pieceAlternation = mockery.sequence( "piece alternation" );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( any( Coord.class )));
            will( returnValue( Board.Piece.None ) );
            oneOf( board ).setContentAt( with( any( Coord.class ) ),
                                         with( equal( Board.Piece.Nought ) ) );
            inSequence( pieceAlternation );
            oneOf( board ).setContentAt( with( any( Coord.class ) ),
                                         with( equal( Board.Piece.Cross ) ) );
            inSequence( pieceAlternation );
            oneOf( board ).setContentAt( with( any( Coord.class ) ),
                                         with( equal( Board.Piece.Nought ) ) );
            inSequence( pieceAlternation );
        }});
        PlayingGameActivity p = new PlayingGameActivity( rendersView, board );

        p.boardTouchEvent( new Coord( 0, 0 ) );
        p.boardTouchEvent( new Coord( 1, 1 ) );
        p.boardTouchEvent( new Coord( 2, 2 ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void ignores_board_touch_from_grid_when_space_already_occupied() {
        final RendersView rendersView = mockery.mock( RendersView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).getContentAt( with( equal( new Coord( 0, 0 ))));
            will( returnValue( Board.Piece.Nought ) );
            never( board ).setContentAt( with( any( Coord.class ) ),
                                         with( any( Board.Piece.class ) ) );
        }});
        PlayingGameActivity p = new PlayingGameActivity( rendersView, board );

        p.boardTouchEvent( new Coord( 0, 0 ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void registers_for_touch_events_from_grid() {
        final BoardTouchGenerator boardTouchGen =
            mockery.mock( BoardTouchGenerator.class );
        final PlayingGameActivity p = new PlayingGameActivity( boardTouchGen );

        mockery.checking( new Expectations() {{
            oneOf( boardTouchGen ).addListener( p );
        }});

        p.onCreate( null );

        mockery.assertIsSatisfied();
    }

    @Test
    public void updates_graphical_board_when_displaying() {
        final Board board = mockery.mock( Board.class );
        final GraphicalBoardView graphicalBoardView =
            mockery.mock( GraphicalBoardView.class );
        final PlayingGameActivity p =
            new PlayingGameActivity( graphicalBoardView );
        p.onCreate( null );

        mockery.checking( new Expectations() {{
            ignoring( board );
            oneOf( graphicalBoardView ).updateFromBoard( board );
        }});

        p.displayBoard( board );

        mockery.assertIsSatisfied();
    }
}

