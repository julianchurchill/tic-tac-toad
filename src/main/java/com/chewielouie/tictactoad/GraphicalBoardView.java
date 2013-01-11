package com.chewielouie.tictactoad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GraphicalBoardView extends ImageView {

    public GraphicalBoardView( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    protected void onDraw( Canvas c ) {
        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;
        Path path = new Path();
        path.addRect( x, y, x+width, y+height, Path.Direction.CW );
        ShapeDrawable sd = new ShapeDrawable(
                new PathShape( path, 100, 100 ) );
        sd.getPaint().setColor(0xff74AC23);
        sd.setBounds(x, y, x + width, y + height);
        sd.draw( c );

        //int x = 10;
        //int y = 10;
        //int width = 300;
        //int height = 50;
        //ShapeDrawable mDrawable = new ShapeDrawable(new OvalShape());
        //mDrawable.getPaint().setColor(0xff74AC23);
        //mDrawable.setBounds(x, y, x + width, y + height);
        //mDrawable.draw( c );
    }
}

