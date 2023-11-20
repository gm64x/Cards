module br.gm.cards {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens br.gm.cards to javafx.fxml;
    exports br.gm.cards;
}
