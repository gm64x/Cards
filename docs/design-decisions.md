Design Decisions

Decision → Use of Java Platform Module System (JPMS)
Context: `module-info.java` declares module `br.gm.cards` and lists `requires` entries for JavaFX modules and `opens`/`exports` for `br.gm.cards`.
Rationale (observable/inferred): The module declaration enables explicit module boundaries and opens the package to `javafx.fxml`, which is required for reflective controller instantiation by the JavaFX runtime.
Trade-offs: Module boundaries require a module-aware build/runtime and may complicate running the application in environments not configured for JPMS.

Decision → Application-scoped, static `Deck` instance
Context: `App` exposes `public static Deck deck` that controllers access via static imports.
Rationale (inferred from structure): A global static `Deck` simplifies sharing state across controller classes without explicit dependency injection.
Trade-offs: Global mutable state increases coupling between controllers and complicates testing; lifetime management is implicit and tied to the `Application` lifecycle.

Decision → Maintain a separate shuffled list
Context: `Deck` keeps `cartas` (primary list) and `cartasShuffle` (shuffled clone) and a `shuffled` boolean to select which list is read.
Rationale: Separation preserves the original ordering while offering a shuffled traversal without permanently destroying the original order.
Trade-offs: Memory duplication for `cartasShuffle` and the risk of inconsistent behavior between lists; an observable example is `getResposta(int)` returning the question when `shuffled` is true (documented bug), demonstrating subtle correctness risks of parallel lists.

Decision → FXML + controller pattern with explicit resource loading
Context: FXML files reference controllers with fully-qualified package names and controllers use `FXMLLoader` to load FXML resources at runtime.
Rationale: Standard JavaFX pattern separates view (FXML) and controller (Java class), enabling UI changes without recompiling code.
Trade-offs: Controller instantiation is reflective and requires the runtime to have access to controller classes; resource path conventions are sensitive to package and file-name mismatches (the repository contains differing conventions in `App.loadFXML` vs `FristOpenController.changeScene`).

Decision → Minimal persistence (none implemented)
Context: There is no file I/O or database code; the `importDeckAction` handlers are present but not implemented.
Rationale: Not present in source; effect is that all data is in-memory for the application run.
Trade-offs: Convenience and portability are improved for a small prototype, but data is lost between runs and import/export functionality is missing.
