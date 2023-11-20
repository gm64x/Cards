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
Context: `Deck` keeps `cartas` (primary list — cards) and `cartasShuffle` (shuffled clone — shuffled view) and a `shuffled` boolean to select which list is read.
Rationale: Separation preserves the original ordering while offering a shuffled traversal without permanently destroying the original order.
Trade-offs: Memory duplication for `cartasShuffle` and the risk of inconsistent behavior between lists; an observable example was `getResposta(int)` returning the question when `shuffled` was true (documented bug), demonstrating subtle correctness risks of parallel lists.

Decision → FXML + controller pattern with explicit resource loading
Context: FXML files reference controllers with fully-qualified package names and controllers use `FXMLLoader` to load FXML resources at runtime.
Rationale: Standard JavaFX pattern separates view (FXML) and controller (Java class), enabling UI changes without recompiling code.
Trade-offs: Controller instantiation is reflective and requires the runtime to have access to controller classes; resource path conventions are sensitive to package and file-name mismatches (the repository contains differing conventions in `App.loadFXML` vs `FristOpenController.changeScene`).

Decision → Minimal persistence (none implemented)
Context: There is no file I/O or database code; the `importDeckAction` handlers are present but not implemented.
Rationale: Not present in source; effect is that all data is in-memory for the application run.
Trade-offs: Convenience and portability are improved for a small prototype, but data is lost between runs and import/export functionality is missing.

---

Note on Portuguese identifiers

The codebase uses Portuguese identifiers in source and docs (for example: `cartas`, `pergunta`, `resposta`, `dificuldade`). These identifiers were left as-is to preserve the original author's language and intent.

If contributors prefer to standardize identifiers to English, a best-effort renaming script is provided in `docs/scripts/rename_portuguese.ps1` (Windows/PowerShell) and `docs/scripts/rename_portuguese.sh` (POSIX shell). The scripts perform textual replacements across the repository and are _dry-run by default_.

Usage examples:

- PowerShell (dry-run):

  `.\docs/scripts/rename_portuguese.ps1`

- PowerShell (apply changes, creates `.bak` backups):

  `.\docs/scripts/rename_portuguese.ps1 -Apply`

- POSIX (dry-run):

  `bash docs/scripts/rename_portuguese.sh`

- POSIX (apply changes):

  `bash docs/scripts/rename_portuguese.sh --apply`

Notes and cautions:

- The script performs plain-text replacements and may introduce build or runtime issues if identifier renames are not reviewed. Create a git branch and verify changes before merging.
- Renaming class or file names is potentially disruptive; the PowerShell script supports an optional `-RenameFiles` flag but use it only after reviewing the dry-run output.
- These changes are optional and provided for contributors who prefer English identifiers; they were not applied to the repository by default to respect the project's original language.
