package ca.centennialcollege.lab5;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources").load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASS");

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CRUD operations for Game
    public void addGame(String gameTitle) {
        String query = "INSERT INTO Game (game_title) VALUES (?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameTitle);
            pstmt.executeUpdate();
            System.out.println("Game added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding game: " + e.getMessage());
        }
    }

    public List<Game> readGames() {
        String query = "SELECT * FROM Game";
        List<Game> gameList = new ArrayList<>();

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Create a Game object using data from the ResultSet
                Game game = new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title")
                );
                gameList.add(game);
            }
        } catch (SQLException e) {
            System.out.println("Error reading games: " + e.getMessage());
        }

        return gameList;
    }

    public void updateGame(int gameId, String gameTitle) {
        String query = "UPDATE Game SET game_title = ? WHERE game_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameTitle);
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
            System.out.println("Game updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating game: " + e.getMessage());
        }
    }

    // CRUD operations for Player
    public void addPlayer(String firstName, String lastName, String address, String postalCode, String province, String phoneNumber) {
        String query = "INSERT INTO Player (first_name, last_name, address, postal_Code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, postalCode);
            pstmt.setString(5, province);
            pstmt.setString(6, phoneNumber);
            pstmt.executeUpdate();
            System.out.println("Player added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

    public List<Player> readPlayers() {
        String query = "SELECT * FROM Player";
        List<Player> playerList = new ArrayList<>();  // Initialize an ArrayList to hold Player objects

        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Create a Player object using data from the ResultSet
                Player player = new Player(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("province"),
                        rs.getString("phone_number")
                );
                playerList.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error reading players: " + e.getMessage());
        }

        return playerList;
    }

    public void updatePlayer(int playerId, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber) {
        String query = "UPDATE Player SET first_name = ?, last_name = ?, address = ?, postal_Code = ?, province = ?, phone_number = ? WHERE player_id = ?";
        try (
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, address);
                pstmt.setString(4, postalCode);
                pstmt.setString(5, province);
                pstmt.setString(6, phoneNumber);
                pstmt.setInt(7, playerId);
                pstmt.executeUpdate();
                System.out.println("Player updated successfully."
            );
        } catch (SQLException e) {
            System.out.println("Error updating player: " + e.getMessage());
        }
    }

public Player getPlayerById(int playerId) {
    String query = "SELECT * FROM Player WHERE player_id = ?";
    Player player = new Player();
    try (
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            // Create a Player object using data from the ResultSet
            player = new Player(
                    rs.getInt("player_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("postal_code"),
                    rs.getString("province"),
                    rs.getString("phone_number")
            );
        }
    } catch (SQLException e) {
        System.out.println("Error updating player: " + e.getMessage());
    }
    return player;
}

    // CRUD operations for PlayerAndGame
    public void addPlayerAndGame(int gameId, int playerId, Date playingDate, int score) {
        String query = "INSERT INTO PlayerAndGame (game_id, player_id, playing_date, score) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, playerId);
            pstmt.setDate(3, playingDate);
            pstmt.setInt(4, score);
            pstmt.executeUpdate();
            System.out.println("Player and Game relationship added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding player and game relationship: " + e.getMessage());
        }
    }

    public List<PlayerAndGame> readPlayerAndGames() {
        // Updated query to include additional fields from the Player table
        String query = "SELECT pag.player_game_id, pag.game_id, pag.player_id, pag.playing_date, pag.score, " +
                "p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number " +
                "FROM PlayerAndGame pag " +
                "JOIN Player p ON pag.player_id = p.player_id"; // Join Player table to get player details

        List<PlayerAndGame> pagList = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Retrieve data from the PlayerAndGame table
                int playerGameId = rs.getInt("player_game_id");
                int gameId = rs.getInt("game_id");
                int playerId = rs.getInt("player_id");
                Date playingDate = rs.getDate("playing_date");
                int score = rs.getInt("score");

                // Retrieve Player data from the Player table
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String postalCode = rs.getString("postal_code");
                String province = rs.getString("province");
                String phoneNumber = rs.getString("phone_number");

                // Create Player object with the updated constructor
                Player player = new Player(playerId, firstName, lastName, address, postalCode, province, phoneNumber);

                // Create PlayerAndGame object and set the Player object
                PlayerAndGame pag = new PlayerAndGame(player, playerGameId, gameId, playerId, playingDate, score);

                // Add the PlayerAndGame object to the list
                pagList.add(pag);
            }
        } catch (SQLException e) {
            System.out.println("Error reading PlayerAndGame data: " + e.getMessage());
        }

        return pagList;
    }


    public void updatePlayerAndGame(int gameId, int playerId, Date playingDate, int score) {
        String query = "UPDATE PlayerAndGame SET playing_date = ?, score = ? WHERE game_id = ? AND player_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, playingDate);
            pstmt.setInt(2, score);
            pstmt.setInt(3, gameId);
            pstmt.setInt(4, playerId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player and Game relationship updated successfully.");
            } else {
                System.out.println("No matching player and game relationship found to update.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating player and game relationship: " + e.getMessage());
        }
    }
}
