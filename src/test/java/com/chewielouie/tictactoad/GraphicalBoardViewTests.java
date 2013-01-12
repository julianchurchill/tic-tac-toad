package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.GraphicalBoardView;
import android.content.Context;
import android.util.AttributeSet;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowImageView;
import org.jmock.*;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class GraphicalBoardViewTests {

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
        GraphicalBoardView g = new GraphicalBoardView( context, attributeSet );

        g.updateFromBoard( board );

        ShadowImageView shadowV = Robolectric.shadowOf( g );
        assertTrue( shadowV.wasInvalidated() );
    }
}


