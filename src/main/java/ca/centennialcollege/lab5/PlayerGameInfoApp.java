package ca.centennialcollege.lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerGameInfoApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXML Loader
        FXMLLoader fxmlLoader = new FXMLLoader(PlayerGameInfoApp.class.getResource("player-game-info-view.fxml"));
        // Scene
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Stage
        stage.setTitle("Player Played Games Data");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}