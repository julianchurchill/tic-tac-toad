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
    private Board board = new NullBoard();

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

    public void updateFromBoard( Board b ) {
        board = b;
        invalidate();
    }

    protected void onDraw( Canvas c ) {
        grid.draw( c );
        drawBoardContent( c );
    }

    private void drawBoardContent( Canvas c ) {
        for( int column = 0; column < grid.numberOfColumns(); column++ ) {
            for( int row = 0; row < grid.numberOfRows(); row++ ) {
                lilyPad.setBounds( grid.cellBounds( column, row ) );
                lilyPad.draw( c );
            }
        }

        //NormalBoardIterator iterator = board.iterator();
        //for( ; iterator.hasNext(); iterator.next() ) {
            //lilyPad.setBounds( grid.cellBounds( iterator.coord().x(),
                                                //iterator.coord().y() ) );
            //lilyPad.draw( c );
        //}
    }
}

