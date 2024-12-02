package ca.centennialcollege.lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PlayerGameInfoController {
    // Insert
    @FXML
    private Label playerInfoLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressField;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private TextField postalCodeField;

    @FXML
    private Label provinceLabel;

    @FXML
    private TextField provinceField;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button insertPlayerButton;

    @FXML
    private Label gameInfoLabel;

    @FXML
    private Label gameTitleLabel;

    @FXML
    private TextField gameTitleField;

    @FXML
    private Button insertGameButton;

    // Update
    @FXML
    private Label playerInfoLabel_Update;

    @FXML
    private TextField firstNameField_Update;

    @FXML
    private TextField lastNameField_Update;

    @FXML
    private TextField addressField_Update;

    @FXML
    private TextField postalCodeField_Update;

    @FXML
    private TextField provinceField_Update;

    @FXML
    private TextField phoneNumberField_Update;

    @FXML
    private Button updatePlayerButton;

    @FXML
    private Label playerIdLabel;

    @FXML
    private ComboBox playerIdComboBox;

    @FXML
    private Label gameInfoLabel_Update;

    @FXML
    private Label gameTitleLabel_Update;

    @FXML
    private TextField gameTitleField_Update;

    @FXML
    private Button updateGameButton;

    @FXML
    private Label gameIdLabel;

    @FXML
    private ComboBox gameIdComboBox;

    @FXML
    private Label playerAndGameInfo;

    @FXML
    private Label gameIdLabel_Pag;

    @FXML
    private ComboBox gameIdComboBox_Pag;

    @FXML
    private Label playerIdLabel_Pag;

    @FXML
    private ComboBox playerIdComboBox_Pag;

    @FXML
    private Label playingDateLabel;

    @FXML
    private TextField playingDateField;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField scoreField;

    @FXML
    private ComboBox gameIdComboBox_Pag_Update;

    @FXML
    private ComboBox playerIdComboBox_Pag_Update;

    // Bottom Border
    @FXML
    private Button displayButton;

    @FXML
    private ComboBox pagComboBox;



    // Tables
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<PlayerAndGame, Integer> pagId;

    @FXML
    private TableColumn<PlayerAndGame, Integer> playerId;

    @FXML
    private TableColumn<PlayerAndGame, Integer> gameId;

    @FXML
    private TableColumn<PlayerAndGame, Date> playingDate;

    @FXML
    private TableColumn<PlayerAndGame, Integer> score;



    // CRUD Operations
    private DatabaseManager databaseManager;

    // Initialize DatabaseManager
    public PlayerGameInfoController() {
        this.databaseManager = new DatabaseManager();
    }

    // Insert Player Button Action
    @FXML
    private void insertPlayer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String province = provinceField.getText();
        String phoneNumber = phoneNumberField.getText();
        boolean success = databaseManager.addPlayer(firstName, lastName, address, postalCode, province, phoneNumber);
        if (success) {
            showAlert(
                    "Player Insertion",
                    "Inserted " + firstName + " " + lastName,
                    "You've successfully inserted " + firstName + " " + lastName + " into the Player table"
            );
            initialize();
        } else {
            showAlert(
                    "Player Insertion Failed",
                    "Failed to insert " + firstName + " " + lastName,
                    "Please check if your database connection is working."
            );
        }
    }

    // Insert Game Button Action
    @FXML
    private void insertGame() {
        String gameTitle = gameTitleField.getText();
        showAlert(
                "Game Insertion",
                "Inserted " + gameTitle,
                "You've successfully inserted " + gameTitle + " into the Game table"
        );
        databaseManager.addGame(gameTitle);
        initialize();
    }

    // Update Player Button Action
    @FXML
    private void updatePlayer() {
        String firstName = firstNameField_Update.getText();
        String lastName = lastNameField_Update.getText();
        String address = addressField_Update.getText();
        String postalCode = postalCodeField_Update.getText();
        String province = provinceField_Update.getText();
        String phoneNumber = phoneNumberField_Update.getText();

        // Player to be modified
        int playerId = Integer.parseInt(playerIdComboBox.getValue().toString());

        showAlert(
                "Player Update",
                "Updated " + firstName + " " + lastName,
                "You've successfully updated " + firstName + " " + lastName + " from the Player table"
        );

        databaseManager.updatePlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
    }

    // Update Game Button Action
    @FXML
    private void updateGame() {
        String gameTitle = gameTitleField_Update.getText();

        // Game to be modified
        int gameId = Integer.parseInt(gameIdComboBox.getValue().toString());

        showAlert(
                "Game Update",
                "Updated " + gameTitle,
                "You've successfully updated " + gameTitle + " from the Game table"
        );

        databaseManager.updateGame(gameId, gameTitle);
    }

    // Update Player And Game Action
    @FXML
    private void updatePlayerAndGame() {
        // Get input values from the text fields
        int gameId = Integer.parseInt(gameIdComboBox.getValue().toString());
        int playerId = Integer.parseInt(playerIdComboBox.getValue().toString());
        String playingDateString = playingDateField.getText();
        int score = Integer.parseInt(scoreField.getText());

        // Convert playingDateString to a Date
        Date playingDate = Date.valueOf(playingDateString);  // Example format: "YYYY-MM-DD"

        // Call the DatabaseManager to update the player-game relationship
        databaseManager.updatePlayerAndGame(gameId, playerId, playingDate, score);
    }


    // Display Players and Games Button Action
    @FXML
    private void displayData() {
        ObservableList<PlayerAndGame> observablePagList = FXCollections.observableArrayList(pagSetter(pagComboBox));
        tableView.setItems(observablePagList);
    }

    // Helper
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public ObservableList<PlayerAndGame> pagSetter(ComboBox<Player> pagComboBox) {
        Player selectedPlayer = pagComboBox.getSelectionModel().getSelectedItem();
        int player_id = selectedPlayer.getPlayerId();
        List<PlayerAndGame> pagList = databaseManager.readPlayerAndGames(String.valueOf(player_id));
        ObservableList<PlayerAndGame> observablePagList = FXCollections.observableArrayList(pagList);
        System.out.println("\nSelected  Player: " + selectedPlayer + "\n");
        return observablePagList;
    }

    public void initialize() {
        List<Player> playerList = databaseManager.readPlayers();
        List<Game> gameList = databaseManager.readGames();

        // Create observable lists
        ObservableList<Player> observablePlayerList = FXCollections.observableArrayList(playerList);
        ObservableList<Game> observableGameList = FXCollections.observableArrayList(gameList);

        // Populate the ComboBoxes
        playerIdComboBox.setItems(observablePlayerList);
        gameIdComboBox.setItems(observableGameList);
        playerIdComboBox_Pag.setItems(observablePlayerList);
        gameIdComboBox_Pag.setItems(observableGameList);
        playerIdComboBox_Pag_Update.setItems(observablePlayerList);
        gameIdComboBox_Pag_Update.setItems(observableGameList);
        pagComboBox.setItems(observablePlayerList);

        // Configure TableView columns
        pagId.setCellValueFactory(new PropertyValueFactory<>("playerGameId"));
        playerId.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        playingDate.setCellValueFactory(new PropertyValueFactory<>("playingDate"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

    }
}