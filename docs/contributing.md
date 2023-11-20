Contributing — Build and Run (observable instructions)

Build and run (Maven)

- The project uses Maven and includes `javafx-maven-plugin` in `pom.xml` configured with `mainClass` = `br.gm.cards.App`.
- Observed usage comment in `pom.xml`: `mvn clean javafx:run` is the configured invocation for running the application via the Maven plugin.

Environment

- Compiler level declared in `pom.xml` is Java 11 (`maven.compiler.source`/`target` set to 11). The OpenJFX dependencies in `pom.xml` reference JavaFX 13.

Quick start

1. Ensure a JDK compatible with Java 11 is installed and available on PATH.
2. From the project root, run:

```bash
mvn clean javafx:run
```

Notes for contributors

- The application is single-process and stores all data in memory; there is no persistence implementation in source (`importDeckAction` handlers are unimplemented and present but empty).
- If altering package names or module declarations, update `module-info.java`, FXML `fx:controller` attributes, and `pom.xml` `mainClass` to remain consistent.
- The documentation in `docs/` is generated from the current source tree and uses package names as present in the repository (`br.gm.cards`).
