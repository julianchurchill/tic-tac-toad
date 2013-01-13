package com.chewielouie.tictactoad;

import com.chewielouie.tictactoad.OpenGridShape;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GraphicalBoardView extends ImageView {
    private OpenGridShape grid = new OpenGridShape( 3, 3 );
    private ShapeDrawable lilyPad = new ShapeDrawable();
    private BitmapDrawable greenFrog = null;
    private Board board = new NullBoard();
    private Context context;

    public GraphicalBoardView( Context context, AttributeSet attrs ) {
        super( context, attrs );
        this.context = context;
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
        grid = new OpenGridShape( board.width(), board.height() );
        invalidate();
    }

    protected void onDraw( Canvas c ) {
        grid.draw( c );
        drawBoardContent( c );
    }

    private void drawBoardContent( Canvas c ) {
        BoardIterator iterator = board.iterator();
        for( ; iterator.hasNext(); iterator.next() )
            new BoardCellPainter( c,
                                  grid.cellBounds( iterator.coord() ) );
    }

    class BoardCellPainter {
        Canvas canvas;
        Rect bounds;

        public BoardCellPainter( Canvas c, Rect b ) {
            canvas = c;
            bounds = b;
            drawLilyPad();
            drawGreenFrog();
        }

        private void drawLilyPad() {
            lilyPad.setBounds( bounds );
            lilyPad.draw( canvas );
        }

        private void drawGreenFrog() {
            canvas.drawBitmap( greenFrog().getBitmap(),
                    bounds.left, bounds.top, null );
        }
    }

    private BitmapDrawable greenFrog() {
        return (BitmapDrawable)context.getResources().
                    getDrawable( R.drawable.green_frog );
    }

    public OpenGridShape grid() {
        return grid;
    }
}

