package ca.centennialcollege.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    // Display
    @FXML
    private Button displayButton;

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

        showAlert(
                "Player Insertion",
                "Inserted " + firstName + " " + lastName,
                "You've successfully inserted " + firstName + " " + lastName + " into the Player table"
        );

        databaseManager.addPlayer(firstName, lastName, address, postalCode, province, phoneNumber);
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
    }

    // Update Player Button Action
    @FXML
    private void updatePlayer() {
        // Player to be modified
        int playerId = Integer.parseInt(playerIdComboBox.getValue().toString());

        // Retrieve current player data
        Player currentPlayer = databaseManager.getPlayerById(playerId);

        // Use current value if the TextField is empty
        String firstName = firstNameField_Update.getText().isEmpty() ? currentPlayer.getFirstName() : firstNameField_Update.getText();
        String lastName = lastNameField_Update.getText().isEmpty() ? currentPlayer.getLastName() : lastNameField_Update.getText();
        String address = addressField_Update.getText().isEmpty() ? currentPlayer.getAddress() : addressField_Update.getText();
        String postalCode = postalCodeField_Update.getText().isEmpty() ? currentPlayer.getPostalCode() : postalCodeField_Update.getText();
        String province = provinceField_Update.getText().isEmpty() ? currentPlayer.getProvince() : provinceField_Update.getText();
        String phoneNumber = phoneNumberField_Update.getText().isEmpty() ? currentPlayer.getPhoneNumber() : phoneNumberField_Update.getText();

        // Show confirmation alert
        showAlert(
                "Player Update",
                "Updated " + firstName + " " + lastName,
                "You've successfully updated " + firstName + " " + lastName + " from the Player table"
        );

        // Update the player in the database
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

    }

    // Helper
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void initialize() {
        List<Player> playerList = new ArrayList<>();
        List<Game> gameList = new ArrayList<>();
        List<PlayerAndGame> pagList = new ArrayList<>();
        playerList = databaseManager.readPlayers();
        gameList = databaseManager.readGames();
        pagList = databaseManager.readPlayerAndGames();

    }
}