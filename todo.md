TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Decide on a thin slice:
  - Give the user some output before asking for input.
  - ? Show the user an empty board, stating 'Your turn'.
- Human vs human game - user should be able to make alternating moves for either side until the board is full

- Tests
  - How to handle BoardView::displayBoard( null )? Stop Model from returning a null for BoardModel::retrieveBoard() or catch it in BoardView?
  - Implement BoardView interface in Android activity
  - Implement basic BoardModel
  - Implement Board

