package br.gm.cards;

import java.io.IOException;

import static br.gm.cards.App.deck;
import static br.gm.cards.FristOpenController.changeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HomeController {

    private boolean isRemoved = false;

    private boolean isAdded = false;
    Dialog dialog = new Dialog();

    @FXML
    private Button addButton;

    @FXML
    private Button importDeckButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button startButton;

    @FXML
    private Text cardsNum;

    @FXML
    public void initialize() {
        cardsNum.setText("You have " + String.valueOf(deck.size()) + " cards.");
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        addCardToDeck(event);
        if (isAdded) {
            cardsNum.setText("You have " + String.valueOf(deck.size()) + " cards.");
        }
    }

    @FXML
    void importDeckAction(ActionEvent event) {
        // to be implemented
    }

    @FXML
    void remove(ActionEvent event) throws IOException {
        removeCardFromDeck(event);
        if (isRemoved) {
            cardsNum.setText("You have " + String.valueOf(deck.size()) + " cards.");
        }
    }

    @FXML
    void start(ActionEvent event) throws IOException {

        if (deck.size() > 1) {
            selectDificulty(event);
            changeScene(event, "play.fxml");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You need at least 2 cards to start");
            alert.setContentText("Add more cards to your deck");
            alert.showAndWait();
        }

    }

    public void selectDificulty(ActionEvent event) throws IOException {
        dialog.setTitle("Select Dificulty");
        dialog.setHeaderText("Select the dificulty of your deck");

        if (!dialog.getDialogPane().getButtonTypes().contains(ButtonType.CANCEL)) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        }

        dialog.getDialogPane().setContent(createSelectDificultyDialog());
        dialog.showAndWait();
    }

    private Node createSelectDificultyDialog() {
        GridPane gridPane = new GridPane();

        ToggleGroup group = new ToggleGroup();

        RadioButton easy = new RadioButton("Easy");
        easy.setToggleGroup(group);
        easy.setSelected(true);

        RadioButton hard = new RadioButton("Hard");
        hard.setToggleGroup(group);
        hard.disableProperty().setValue(true);
        Button confirmButton = new Button("Confirm");

        confirmButton.setOnAction((ActionEvent event) -> {

            if (easy.isSelected()) {
                deck.setDificuldade(1);
            } else if (hard.isSelected()) {
                deck.setDificuldade(2);
            }
            System.out.println("Difficulty defined to: " + deck.getDificuldade());
            dialog.close();

        });

        gridPane.add(new Label("Easy difficulty: "), 0, 0);
        gridPane.add(new Label("Hard difficulty: "), 0, 1);
        gridPane.add(easy, 1, 0);
        gridPane.add(hard, 1, 1);
        gridPane.add(confirmButton, 1, 2);
        return gridPane;
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

    public void removeCardFromDeck(ActionEvent event) throws IOException {
        dialog.setTitle("Remove Card");
        dialog.setHeaderText("Remove a card from your deck");

        if (!dialog.getDialogPane().getButtonTypes().contains(ButtonType.CANCEL)) {
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        }
        dialog.getDialogPane().setContent(createRemoveCardDialog());
        dialog.showAndWait();
    }

    private Node createRemoveCardDialog() {
        GridPane gridPane = new GridPane();

        TextField questionTextField = new TextField();
        TextField answerTextField = new TextField();
        Button removeButton = new Button("Remove");

        Button removeAllButton = new Button("Clear All Deck");

        removeButton.setOnAction((ActionEvent event) -> {
            deck.remove(questionTextField.getText(), answerTextField.getText());
            deck.removeFromShuffled(questionTextField.getText(), answerTextField.getText());
            System.out.println(deck.size());
            isRemoved = true;

            dialog.close();

        });

        removeAllButton.setOnAction((ActionEvent event) -> {
            deck.clearShuffled();
            deck.removeAll();
            System.out.println(deck.size());
            isRemoved = true;

            dialog.close();

        });

        gridPane.add(new Label("Question: "), 0, 0);
        gridPane.add(new Label("Answer: "), 0, 1);
        gridPane.add(questionTextField, 1, 0);
        gridPane.add(answerTextField, 1, 1);
        gridPane.add(removeButton, 1, 2);
        gridPane.add(removeAllButton, 1, 3);
        return gridPane;
    }

}
