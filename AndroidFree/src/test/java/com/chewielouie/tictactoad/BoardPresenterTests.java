import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class BoardPresenterTests {

    private Mockery mockery = new Mockery();

    @Test
    public void should_tell_view_about_board_when_rendering() {
        final BoardView view = mockery.mock( BoardView.class );
        final BoardModel model = mockery.mock( BoardModel.class );
        final Board board = new Board();
        BoardPresenter p = new BoardPresenter( model, view );

        mockery.checking( new Expectations() {{
            allowing( model ).retrieveBoard();
            will( returnValue( board ) );

            oneOf( view ).displayBoard( board );
        }});

        p.render();
    }
}

