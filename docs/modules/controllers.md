Controllers

This document enumerates controllers present in `src/main/java/br/gm/cards/` and describes their observable public members and interactions with `App.deck`.

FristOpenController

- Purpose: initial setup view controller; presents dialogs to add or remove cards and transitions to the home view when appropriate.
- Key members (observable):
  - `@FXML Button addFristCardButton` — bound in `fristOpen.fxml`.
  - `@FXML void addFristCardAction(ActionEvent)` — opens add-card dialog and on success calls `changeScene(event, "home.fxml")`.
  - `public void addCardToDeck(ActionEvent)` / `public void removeCardFromDeck(ActionEvent)` — build and show modal dialogs that add/remove items in `App.deck`.
  - `public static void changeScene(ActionEvent, String)` — utility that loads an FXML resource with `FristOpenController.class.getResource(fxml)` and replaces the `Stage` `Scene`.
  - `private Card Card(String, String)` — placeholder method that throws `UnsupportedOperationException` in source.

HomeController

- Purpose: main application view; display deck size, allow adding/removing of cards, difficulty selection, and start a study session.
- Key members (observable):
  - `@FXML void initialize()` — sets `cardsNum` text to reflect `deck.size()`.
  - `@FXML void add(ActionEvent)` / `@FXML void remove(ActionEvent)` — show dialogs that mutate `App.deck` and update displayed count on success.
  - `@FXML void start(ActionEvent)` — requires `deck.size() > 1` to proceed; otherwise shows an `Alert` with an error message.
  - `public void selectDificulty(ActionEvent)` — displays a dialog with radio buttons; `Hard` option is disabled in UI code.
  - `@FXML void importDeckAction(ActionEvent)` — present in source but not implemented.

PlayController

- Purpose: study/play view; toggles between question and answer and offers navigation (Next/Back) through deck items.
- Key members (observable):
  - `@FXML public void initialize()` — sets initial texts and disables back button at start.
  - `@FXML void next(ActionEvent)` / `@FXML void back(ActionEvent)` — control `page`, `index` and `allReadyShowQuestion` state and call `deck.getPergunta` / `deck.getResposta` to update display.
  - `@FXML void exit(ActionEvent)` — returns to home view via `changeScene`.

FinishController

- Purpose: final view at the end of a study session; allows resetting or shuffling the deck and returning home.
- Key members (observable):
  - `@FXML void close(ActionEvent)` — calls `Platform.exit()`.
  - `@FXML void returnHome(ActionEvent)` — opens a reset confirmation dialog and calls `changeScene(event, "home.fxml")`.
  - Dialog behavior: `resetDialog` presents `Yes`/`No` radio buttons; `Confirm` either calls `deck.removeAll()` (delete) or `deck.shuffle()` (keep but reshuffle).
