package com.chewielouie.tictactoad;

import static org.junit.Assert.*;

import com.chewielouie.tictactoad.OpenGridShape;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class OpenGridShapeTests {

    @Test
    public void totalWidthIsBasedOnCellWidthAndNumberOfColumns() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.width(), o.cellWidth() * columns );
    }

    @Test
    public void totalHeightIsBasedOnCellHeightAndNumberOfRows() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.height(), o.cellHeight() * rows );
    }

    @Test
    public void cellBoundsLeftIsCorrect() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.cellBounds( new Coord( 2, 2 ) ).left, 2 * o.cellWidth() );
    }

    @Test
    public void cellBoundsTopIsCorrect() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.cellBounds( new Coord( 2, 2 ) ).top, 2 * o.cellHeight() );
    }

    @Test
    public void cellBoundsRightIsCorrect() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.cellBounds( new Coord( 2, 2 ) ).right, 3 * o.cellWidth() );
    }

    @Test
    public void cellBoundsBottomIsCorrect() {
        final int columns = 3;
        final int rows = 7;
        OpenGridShape o = new OpenGridShape( columns, rows );

        assertEquals( o.cellBounds( new Coord( 2, 2 ) ).bottom, 3 * o.cellHeight() );
    }
}

