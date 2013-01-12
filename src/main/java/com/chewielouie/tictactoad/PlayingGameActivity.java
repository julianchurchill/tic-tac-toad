package com.chewielouie.tictactoad;

import android.app.Activity;
import android.os.Bundle;
import com.chewielouie.tictactoad.NormalBoard;
import com.chewielouie.tictactoad.PlayGameView;
import com.chewielouie.tictactoad.RendersView;
import java.lang.IllegalArgumentException;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayingGameActivity extends Activity implements PlayGameView
{
    private final int boardSize = 3;
    private final int lastBoardCoordinate = boardSize-1;
    private final char NoughtBoardPoint = 'O';
    private final char CrossBoardPoint = 'X';
    private final char EmptyBoardPoint = '.';
    private final char BoardPointSeperator = '|';
    private final char LineSeperator = '\n';
    private Board board = null;
    private final RendersView rendersView;

    public PlayingGameActivity() {
        this.rendersView = new PlayGamePresenter( new NormalBoard(), this );
    }

    public PlayingGameActivity( RendersView p ) {
        this.rendersView = p;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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

        board = b;
        TextView t = (TextView)findViewById( R.id.board );
        t.setText( generateBoardText() );

        final GraphicalBoardView v = (GraphicalBoardView)findViewById( R.id.graphical_board );
        v.updateFromBoard( b );
    }

    private String generateBoardText() {
        StringBuilder text = new StringBuilder();
        for( int y = 0; y < boardSize; ++y ) {
            for( int x = 0; x < boardSize; ++x ) {
                text.append( getTextRepresentationOfPoint( new Coord( x, y ) ) );
                if( x != lastBoardCoordinate )
                    text.append( BoardPointSeperator );
                if( x == lastBoardCoordinate && y != lastBoardCoordinate )
                    text.append( LineSeperator );
            }
        }
        return text.toString();
    }

    public char getTextRepresentationOfPoint( Coord coord ) {
        if( board.getContentAt( coord ) == Board.Piece.Nought )
            return NoughtBoardPoint;
        else if( board.getContentAt( coord ) == Board.Piece.Cross )
            return CrossBoardPoint;
        return EmptyBoardPoint;
    }
}
