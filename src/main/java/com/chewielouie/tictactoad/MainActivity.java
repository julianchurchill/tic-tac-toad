package com.chewielouie.tictactoad;

import android.app.Activity;
import android.os.Bundle;
import com.chewielouie.tictactoad.Board;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private final String emptyBoardText = ".|.|.\n.|.|.\n.|.|.";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void displayBoard( Board b ) {
        TextView t = (TextView)findViewById( R.id.board );
        t.setText( emptyBoardText );
    }
}
