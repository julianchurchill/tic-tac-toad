package com.chewielouie.tictactoad;

class PlayGamePresenter implements RendersView, BoardListener {
    private final Board board;
    private final PlayGameView view;

    public PlayGamePresenter( Board b, PlayGameView v ) {
        this.board = b;
        this.view = v;
        this.board.addListener( this );
    }

    public void render() {
        view.displayBoard( board );
    }

    public void boardChanged( Board b ) {
        Board.Piece winner = b.whoHasWon();
        if( winner != Board.Piece.None ) {
            view.gameWonBy( winner );
            b.lock();
        }
        else if( drawnGame( b ) )
            view.gameDrawn();
        render();
    }

    private boolean drawnGame( Board b ) {
        BoardIterator iter = b.iterator();
        for( ; iter.hasNext() ; iter.next() )
            if( iter.piece() == Board.Piece.None )
                return false;
        return true;
    }

    public void newGame() {
        board.unlock();
        board.clear();
    }
}
