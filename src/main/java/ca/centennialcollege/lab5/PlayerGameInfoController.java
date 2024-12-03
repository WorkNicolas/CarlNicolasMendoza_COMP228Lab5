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

    @FXML
    private TableView playerView;

    @FXML
    private TableColumn<Player, Integer> playerId_pView;

    @FXML
    private TableColumn<Player, String> fname;

    @FXML
    private TableColumn<Player, String> lname;

    @FXML
    private TableColumn<Player, String> address;

    @FXML
    private TableColumn<Player, String> province;

    @FXML
    private TableColumn<Player, String> postalCode;

    @FXML
    private TableColumn<Player, String> phoneNumber;

    // CRUD Operations
    private DatabaseManager databaseManager;

    // Initialize DatabaseManager
    public PlayerGameInfoController() {
        this.databaseManager = new DatabaseManager();
    }

    // Insert Player Button Action
    @FXML
    private void insertPlayer() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String address = addressField.getText().trim();
        String postalCode = postalCodeField.getText().trim();
        String province = provinceField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || province.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Player Insertion Failed",
                    "Please fill in all fields before submitting."
            );
            return;
        }

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
        String gameTitle = gameTitleField.getText().trim();

        // Check if the game title is empty
        if (gameTitle.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Game Insertion Failed",
                    "Please provide a game title before submitting."
            );
            return;
        }

        boolean success = databaseManager.addGame(gameTitle);

        if (success) {
            showAlert(
                    "Game Insertion",
                    "Inserted " + gameTitle,
                    "You've successfully inserted " + gameTitle + " into the Game table"
            );
            initialize();
        } else {
            showAlert(
                    "Game Insertion Failed",
                    "Failed to insert " + gameTitle,
                    "Please check if your database connection is working."
            );
        }
    }

    // Update Player Button Action
    @FXML
    private void updatePlayer() {
        String firstName = firstNameField_Update.getText().trim();
        String lastName = lastNameField_Update.getText().trim();
        String address = addressField_Update.getText().trim();
        String postalCode = postalCodeField_Update.getText().trim();
        String province = provinceField_Update.getText().trim();
        String phoneNumber = phoneNumberField_Update.getText().trim();

        // Player to be modified
        Player selectedPlayer = (Player) playerIdComboBox.getValue();
        if (selectedPlayer == null) {
            showAlert(
                    "Invalid Input",
                    "Player Selection Failed",
                    "Please select a valid player."
            );
            return;
        }
        Integer playerId = selectedPlayer.getPlayerId();

        // Check if any field is empty
        if (playerId == null || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || province.isEmpty() || phoneNumber.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Player Update Failed",
                    "Please fill in all fields before submitting."
            );
            return;
        }


        boolean success = databaseManager.updatePlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber);

        if (success) {
            showAlert(
                    "Player Update",
                    "Updated " + firstName + " " + lastName,
                    "You've successfully updated " + firstName + " " + lastName + " from the Player table"
            );
            initialize();
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
        // Get the selected Game and Player objects
        Game selectedGame = (Game) gameIdComboBox_Pag.getValue();
        Player selectedPlayer = (Player) playerIdComboBox_Pag.getValue();

        // Check if the selected game or player is null
        if (selectedGame == null || selectedPlayer == null) {
            showAlert(
                    "Invalid Input",
                    "Player and Game Insertion Failed",
                    "Please select a valid game and player."
            );
            return;
        }

        Integer gameId = selectedGame.getGameId();
        Integer playerId = selectedPlayer.getPlayerId();

        // Get other input values
        LocalDate playingLocalDate = playingDatePicker.getValue();
        String scoreText = scoreField.getText().trim();

        // Check if any field is empty or null
        if (gameId == null || playerId == null || playingLocalDate == null || scoreText.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Player and Game Insertion Failed",
                    "Please fill in all fields before submitting."
            );
            return;
        }

        // Parse the score input
        int score;
        try {
            score = Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            showAlert(
                    "Invalid Score",
                    "Player and Game Insertion Failed",
                    "Score must be a valid number."
            );
            return;
        }

        // Convert LocalDate to Date
        Date sqlPlayingDate = Date.valueOf(playingLocalDate);

        // Insert player and game relationship into the database
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
        String gameTitle = gameTitleField_Update.getText().trim();

        // Validate that the game title is not empty
        if (gameTitle.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Game Update Failed",
                    "Please provide a valid game title."
            );
            return;
        }

        // Get the selected game ID from the ComboBox
        Game selectedGame = (Game) gameIdComboBox.getValue();
        if (selectedGame == null) {
            showAlert(
                    "Invalid Input",
                    "Game Selection Failed",
                    "Please select a valid game."
            );
            return;
        }

        int gameId = selectedGame.getGameId();

        boolean success = databaseManager.updateGame(gameId, gameTitle);

        if (success) {
            showAlert(
                    "Player and Game Update",
                    "Updated player" + gameTitle,
                    "You've successfully updated " + gameTitle + " from the Game table"
            );
            initialize();
        } else {
            showAlert(
                    "Game Update Failed",
                    "Failed to update " + gameTitle,
                    "Please check if your database connection is working."
            );
        }
    }

    // Update Player And Game Action
    @FXML
    private void updatePlayerAndGame() {
        // Get the selected Game and Player objects
        Game selectedGame = (Game) gameIdComboBox_Pag_Update.getValue();
        Player selectedPlayer = (Player) playerIdComboBox_Pag_Update.getValue();

        // Check if either the selected Game or Player is null
        if (selectedGame == null) {
            showAlert(
                    "Invalid Input",
                    "Update Failed",
                    "Please select a valid game ID."
            );
            return;
        }

        if (selectedPlayer == null) {
            showAlert(
                    "Invalid Input",
                    "Update Failed",
                    "Please select a valid player ID."
            );
            return;
        }

        Integer gameId = selectedGame.getGameId(); // Assuming Game object has getGameId() method
        Integer playerId = selectedPlayer.getPlayerId(); // Assuming Player object has getPlayerId() method

        LocalDate playingLocalDate = playingDatePicker_Update.getValue();
        String scoreText = scoreField_Update.getText().trim();

        // Validate that playing date and score are valid
        if (playingLocalDate == null) {
            showAlert(
                    "Invalid Input",
                    "Update Failed",
                    "Please select a valid playing date."
            );
            return;
        }

        if (scoreText.isEmpty()) {
            showAlert(
                    "Invalid Input",
                    "Update Failed",
                    "Please provide a valid score."
            );
            return;
        }

        // Convert score to integer and handle potential invalid input
        int score;
        try {
            score = Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            showAlert(
                    "Invalid Input",
                    "Update Failed",
                    "Please provide a valid numeric score."
            );
            return;
        }

        // Convert playing date to SQL Date
        Date sqlPlayingDate = Date.valueOf(playingLocalDate);

        // Update the player and game relationship in the database
        boolean success = databaseManager.updatePlayerAndGame(gameId, playerId, sqlPlayingDate, score);

        if (success) {
            showAlert(
                    "Player And Game Update",
                    "Updated game_id:" + gameId + " and player_id:" + playerId,
                    "You've successfully updated game_id:" + gameId + " and player_id:" + playerId + " in the Player table"
            );
            initialize();
        } else {
            showAlert(
                    "Player and Game Update Failed",
                    "Failed to update game_id:" + gameId + " and player_id:" + playerId,
                    "Please check if your database connection is working."
            );
        }
    }


    // Display Players and Games Button Action
    @FXML
    private void displayData() {
        ObservableList<PlayerAndGame> observablePagList = FXCollections.observableArrayList(pagSetter(pagComboBox));
        ObservableList<Player> observablePlayerList = FXCollections.observableArrayList(playerSetter(pagComboBox));
        tableView.setItems(observablePagList);
        playerView.setItems(observablePlayerList);
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

    public ObservableList<Player> playerSetter(ComboBox<Player> pagComboBox) {
        Player selectedPlayer = pagComboBox.getSelectionModel().getSelectedItem();
        int player_id = selectedPlayer.getPlayerId();
        List<Player> playerList = databaseManager.readPlayerId(player_id);
        ObservableList<Player> observablePlayerList = FXCollections.observableArrayList(playerList);
        return observablePlayerList;
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
        playerId_pView.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        province.setCellValueFactory(new PropertyValueFactory<>("province"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }
}