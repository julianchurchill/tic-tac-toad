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
    //@Test
    public void drawsAnEmptyBoardIfPassedNull() {
        PlayingGameActivity m = new PlayingGameActivity();
        m.onCreate( null );

        m.displayBoard( null );

        final TextView t = (TextView)m.findViewById( R.id.board );
        assertEquals( ".|.|.\n.|.|.\n.|.|.", t.getText().toString() );
    }

    @Test
    public void throwsARuntimeExceptionIfDisplayBoardIsPassedNull() {
        PlayingGameActivity m = new PlayingGameActivity();
        m.onCreate( null );

        try {
            m.displayBoard( null );
            fail( "Expected exception to be thrown" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof IllegalArgumentException );
        }
    }

    //public void drawsAnEmptyBoard() {
    //public void drawsTheBoardContentCorrectly() {
    //public void showsTurnPrompt();
}

