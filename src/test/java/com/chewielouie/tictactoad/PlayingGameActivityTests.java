package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import com.chewielouie.tictactoad.ProgrammerMistake;
import com.chewielouie.tictactoad.PlayGameViewContract;
import com.chewielouie.tictactoad.RendersView;
import android.widget.Button;
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

    @Test
    public void sets_user_message_when_game_is_drawn() {
        PlayingGameActivity p = new PlayingGameActivity();
        p.onCreate( null );

        p.gameDrawn();

        final TextView t = (TextView)p.findViewById( R.id.turn_prompt );
        assertEquals( "Game drawn!", t.getText().toString() );
    }

    @Test
    public void sets_user_message_when_game_is_won_by_noughts() {
        PlayingGameActivity p = new PlayingGameActivity();
        p.onCreate( null );

        p.gameWonBy( Board.Piece.Nought );

        final TextView t = (TextView)p.findViewById( R.id.turn_prompt );
        assertEquals( "Game won by Green!", t.getText().toString() );
    }

    @Test
    public void sets_user_message_when_game_is_won_by_crosses() {
        PlayingGameActivity p = new PlayingGameActivity();
        p.onCreate( null );

        p.gameWonBy( Board.Piece.Cross );

        final TextView t = (TextView)p.findViewById( R.id.turn_prompt );
        assertEquals( "Game won by Brown!", t.getText().toString() );
    }

    @Test
    public void pressing_new_game_button_calls_presenter_to_start_new_game() {
        final RendersView rendersView = mockery.mock( RendersView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            oneOf( rendersView ).newGame();
            ignoring( board );
        }});
        PlayingGameActivity p = new PlayingGameActivity( rendersView, board );
        p.onCreate( null );

        final Button b = (Button)p.findViewById( R.id.new_game_button );
        b.performClick();

        mockery.assertIsSatisfied();
    }

    @Test
    public void new_game_button_text_is_correct() {
        PlayingGameActivity p = new PlayingGameActivity();
        p.onCreate( null );

        final Button b = (Button)p.findViewById( R.id.new_game_button );
        assertEquals( "New game", b.getText().toString() );
    }

    @Test
    public void should_reset_turn_to_noughts_on_new_game() {
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
                                         with( equal( Board.Piece.Nought ) ) );
            inSequence( pieceAlternation );
            ignoring( rendersView );
        }});
        PlayingGameActivity p = new PlayingGameActivity( rendersView, board );
        p.onCreate( null );
        p.boardTouchEvent( new Coord( 0, 0 ) );

        final Button b = (Button)p.findViewById( R.id.new_game_button );
        b.performClick();
        p.boardTouchEvent( new Coord( 0, 0 ) );

        mockery.assertIsSatisfied();
    }
}

