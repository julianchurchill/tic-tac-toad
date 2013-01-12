package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.OpenGridShape;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GraphicalBoardView extends ImageView {
    private ShapeDrawable grid = new OpenGridShape( 3, 3 );

    public GraphicalBoardView( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    protected void onDraw( Canvas c ) {
        grid.draw( c );
    }
}

