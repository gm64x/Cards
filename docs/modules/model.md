Model

This module describes `Card` and `Deck` types implemented under `src/main/java/br/gm/cards/model/`.

Card

- Fields: `private String pergunta`, `private String resposta`.
- Constructors: `Card()` and `Card(String pergunta, String resposta)`.
- Accessors: `getPergunta()`, `setPergunta(String)`, `getResposta()`, `setResposta(String)`.
- Stringification: `toString()` and `toString(int index)` return a literal `Carta{...}` representation.

Deck

- Internal state (observable):
  - `ArrayList<Card> cartas` — primary list of cards.
  - `ArrayList<Card> cartasEasy` — auxiliary list used during difficulty changes.
  - `ArrayList<Card> cartasShuffle` — cloned list used when `shuffle()` is executed.
  - `boolean shuffled` — indicates whether the shuffled list should be used for reads.
  - `int dificuldade` — numeric difficulty flag.

- Public methods (signatures and behavior):
  - `isShuffled()` — returns the `shuffled` flag.
  - `getDificuldade()` / `setDificuldade(int)` — `setDificuldade` assigns or rebuilds lists depending on the supplied value; for `dificuldade == 0 || dificuldade == 1` it resets `cartas` from `cartasEasy`, otherwise it calls `shuffle()` and assigns `cartas` from `cartasShuffle`.
  - `add(String pergunta, String resposta)` / `add(Card)` — append new cards to `cartas`.
  - `remove(Card)` / `remove(String, String)` / `removeFromShuffled(String, String)` / `remove(int)` — remove by object, by matching fields, or by index.
  - `removeAll()` — implementation calls `cartas.removeAll(cartas)` (observable effect: clears `cartas`).
  - `getIndex(Card)` — returns `cartas.indexOf(carta)`.
  - `size()` — returns `cartas.size()`.
  - `shuffle()` — clones `cartas` to `cartasShuffle`, calls `Collections.shuffle(cartasShuffle)`, and sets `shuffled = true`.
  - `clearShuffled()` — clears `cartasShuffle` and sets `shuffled = false`.
  - `getPergunta(int)` / `getResposta(int)` — when `shuffled` is true they read from `cartasShuffle`, otherwise from `cartas`.

Known issues (observable)

- `getResposta(int)` returns the question (`getPergunta`) when `shuffled` is true; this behavior is visible in source and is documented in the repository `README.md`.
