Architecture

Module declaration

- The Java module is declared in `module-info.java` as `br.gm.cards`.
- The module declaration lists the required modules: `javafx.controls`, `javafx.fxml`, `javafx.base`, `javafx.graphics`, and `java.base`.

External dependencies

- Maven `pom.xml` declares dependencies on `org.openjfx:javafx-controls:13` and `org.openjfx:javafx-fxml:13` and configures `javafx-maven-plugin` with `mainClass` set to `br.gm.cards.App`.

Runtime flow (observable)

- Application startup: `br.gm.cards.App#main` invokes JavaFX `launch`; `App#start(Stage)` constructs a `Scene` by calling `App.loadFXML("fristOpen")` and sets a fixed window size (600×600) and non-resizable stage.
- FXML loading: `App.loadFXML(String)` calls `FXMLLoader` with `App.class.getResource(fxml + ".fxml")` and returns the loaded `Parent`.
- Scene transitions: controllers use `FristOpenController.changeScene(ActionEvent, String)` which calls `FXMLLoader.load(FristOpenController.class.getResource(fxml))` and replaces the `Stage` `Scene` with a new `Scene(root)`.

Data flow

- A single, global `Deck` instance is exposed as `public static Deck deck` on `App` and is accessed by controllers via static imports (observed in controller source files). Controllers mutate and read the `Deck` to add, remove, shuffle, and retrieve card content.
- `Deck` stores card data in three lists: `cartas` (primary), `cartasShuffle` (shuffled view), and `cartasEasy` (auxiliary copy used during difficulty changes). The `shuffled` boolean controls whether getters use `cartas` or `cartasShuffle`.

Resource / controller mapping

- FXML files under `src/main/resources/br/gm/cards/` reference controllers by `fx:controller="br.gm.cards.<ControllerName>"`.
- Note: FXML controller references and resource-loading code use slightly different conventions: `App.loadFXML` appends `.fxml` to the provided name (expects a base name), while `FristOpenController.changeScene` expects a resource name that may include the `.fxml` extension (source code calls `changeScene(event, "home.fxml")`). This inconsistency is observable in source and may affect callers.
