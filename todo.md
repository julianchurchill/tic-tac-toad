TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Change PlayingGameActivity::generateBoardText() to use iterator
  - Downside is it makes the unit testing trickier because of having to mock the iterator... how do we do this or is this a sign that using an iterator here is a bad idea?

- React to user input to place alternate noughts and crosses until the board is full
  - PlayingGameActivity needs to register for and receive board touch events from the graphical_board
  - GraphicalBoardView needs to send board touch events to listeners, converting screen coordinates to board grid coordinates (use dispatchTouchEvent() to fake an event into the GraphicalBoardView)
- Remove text view from PlayingGameActivity

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

