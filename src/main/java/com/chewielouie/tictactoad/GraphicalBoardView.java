package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.OpenGridShape;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GraphicalBoardView extends ImageView {
    private OpenGridShape grid = new OpenGridShape( 3, 3 );
    private ShapeDrawable lilyPad = new ShapeDrawable();

    public GraphicalBoardView( Context context, AttributeSet attrs ) {
        super( context, attrs );
        createDrawableForEmptyBoardPoint();
    }

    private void createDrawableForEmptyBoardPoint() {
        int width = 30;
        int height = 30;
        OvalShape lilyPadShape = new OvalShape();
        lilyPadShape.resize( width, height );
        lilyPad.setShape( lilyPadShape );
        lilyPad.getPaint().setColor( Color.GREEN );
    }

    protected void onDraw( Canvas c ) {
        grid.draw( c );
        drawBoardContent( c );
    }

    private void drawBoardContent( Canvas c ) {
        for( int column = 0; column < grid.numberOfColumns(); column++ ) {
            for( int row = 0; row < grid.numberOfRows(); row++ ) {
                int x = column * grid.cellWidth();
                int y = row * grid.cellHeight();
                lilyPad.setBounds( x, y, x + grid.cellWidth(), y + grid.cellHeight() );
                lilyPad.draw( c );
            }
        }

        //NormalBoardIterator iterator = board.iterator();
        //for( ; iterator.hasNext(); iterator.next() ) {
            ////Coord gridPixels = convertBoardCellCoordToGridPixels( iterator.coord() );
            ////int x = gridPixels.x();
            ////int y = gridPixels.y();
            //int x = iterator.coord().x() * grid.cellWidth();
            //int y = iterator.coord().y() * grid.cellHeight();
            //lilyPad.setBounds( x, y, x + grid.cellWidth(), y + grid.cellHeight() );
            //lilyPad.draw( c );
        //}
    }
}

