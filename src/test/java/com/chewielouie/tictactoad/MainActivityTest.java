package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.Board;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    @Test
    public void drawsTheBoardPassedToIt() {
        MainActivity m = new MainActivity();
        m.onCreate( null );

        m.displayBoard( new Board() );

        final TextView t = (TextView)m.findViewById( R.id.board );
        assertEquals( ".|.|.\n.|.|.\n.|.|.", t.getText().toString() );
    }

    //public void showsTurnPrompt();
    //public void drawsAnEmptyBoardIfPassedNull();
}

