TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

... - Show the user an empty board, stating 'Your turn'.
- Human vs human game - user should be able to make alternating moves for either side until the board is full

- Tests
  - Implement BoardView interface in Android Activity
      - Call Presenter.render() onResume
      - Create a presenter and a model and hook up to activity as the view
  - Implement Board
  - Implement basic BoardModel

