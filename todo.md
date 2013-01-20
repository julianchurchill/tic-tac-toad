TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Remove text view and tests from PlayingGameActivity

- React to user input to place alternate noughts and crosses until the board is full
  - PlayingGameActivity needs to register for and receive board touch events from the graphical_board
  - GridBoardView needs to send board touch events to listeners, converting screen coordinates to board grid coordinates (use dispatchTouchEvent() to fake an event into the GridBoardView)

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

