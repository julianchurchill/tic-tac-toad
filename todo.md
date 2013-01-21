TODO
====

- Follow MVC pattern, controller-first design. Minimise Android code, only refer from Android to Android-free.

Backlog
=======

- Detect completed board and end the game:
  - Declare who won:
    - View must show to user win or draw message, change 'Your turn' to 'Green won!' or 'Brown won!'
  - Stop any further pieces being placed:
    - When presenter detects win/draw lock the board
    - Board must ignore content setting when locked
  - Ask if a new game should be started:
    - View should show 'New game?' button when told to show win/draw message
    - View must call new game on presenter when user presses new game button
    - View must hide new game button when user presses it
    - Presenter must clear and unlock board on new game. Also tell view to render after clear.

- Human vs random computer
- Human vs medium computer
- Human vs perfect play computer

