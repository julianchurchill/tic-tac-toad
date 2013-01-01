package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import com.chewielouie.tictactoad.ProgrammerMistake;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class PlayingGameActivityTest {

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

        activity.displayBoard( new Board() );

        final TextView t = (TextView)activity.findViewById( R.id.board );
        assertEquals( ".|.|.\n.|.|.\n.|.|.", t.getText().toString() );
    }

    //public void drawsTheBoardContentCorrectly() {
    //public void showsTurnPrompt();
}

