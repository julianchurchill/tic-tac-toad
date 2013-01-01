package com.chewielouie.tictactoad;

public class ProgrammerMistake extends RuntimeException {
    private RuntimeException cause = null;

    public ProgrammerMistake( RuntimeException cause ) {
        this.cause = cause;
    }

    public RuntimeException getCause() {
        return cause;
    }
}

