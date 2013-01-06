package com.chewielouie.tictactoad;

class PlayGamePresenter implements RendersView {
    private final PlayGameModel model;
    private final PlayGameView view;

    public PlayGamePresenter( PlayGameModel m, PlayGameView v ) {
        this.model = m;
        this.view = v;
    }

    public void render() {
        view.displayBoard( model.retrieveBoard() );
    }
}
