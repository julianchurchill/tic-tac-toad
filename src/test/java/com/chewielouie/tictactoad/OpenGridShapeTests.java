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
}

