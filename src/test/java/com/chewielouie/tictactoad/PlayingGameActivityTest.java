package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class PlayingGameActivityTest {
    @Test
    public void drawsAnEmptyBoardIfPassedNull() {
        PlayingGameActivity m = new PlayingGameActivity();
        m.onCreate( null );

        m.displayBoard( null );

        final TextView t = (TextView)m.findViewById( R.id.board );
        assertEquals( ".|.|.\n.|.|.\n.|.|.", t.getText().toString() );
    }

    //public void throwsARuntimeExceptionIfDisplayBoardIsPassedNull() {
    //public void drawsAnEmptyBoard() {
    //public void drawsTheBoardContentCorrectly() {
    //public void showsTurnPrompt();
}

