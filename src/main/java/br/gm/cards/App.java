package br.gm.cards;

import java.io.IOException;

import br.gm.cards.model.Deck;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Deck deck = new Deck();
    public static String fxml;
    private static Scene scene;
    private int width = 600;
    private int height = 600;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fristOpen"), width, height);


        stage.setScene(scene);
        stage.setTitle("Cards");
        stage.setResizable(false);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}