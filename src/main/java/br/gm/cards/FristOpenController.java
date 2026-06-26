package br.gm.cards;

import java.io.IOException;

import static br.gm.cards.App.deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FristOpenController {

    private boolean isAdded = false;
    private Stage stage;
    private Scene scene;
    private Parent root;

    int inicialDeckSize = (int) deck.size();
    int finalDeckSize;
    Dialog dialog = new Dialog();

    @FXML
    private Button addFristCardButton;

    @FXML
    private Button importDeckButton;

    @FXML
    void addFristCardAction(ActionEvent event) throws IOException {

        addCardToDeck(event);

        if (isAdded) {

            changeScene(event, "home.fxml");
            isAdded = false;
        }

    }

    public void removeCardFromDeck(ActionEvent event) throws IOException {
        dialog.setTitle("Remove Card");
        dialog.setHeaderText("Remove a card from your deck");

        if (!dialog.getDialogPane().getButtonTypes().contains(ButtonType.CANCEL)) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        }
        dialog.getDialogPane().setContent(createRemoveCardDialog());
        dialog.showAndWait();
    }

    public void addCardToDeck(ActionEvent event) throws IOException {
        dialog.setTitle("Add Card");
        dialog.setHeaderText("Add a card to your deck");

        if (!dialog.getDialogPane().getButtonTypes().contains(ButtonType.CANCEL)) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        }
        dialog.getDialogPane().setContent(createAddCardDialog());
        dialog.showAndWait();
    }


    private Node createAddCardDialog() {
        GridPane gridPane = new GridPane();

        TextField questionTextField = new TextField();
        TextField answerTextField = new TextField();
        Button addButton = new Button("Add");

        addButton.setOnAction((ActionEvent event) -> {
            deck.add(questionTextField.getText(), answerTextField.getText());
            System.out.println(deck.size());
            isAdded = true;

            dialog.close();

        });

        gridPane.add(new Label("Question: "), 0, 0);
        gridPane.add(new Label("Answer: "), 0, 1);
        gridPane.add(questionTextField, 1, 0);
        gridPane.add(answerTextField, 1, 1);
        gridPane.add(addButton, 1, 2);
        return gridPane;
    }

    private Node createRemoveCardDialog() {
        GridPane gridPane = new GridPane();

        TextField questionTextField = new TextField();
        TextField answerTextField = new TextField();
        Button addButton = new Button("Remove");

        addButton.setOnAction((ActionEvent event) -> {
            deck.remove(questionTextField.getText(), answerTextField.getText());
            System.out.println(deck.size());
            isAdded = true;
            dialog.close();

        });

        gridPane.add(new Label("Question: "), 0, 0);
        gridPane.add(new Label("Answer: "), 0, 1);
        gridPane.add(questionTextField, 1, 0);
        gridPane.add(answerTextField, 1, 1);
        gridPane.add(addButton, 1, 2);
        return gridPane;
    }

    public static void changeScene(ActionEvent event, String fxml) throws IOException {
        try {
            String base = fxml.endsWith(".fxml") ? fxml.substring(0, fxml.length() - 5) : fxml;
            Parent root = App.loadFXML(base);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    void importDeckAction(ActionEvent event) {
        // to be implemented

    }

    

}
