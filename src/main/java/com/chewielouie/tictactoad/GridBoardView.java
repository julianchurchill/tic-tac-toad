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
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class GridBoardView extends ImageView implements BoardTouchGenerator, GraphicalBoardView {
    private OpenGridShape grid = new OpenGridShape( 3, 3 );
    private ShapeDrawable lilyPad = new ShapeDrawable();
    private BitmapDrawable greenFrog = null;
    private Board board = new NullBoard();
    private Context context;
    private List<BoardTouchListener> listeners =
        new ArrayList<BoardTouchListener>();

    public GridBoardView( Context context, AttributeSet attrs ) {
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
            new BoardCellPainter( c, iterator.coord(), iterator.piece() );
    }

    class BoardCellPainter {
        Canvas canvas;
        Rect bounds;

        public BoardCellPainter( Canvas c, Coord boardCoord,
                Board.Piece piece ) {
            canvas = c;
            bounds = grid.cellBounds( boardCoord );
            drawLilyPad();
            if( piece == Board.Piece.Nought )
                drawBitmapInBounds( greenFrog() );
            else if( piece == Board.Piece.Cross )
                drawBitmapInBounds( brownFrog() );
        }

        private void drawLilyPad() {
            lilyPad.setBounds( bounds );
            lilyPad.draw( canvas );
        }

        private void drawBitmapInBounds( BitmapDrawable b ) {
            canvas.drawBitmap( b.getBitmap(),
                    bounds.left, bounds.top, null );
        }

        private BitmapDrawable greenFrog() {
            return (BitmapDrawable)context.getResources().
                        getDrawable( R.drawable.green_frog );
        }

        private BitmapDrawable brownFrog() {
            return (BitmapDrawable)context.getResources().
                        getDrawable( R.drawable.brown_frog );
        }
    }

    public OpenGridShape grid() {
        return grid;
    }

    public void addListener( BoardTouchListener l ) {
        listeners.add( l );
    }

    @Override
    public boolean dispatchTouchEvent( MotionEvent e ) {
        float x_limit = grid().numberOfColumns() * grid().cellWidth();
        float y_limit = grid().numberOfRows() * grid().cellHeight();
        if( e.getX() < x_limit && e.getY() < y_limit )
            for( BoardTouchListener l : listeners )
                l.boardTouchEvent( convertMotionEventToBoardCoord( e ) );
        return super.dispatchTouchEvent( e );
    }

    private Coord convertMotionEventToBoardCoord( MotionEvent e ) {
        return new Coord( (int)(e.getX() / grid().cellWidth()),
                          (int)(e.getY() / grid().cellHeight()) );
    }
}

