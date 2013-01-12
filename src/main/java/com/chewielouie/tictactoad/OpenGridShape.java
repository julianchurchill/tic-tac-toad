package com.chewielouie.tictactoad;

import android.graphics.Color;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;

public class OpenGridShape extends ShapeDrawable {
    private int left = 0;
    private int top = 0;
    private int cellWidth = 100;
    private int cellHeight = 100;
    private int numberOfColumns = 3;
    private int numberOfRows = 3;
    private int lineWidth = 2;

    public OpenGridShape() {
        super();
        Path path = new Path();
        addVerticalLines( path );
        addHorizontalLines( path );
        setShape( new PathShape( path, totalWidth(), totalHeight() ) );
        getPaint().setColor( Color.WHITE );
        setBounds(left, top, left + totalWidth(), top + totalHeight());
    }

    private void addVerticalLines( Path path ) {
        for( int i = 1; i < numberOfColumns; ++i )
            path.addRect( i*cellWidth, top,
                          i*cellWidth+lineWidth, top+totalHeight(),
                          Path.Direction.CW );
    }

    private void addHorizontalLines( Path path ) {
        for( int i = 1; i < numberOfRows; ++i )
            path.addRect( left, i*cellHeight,
                          left+totalWidth(), i*cellHeight+lineWidth,
                          Path.Direction.CW );
    }

    final int totalWidth() {
       return cellWidth * numberOfColumns;
    }

    final int totalHeight() {
       return cellHeight * numberOfRows;
    }
}

