package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import com.chewielouie.tictactoad.ProgrammerMistake;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class PlayingGameActivityTest {

    private Mockery mockery = new Mockery();

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
            allowing( board ).getContent( with( any( Coord.class ) ) );
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
            allowing( board ).getContent( with( any( Coord.class ) ) );
            will( returnValue( Board.Piece.Nought ) );
        }});

        activity.displayBoard( board );

        final TextView t = (TextView)activity.findViewById( R.id.board );
        assertEquals( "O|O|O\nO|O|O\nO|O|O", t.getText().toString() );
    }

    //public void drawsABoardFullOfCrossesCorrectly() {
    //public void showsTurnPrompt();
}

