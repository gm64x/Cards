App (br.gm.cards.App)

Purpose

- Application entry point implementing `javafx.application.Application`.

Observable behavior

- Field: `public static Deck deck` — single application-scoped `Deck` instance accessible to controllers.
- Window: `start(Stage)` constructs a `Scene` with `App.loadFXML("fristOpen")` and sets the stage title to `Cards`, fixes size to 600×600, and disables resizing.
- Resource loading: `loadFXML(String)` resolves an FXML resource using `App.class.getResource(fxml + ".fxml")` and returns the loaded `Parent`.
- Scene replacement: `setRoot(String)` replaces the `Scene` root by loading the named FXML resource via `loadFXML`.

Notes

- `loadFXML` appends the `.fxml` extension; some controllers elsewhere call `changeScene` with a resource name that already includes `.fxml` (see controller module), exposing a small inconsistency in resource name conventions.
