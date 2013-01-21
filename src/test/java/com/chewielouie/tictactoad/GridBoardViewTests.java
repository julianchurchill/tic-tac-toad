package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.GridBoardView;
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
        mockery.checking( new Expectations() {{
            ignoring( board );
        }});
        GridBoardView g = new GridBoardView( null, null );

        g.updateFromBoard( board );

        ShadowImageView shadowV = Robolectric.shadowOf( g );
        assertTrue( shadowV.wasInvalidated() );
    }

    @Test
    public void gridIsUpdatedToMatchBoardSizeOnUpdateFromBoard() {
        final int boardWidth = 5;
        final int boardHeight = 8;
        final Board board = mockery.mock( Board.class );
        mockery.checking( new Expectations() {{
            allowing( board ).width();
            will( returnValue( boardWidth ) );
            allowing( board ).height();
            will( returnValue( boardHeight ) );
            ignoring( board );
        }});
        GridBoardView g = new GridBoardView( null, null );

        g.updateFromBoard( board );

        assertEquals( boardWidth, g.grid().numberOfColumns() );
        assertEquals( boardHeight, g.grid().numberOfRows() );
    }

    @Test
    public void notifies_all_listeners_for_touch_events() {
        final BoardTouchListener listener1 =
            mockery.mock( BoardTouchListener.class, "BTL1" );
        final BoardTouchListener listener2 =
            mockery.mock( BoardTouchListener.class, "BTL2" );
        mockery.checking( new Expectations() {{
            oneOf( listener1 ).boardTouchEvent( with( any( Coord.class ) ) );
            oneOf( listener2 ).boardTouchEvent( with( any( Coord.class ) ) );
        }});
        GridBoardView g = new GridBoardView( null, null );
        g.addListener( listener1 );
        g.addListener( listener2 );

        long downTime = 0;
        long eventTime = 0;
        int action = 0;
        float x = 0;
        float y = 0;
        int metaState = 0;
        g.dispatchTouchEvent( MotionEvent.obtain( downTime, eventTime, action,
                    x, y, metaState ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void converts_screen_coords_to_board_coords_for_notify_of_touch_event_at_origin() {
        final BoardTouchListener listener = mockery.mock( BoardTouchListener.class );
        mockery.checking( new Expectations() {{
            oneOf( listener ).boardTouchEvent( with( new Coord( 0, 0 ) ) );
        }});
        GridBoardView g = new GridBoardView( null, null );
        g.addListener( listener );

        long downTime = 0;
        long eventTime = 0;
        int action = 0;
        float x = 0;
        float y = 0;
        int metaState = 0;
        g.dispatchTouchEvent( MotionEvent.obtain( downTime, eventTime, action,
                    x, y, metaState ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void converts_screen_coords_to_board_coords_for_notify_of_touch_event_at_non_origin() {
        final Coord boardCoord = new Coord( 1, 2 );
        final BoardTouchListener listener = mockery.mock( BoardTouchListener.class );
        mockery.checking( new Expectations() {{
            oneOf( listener ).boardTouchEvent( with( boardCoord ) );
        }});
        GridBoardView g = new GridBoardView( null, null );
        g.addListener( listener );

        long downTime = 0;
        long eventTime = 0;
        int action = 0;
        float x = g.grid().cellWidth() * boardCoord.x();
        float y = g.grid().cellHeight() * boardCoord.y();
        int metaState = 0;
        g.dispatchTouchEvent( MotionEvent.obtain( downTime, eventTime, action,
                    x, y, metaState ) );

        mockery.assertIsSatisfied();
    }

    @Test
    public void ignore_off_grid_touch_events() {
        final BoardTouchListener listener = mockery.mock( BoardTouchListener.class );
        mockery.checking( new Expectations() {{
            never( listener ).boardTouchEvent( with( any( Coord.class ) ) );
        }});
        GridBoardView g = new GridBoardView( null, null );
        g.addListener( listener );

        long downTime = 0;
        long eventTime = 0;
        int action = 0;
        float x = g.grid().numberOfColumns() * g.grid().cellWidth();
        float y = 0;
        int metaState = 0;
        g.dispatchTouchEvent( MotionEvent.obtain( downTime, eventTime, action,
                    x, y, metaState ) );

        mockery.assertIsSatisfied();
    }
}


