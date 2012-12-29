TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

... - Show the user an empty board, stating 'Your turn'.
- Human vs human game - user should be able to make alternating moves for either side until the board is full

- Tests
  - Implement BoardView interface in Android Activity
      - How to handle BoardView::displayBoard( null )? Throw a runtime exception, i.e. ProgrammerMistake (see tddandroid.com)
      - Extract Contract Tests for BoardView from tests for Activity
  - Implement basic BoardModel
  - Implement Board

