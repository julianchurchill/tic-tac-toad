package com.chewielouie.tictactoad;

import android.graphics.Color;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.Rect;
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

    public OpenGridShape( int columns, int rows ) {
        super();
        numberOfColumns = columns;
        numberOfRows = rows;
        Path path = new Path();
        addVerticalLines( path );
        addHorizontalLines( path );
        setShape( new PathShape( path, width(), height() ) );
        getPaint().setColor( Color.WHITE );
        setBounds(left, top, left + width(), top + height());
    }

    private void addVerticalLines( Path path ) {
        for( int i = 1; i < numberOfColumns; ++i )
            path.addRect( i*cellWidth, top,
                          i*cellWidth+lineWidth, top+height(),
                          Path.Direction.CW );
    }

    private void addHorizontalLines( Path path ) {
        for( int i = 1; i < numberOfRows; ++i )
            path.addRect( left, i*cellHeight,
                          left+width(), i*cellHeight+lineWidth,
                          Path.Direction.CW );
    }

    public final int width() {
       return cellWidth * numberOfColumns;
    }

    public final int height() {
       return cellHeight * numberOfRows;
    }

    public final int cellWidth() {
        return cellWidth;
    }

    public final int cellHeight() {
        return cellHeight;
    }

    public final int numberOfColumns() {
        return numberOfColumns;
    }

    public final int numberOfRows() {
        return numberOfRows;
    }

    public Rect cellBounds( int column, int row ) {
        return new Rect(
                column * cellWidth(),
                row * cellHeight(),
                column * cellWidth() + cellWidth(),
                row * cellHeight() + cellHeight() );
    }
}

