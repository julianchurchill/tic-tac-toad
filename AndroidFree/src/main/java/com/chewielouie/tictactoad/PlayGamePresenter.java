package com.chewielouie.tictactoad;

class PlayGamePresenter implements RendersView {
    private final Board board;
    private final PlayGameView view;

    public PlayGamePresenter( Board b, PlayGameView v ) {
        this.board = b;
        this.view = v;
    }

    public void render() {
        view.displayBoard( board );
    }
}
