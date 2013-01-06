package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import com.chewielouie.tictactoad.ProgrammerMistake;
import com.chewielouie.tictactoad.PlayGameViewContract;
import com.chewielouie.tictactoad.RendersView;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
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
        mockery.checking( new Expectations() {{
            oneOf( rendersView ).render();
        }});

        new PlayingGameActivity( rendersView ) {
            // Override to make onResume() accessible for testing
            @Override
            public void onResume() {
                super.onResume();
            }
        }.onResume();

        mockery.assertIsSatisfied();
    }
}

