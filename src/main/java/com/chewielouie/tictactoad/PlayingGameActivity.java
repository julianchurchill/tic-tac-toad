package com.chewielouie.tictactoad;

import android.app.Activity;
import android.os.Bundle;
import com.chewielouie.tictactoad.NormalBoard;
import com.chewielouie.tictactoad.PlayGameView;
import com.chewielouie.tictactoad.RendersView;
import java.lang.IllegalArgumentException;
import android.widget.ImageView;

public class PlayingGameActivity extends Activity implements PlayGameView, BoardTouchListener
{
    private Board board = null;
    private RendersView rendersView = null;
    private Board.Piece nextPieceToPlay = Board.Piece.Nought;
    private BoardTouchGenerator boardTouchGenerator = null;
    private GraphicalBoardView graphicalBoardView = null;

    public PlayingGameActivity() {
        board = new NormalBoard();
        board.setContentAt( new Coord( 1, 0 ), Board.Piece.Nought );
        board.setContentAt( new Coord( 2, 0 ), Board.Piece.Cross );
        this.rendersView = new PlayGamePresenter( board, this );
    }

    public PlayingGameActivity( RendersView p, Board b ) {
        this.rendersView = p;
        this.board = b;
    }

    public PlayingGameActivity( BoardTouchGenerator t ) {
        super();
        this.boardTouchGenerator = t;
    }

    public PlayingGameActivity( GraphicalBoardView g ) {
        super();
        this.graphicalBoardView = g;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if( boardTouchGenerator == null )
            boardTouchGenerator =
                (BoardTouchGenerator)findViewById( R.id.graphical_board );
        boardTouchGenerator.addListener( this );
        if( graphicalBoardView == null )
            graphicalBoardView =
                (GraphicalBoardView)findViewById( R.id.graphical_board );
    }

    @Override
    public void onResume() {
        super.onResume();
        rendersView.render();
    }

    public void displayBoard( Board b ) {
        if( b == null )
            throw new ProgrammerMistake(
                new IllegalArgumentException( "A null board is not displayable" ) );

        graphicalBoardView.updateFromBoard( b );
    }

    public void boardTouchEvent( Coord c ) {
        if( board.getContentAt( c ) == Board.Piece.None ) {
            board.setContentAt( c, nextPieceToPlay );
            alternateNextToPlay();
        }
    }

    private void alternateNextToPlay() {
        if( nextPieceToPlay == Board.Piece.Nought )
            nextPieceToPlay = Board.Piece.Cross;
        else
            nextPieceToPlay = Board.Piece.Nought;
    }

    public void gameWonBy( Board.Piece p ) {
    }

    public void gameDrawn() {
    }
}

