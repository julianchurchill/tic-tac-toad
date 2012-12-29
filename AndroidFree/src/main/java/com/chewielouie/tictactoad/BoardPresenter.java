package com.chewielouie.tictactoad;

class BoardPresenter {
    private final BoardModel model;
    private final BoardView view;

    public BoardPresenter( BoardModel m, BoardView v ) {
        this.model = m;
        this.view = v;
    }

    public void render() {
        view.displayBoard( model.retrieveBoard() );
    }
}
