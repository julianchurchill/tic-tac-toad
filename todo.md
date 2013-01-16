TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Change PlayingGameActivity::generateBoardText() to use iterator
  - Downside is it makes the unit testing trickier because of having to mock the iterator... how do we do this or is this a sign that using an iterator here is a bad idea?

- React to user input to place alternate noughts and crosses until the board is full

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

