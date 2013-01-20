TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- React to user input to place alternate noughts and crosses until the board is full
  - Ignore off-grid touch events that are still in the GridBoardView, currently throws a ProgrammerException when the PlayingGameActivity tries to read the coordinate from the Board.

- Detect winning board and end the game
  - Declare who won (change 'Your turn' to 'Green won!' or 'Brown won!')
  - Stop any further pieces being placed
  - Ask if a new game should be started

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

