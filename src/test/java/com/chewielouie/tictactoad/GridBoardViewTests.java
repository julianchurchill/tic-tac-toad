package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.GridBoardView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowImageView;
import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class GridBoardViewTests {

    private Mockery mockery = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test
    public void invalidatesSelfOnUpdateFromBoard() {
        final Board board = mockery.mock( Board.class );
        final Context context = mockery.mock( Context.class );
        final AttributeSet attributeSet = mockery.mock( AttributeSet.class );
        mockery.checking( new Expectations() {{
            ignoring( board );
            ignoring( context );
            ignoring( attributeSet );
        }});
        GridBoardView g = new GridBoardView( context, attributeSet );

        g.updateFromBoard( board );

        ShadowImageView shadowV = Robolectric.shadowOf( g );
        assertTrue( shadowV.wasInvalidated() );
    }

    @Test
    public void gridIsUpdatedToMatchBoardSizeOnUpdateFromBoard() {
        final int boardWidth = 5;
        final int boardHeight = 8;
        final Board board = mockery.mock( Board.class );
        final Context context = mockery.mock( Context.class );
        final AttributeSet attributeSet = mockery.mock( AttributeSet.class );
        mockery.checking( new Expectations() {{
            allowing( board ).width();
            will( returnValue( boardWidth ) );
            allowing( board ).height();
            will( returnValue( boardHeight ) );
            ignoring( board );
            ignoring( context );
            ignoring( attributeSet );
        }});
        GridBoardView g = new GridBoardView( context, attributeSet );

        g.updateFromBoard( board );

        assertEquals( boardWidth, g.grid().numberOfColumns() );
        assertEquals( boardHeight, g.grid().numberOfRows() );
    }

    @Test
    public void notifies_listeners_for_touch_events() {
        final Context context = null;
        final AttributeSet attributeSet = null;
        final BoardTouchListener listener = mockery.mock( BoardTouchListener.class );
        mockery.checking( new Expectations() {{
            oneOf( listener ).boardTouchEvent( with( any( Coord.class ) ) );
        }});
        GridBoardView g = new GridBoardView( context, attributeSet );
        g.addListener( listener );

        long downTime = 0;
        long eventTime = 0;
        int action = 0;
        float x = 0;
        float y = 0;
        int metaState = 0;
        g.dispatchTouchEvent( MotionEvent.obtain(
                    downTime,
                    eventTime,
                    action,
                    x,
                    y,
                    metaState ) );

        mockery.assertIsSatisfied();
    }

    //@Test
    //public void converts_screen_coords_to_board_coords_for_notify_of_touch_event() {
}


