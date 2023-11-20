# Cards

**Archived — this repository is archived.** This was a college project and is no longer actively maintained. The code is provided "as‑is" for reference or fork; no support will be provided. See `LICENSE` (GPLv3) for terms.

Short description

- Simple flashcard app written in Java (JavaFX). Focused on a single deck of cards (question/answer).

Implemented features

- Add cards (`question` / `answer`)
- Remove cards (by index or by question+answer)
- Clear deck
- Shuffle (creates a separate shuffled list)
- Study mode: show question → show answer, Next / Back navigation
- Difficulty selection dialog (Easy enabled; Hard present but disabled in UI)
- Basic UI controllers: `FristOpenController`, `HomeController`, `PlayController`, `FinishController`
- Models: `Card`, `Deck`

How to run (brief)

1. Open the project in a Java IDE with JavaFX support.
2. Run `br.gm.cards.App` (main).
3. Ensure JavaFX is on the classpath according to your environment.

Limitations / notes

- No persistence: all data is in memory only.
- Import/export is not implemented (`importDeckAction` is empty).
- There is a placeholder method in `FristOpenController` that throws `UnsupportedOperationException`.
- Known bug: `Deck.getResposta(int)` returns the question when the deck is shuffled (`cartasShuffle.get(index).getPergunta()`).

License

- See `LICENSE` (GPLv3).

## Documentation

Complete technical documentation is available in the [`docs/`](./docs/) directory.

If you are interested in forking or contributing to this project, the documentation covers
the system architecture, module structure, design decisions, and API reference in detail.
Start with [`docs/index.md`](./docs/index.md).
