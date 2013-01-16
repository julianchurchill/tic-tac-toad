package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class PlayGamePresenterTests {

    private Mockery mockery = new Mockery();

    @Test
    public void should_tell_view_about_board_when_rendering() {
        final PlayGameView view = mockery.mock( PlayGameView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            oneOf( view ).displayBoard( board );
            ignoring( board );
        }});
        PlayGamePresenter p = new PlayGamePresenter( board, view );

        p.render();
    }

    @Test
    public void listens_to_board_for_changes() {
        final PlayGameView view = mockery.mock( PlayGameView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            oneOf( board ).addListener( with( any( BoardListener.class ) ) );
        }});

        new PlayGamePresenter( board, view );
    }

    @Test
    public void displays_the_board_using_the_view_upon_board_change() {
        final PlayGameView view = mockery.mock( PlayGameView.class );
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            oneOf( view ).displayBoard( board );
            ignoring( board );
        }});
        PlayGamePresenter p = new PlayGamePresenter( board, view );

        p.boardChanged( board );
    }
}

