package ca.centennialcollege.lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
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
    private DatePicker playingDatePicker;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField scoreField;

    @FXML
    private Label playerAndGameInfo_Update;

    @FXML
    private Label gameIdLabel_Pag_Update;

    @FXML
    private ComboBox gameIdComboBox_Pag_Update;

    @FXML
    private Label playerIdLabel_Pag_Update;

    @FXML
    private ComboBox playerIdComboBox_Pag_Update;

    @FXML
    private Label playingDateLabel_Update;

    @FXML
    private DatePicker playingDatePicker_Update;

    @FXML
    private Label scoreLabel_Update;

    @FXML
    private TextField scoreField_Update;

    @FXML
    private Button insertPagButton_Update;


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
        boolean success = databaseManager.addGame(gameTitle);
        if (success) {
            showAlert(
                    "Game Insertion",
                    "Inserted " + gameTitle,
                    "You've successfully inserted " + gameTitle + " into the Game table"
            );
        } else {
            showAlert(
                    "Game Insertion Failed",
                    "Failed to insert " + gameTitle,
                    "Please check if your database connection is working."
            );
        }

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

        boolean success = databaseManager.updatePlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber);

        if (success) {
            showAlert(
                    "Player Update",
                    "Updated " + firstName + " " + lastName,
                    "You've successfully updated " + firstName + " " + lastName + " from the Player table"
            );
        } else {
            showAlert(
                    "Player Update Failed",
                    "You've failed to update " + firstName + " " + lastName,
                    "Please check if your database connection is working."
            );
        }

    }
    // Insert Player Button Action
    @FXML
    private void insertPlayerAndGame() {
        int gameId = Integer.parseInt(gameIdComboBox_Pag.getValue().toString());
        int playerId = Integer.parseInt(playerIdComboBox_Pag.getValue().toString());
        LocalDate playingLocalDate = playingDatePicker.getValue();
        Date sqlPlayingDate = Date.valueOf(playingLocalDate);
        int score = Integer.parseInt(scoreField.getText());

        boolean success = databaseManager.addPlayerAndGame(gameId, playerId, sqlPlayingDate, score);
        if (success) {
            showAlert(
                    "Player and Game Insertion",
                    "Inserted game_id:" + gameId + " and player_id:" + playerId,
                    "You've successfully inserted game_id:" + gameId + " and player_id:" + playerId + " into the Player table"
            );
            initialize();
        } else {
            showAlert(
                    "Player and Game Insertion Failed",
                    "Failed to insert game_id:" + gameId + " and player_id: " + playerId,
                    "Please check if your database connection is working."
            );
        }
    }

    // Update Game Button Action
    @FXML
    private void updateGame() {
        String gameTitle = gameTitleField_Update.getText();

        // Game to be modified
        int gameId = Integer.parseInt(gameIdComboBox.getValue().toString());

        boolean success = databaseManager.updateGame(gameId, gameTitle);

        if (success) {
            showAlert(
                    "Player And Game Update",
                    "Updated game_id:" + gameId + " and player_id:" + playerId,
                    "You've successfully updated game_id:" + gameId + " and player_id:" + playerId + " into the Player table"
            );
        } else {
            showAlert(
                    "Player and Game Update Failed",
                    "Failed to update game_id:" + gameId + " and player_id: " + playerId,
                    "Please check if your database connection is working."
            );
        }


    }

    // Update Player And Game Action
    @FXML
    private void updatePlayerAndGame() {
        // Get input values from the text fields
        int gameId = Integer.parseInt(gameIdComboBox_Pag_Update.getValue().toString());
        int playerId = Integer.parseInt(playerIdComboBox_Pag_Update.getValue().toString());
        LocalDate playingLocalDate = playingDatePicker_Update.getValue();
        Date sqlPlayingDate = Date.valueOf(playingLocalDate);
        int score = Integer.parseInt(scoreField_Update.getText());

        boolean success = databaseManager.updatePlayerAndGame(gameId, playerId, sqlPlayingDate, score);

        if (success) {
            showAlert(
                    "Player and Game Update",
                    "Updated player" + gameTitle,
                    "You've successfully updated " + gameTitle + " from the Game table"
            );
        } else {
            showAlert(
                    "Game Update Failed",
                    "Failed to update " + gameTitle,
                    "Please check if your database connection is working."
            );
        }
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