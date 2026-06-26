package br.gm.cards;

import java.io.IOException;

import static br.gm.cards.App.deck;
import static br.gm.cards.FristOpenController.changeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PlayController {

    private boolean showingQuestion = true;
    private int currentCardIndex = 0;

    @FXML
    private Button backButton;

    @FXML
    private Text changeText;

    @FXML
    private Button exitButton;

    @FXML
    private Button nextButton;

    @FXML
    private Text numberText;

    @FXML
    private Text tipText;

    @FXML
    void back(ActionEvent event) {
        if (showingQuestion) {
            if (currentCardIndex == 0) {
                backButton.setDisable(true);
                return;
            }

            currentCardIndex--;
            showingQuestion = false;
        } else {
            showingQuestion = true;
        }

        updateCardView();
    }

    @FXML
    public void initialize() {
        tipText.setText("Number of Cards");
        currentCardIndex = 0;
        showingQuestion = true;

        updateCardView();
        changeText.setOpacity(1);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        changeScene(event, "home.fxml");
    }

    @FXML
    void next(ActionEvent event) throws IOException {
        if (showingQuestion) {
            showingQuestion = false;
            updateCardView();
            return;
        }

        currentCardIndex++;
        if (currentCardIndex >= deck.size()) {
            changeScene(event, "finish.fxml");
            return;
        }

        showingQuestion = true;
        updateCardView();
    }

    private void updateCardView() {
        backButton.setDisable(currentCardIndex == 0 && showingQuestion);
        numberText.setText((currentCardIndex + 1) + " / " + deck.size());

        if (showingQuestion) {
            changeText.setText(deck.getPergunta(currentCardIndex));
        } else {
            changeText.setText(deck.getResposta(currentCardIndex));
        }
    }

}
