Cards — Project Overview

This repository contains a simple flashcard application implemented in Java using JavaFX. The implementation focuses on a single, in-memory deck of question/answer pairs and a minimal graphical user interface defined with FXML and controller classes.

Primary components

- Module: `br.gm.cards` (see `module-info.java`).
- Entry point: `br.gm.cards.App` (JavaFX `Application`). The initial scene is loaded from `fristOpen.fxml`.
- Model: `br.gm.cards.model.Card`, `br.gm.cards.model.Deck`.
- Controllers: `br.gm.cards.FristOpenController`, `br.gm.cards.HomeController`, `br.gm.cards.PlayController`, `br.gm.cards.FinishController`.
- Resources: FXML files are located under `src/main/resources/br/gm/cards/`.

Follow these documents for details:

- [Architecture](architecture.md)
- [API Reference](api-reference.md)
- [Design Decisions](design-decisions.md)
- Modules
  - [App](modules/app.md)
  - [Controllers](modules/controllers.md)
  - [Model](modules/model.md)
- [Contributing](contributing.md)

Exact claims in this documentation are sourced from the repository files under `src/main/` and `README.md`. Ambiguities observed in source are identified and labeled explicitly in the referenced documents.
