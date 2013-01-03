package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.ProgrammerMistake;
import org.junit.Test;

public abstract class PlayGameViewContract {

    public abstract PlayGameView createView();

    @Test
    public void throwsARuntimeExceptionIfDisplayBoardIsPassedNull() {
        PlayGameView view = createView();

        try {
            view.displayBoard( null );
            fail( "Expected exception to be thrown" );
        } catch( ProgrammerMistake e ) {
            assertTrue( e.getCause() instanceof IllegalArgumentException );
        }
    }
}


