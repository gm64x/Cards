package br.gm.cards;

import java.io.IOException;

import static br.gm.cards.App.deck;
import static br.gm.cards.FristOpenController.changeScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class FinishController {

    Dialog dialog = new Dialog();

    @FXML
    private Button closeButton;

    @FXML
    private Button returnHomeButton;

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void returnHome(ActionEvent event) throws IOException {
        resetDialog(event);
        changeScene(event, "home.fxml");
    }

    public void resetDialog(ActionEvent event) throws IOException {
        dialog.setTitle("Reset Cards deck?");
        dialog.setHeaderText("Select with you want to reset cards deck");

        if (!dialog.getDialogPane().getButtonTypes().contains(ButtonType.CANCEL)) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        }

        dialog.getDialogPane().setContent(createResetDialog());
        dialog.showAndWait();
    }

    private Node createResetDialog() {
        GridPane gridPane = new GridPane();

        ToggleGroup group = new ToggleGroup();

        RadioButton yes = new RadioButton("Yes");
        yes.setToggleGroup(group);

        RadioButton no = new RadioButton("No");
        no.setToggleGroup(group);
        no.setSelected(true);

        Button confirmButton = new Button("Confirm");

        confirmButton.setOnAction((ActionEvent event) -> {

            if (yes.isSelected()) {
                deck.clearShuffled();
                deck.removeAll();
            } else if (no.isSelected()) {
                deck.shuffle();
            }
            dialog.close();

        });

        gridPane.add(new Label("I would like to delete my current deck: "), 0, 0);
        gridPane.add(yes, 1, 0);
        gridPane.add(no, 1, 1);
        gridPane.add(confirmButton, 1, 2);
        return gridPane;
    }

}
