TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Change PlayingGameActivity::generateBoardText() to use iterator
  - Downside is it makes the unit testing trickier because of having to mock the iterator... how do we do this or is this a sign that using an iterator here is a bad idea?
- Add graphics to board display
  - Add lily pad graphics assuming an empty board
    - GraphicalBoardView must invalidate itself when passed a Board
  - Add frog and lily pad graphics based on actual board contents
  - GraphicalBoardView needs Board size to set up OpenGridShape correctly
    - If passed a Board of a different size create a new OpenGridShape

- React to user input to place alternate noughts and crosses until the board is full
  - When model updated it must notify listeners (i.e the presenter which will render itself)

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

