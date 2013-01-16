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
        render();
    }
}
