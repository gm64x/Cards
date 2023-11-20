API Reference

This file lists the public classes and their observable public members. All signatures and behaviors are taken directly from the source in `src/main/java/`.

br.gm.cards.App

- `public static Deck deck` — application-wide deck instance.
- `public void start(Stage stage) throws IOException` — JavaFX lifecycle method; constructs the initial `Scene` with the `fristOpen` FXML resource and shows the `Stage` (window). Window size is fixed to 600×600 and stage is set non-resizable in the implementation.
- `static void setRoot(String fxml) throws IOException` — replaces the current scene root with the `Parent` loaded from `loadFXML(fxml)`.
- `public static Parent loadFXML(String fxml) throws IOException` — loads an FXML resource using `App.class.getResource(fxml + ".fxml")` and returns the loaded `Parent`.
- `public static void main(String[] args)` — calls `launch()`.

br.gm.cards.model.Card

- `public Card()` — no-argument constructor.
- `public Card(String pergunta, String resposta)` — constructs a card with the provided question (`pergunta`) and answer (`resposta`).
- `public String getPergunta()` / `public void setPergunta(String)` — getter/setter for question.
- `public String getResposta()` / `public void setResposta(String)` — getter/setter for answer.
- `public String toString()` and `public String toString(int index)` — string representations; literal `Carta{...}` is used in output.

br.gm.cards.model.Deck

- Fields (observable): `ArrayList<Card> cartas`, `ArrayList<Card> cartasEasy`, `ArrayList<Card> cartasShuffle`; `boolean shuffled`; `int dificuldade`.
- `public boolean isShuffled()` — returns the `shuffled` flag.
- `public int getDificuldade()` / `public void setDificuldade(int dificuldade)` — setter modifies internal lists: when `dificuldade` is 0 or 1, `cartasEasy` is used to reset `cartas`; otherwise the implementation clones and shuffles to `cartasShuffle` and assigns `cartas` from `cartasShuffle`.
- `public void add(String pergunta, String resposta)` — constructs a new `Card` and appends to `cartas`.
- `public void add(Card carta)` — appends an existing `Card` object to `cartas`.
- `public void remove(Card carta)` — removes the supplied `Card` from `cartas`.
- `public void remove(String questionTextField, String answerTextField)` — iterates `cartas` and removes the first `Card` whose `pergunta` equals `questionTextField` and `resposta` equals `answerTextField`.
- `public void removeFromShuffled(String questionTextField, String answerTextField)` — same logic as `remove`, operated on `cartasShuffle`.
- `public void remove(int index)` — removes the element at `index` from `cartas`.
- `public void removeAll()` — calls `cartas.removeAll(cartas)` in source; observable effect is that `cartas` becomes empty (see source for exact call).
- `public int getIndex(Card carta)` — returns `cartas.indexOf(carta)`.
- `public int size()` — returns `cartas.size()`.
- `public void shuffle()` — clones `cartas` into `cartasShuffle` and calls `Collections.shuffle(cartasShuffle)`, then sets `shuffled = true`.
- `public void clearShuffled()` — clears `cartasShuffle` and sets `shuffled = false`.
- `public String getPergunta(int index)` — if `shuffled` is true returns `cartasShuffle.get(index).getPergunta()`, otherwise `cartas.get(index).getPergunta()`.
- `public String getResposta(int index)` — implementation note: when `shuffled` is true the method returns `cartasShuffle.get(index).getPergunta()` (the question) rather than the answer; this is observable in source and documented as a known bug in `README.md`.
- `@Override public String toString()` — returns a combined representation of `cartas` and `cartasShuffle` including `dificuldade`.

Controllers (summary of public handlers)

br.gm.cards.FristOpenController

- `@FXML void addFristCardAction(ActionEvent event) throws IOException` — opens modal dialog to add a first card and, on success, calls `changeScene(event, "home.fxml")`.
- `@FXML void importDeckAction(ActionEvent event)` — present but empty in source (no implementation).
- `public void addCardToDeck(ActionEvent event)` / `public void removeCardFromDeck(ActionEvent event)` — build and display modal dialogs to add/remove cards.
- `public static void changeScene(ActionEvent event, String fxml) throws IOException` — loads the given resource with `FXMLLoader.load(FristOpenController.class.getResource(fxml))` and sets it as the current `Stage` `Scene`.
- `private Card Card(String text, String text0)` — in source this method throws `UnsupportedOperationException` and is therefore a placeholder.

br.gm.cards.HomeController

- `@FXML public void initialize()` — initializes UI text using `deck.size()`.
- `@FXML void add(ActionEvent event)`, `@FXML void remove(ActionEvent event)`, `@FXML void start(ActionEvent event)` — handlers for add/remove/start actions; `start` requires `deck.size() > 1`, otherwise shows an error `Alert`.
- `@FXML void importDeckAction(ActionEvent event)` — present but empty in source.
- `public void selectDificulty(ActionEvent event)` — shows a dialog to set difficulty using radio buttons; the `Hard` option is disabled in the UI.

br.gm.cards.PlayController

- `@FXML public void initialize()` — sets initial UI state and displays `deck.getPergunta(page)`.
- `@FXML void back(ActionEvent event)` / `@FXML void next(ActionEvent event)` — navigate between question and answer using internal `page`, `index`, and `allReadyShowQuestion` state.
- `@FXML void exit(ActionEvent event)` — calls `changeScene(event, "home.fxml")`.

br.gm.cards.FinishController

- `@FXML void close(ActionEvent event)` — calls `Platform.exit()`.
- `@FXML void returnHome(ActionEvent event) throws IOException` — opens reset dialog and calls `changeScene(event, "home.fxml")`.
- `public void resetDialog(ActionEvent event)` — builds and shows a dialog offering to delete or shuffle the deck; `Confirm` action invokes `deck.removeAll()` if yes selected, otherwise `deck.shuffle()`.
