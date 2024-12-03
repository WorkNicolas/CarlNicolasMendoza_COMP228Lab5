package ca.centennialcollege.lab5;

import java.sql.Date;

public class PlayerAndGame {
    private int player_game_id;
    private int game_id;
    private int player_id;
    private Date playing_date;
    private int score;

    public PlayerAndGame(int player_game_id, int game_id, int player_id, Date playing_date, int score) {
        this.player_game_id = player_game_id;
        this.game_id = game_id;
        this.player_id = player_id;
        this.playing_date = playing_date;
        this.score = score;
    }

    public int getPlayerGameId() {
        return player_game_id;
    }

    public void setPlayerGameId(int player_game_id) {
        this.player_game_id = player_game_id;
    }

    public int getGameId() {
        return game_id;
    }

    public void setGameId(int game_id) {
        this.game_id = game_id;
    }

    public int getPlayerId() {
        return player_id;
    }

    public void setPlayerId(int player_id) {
        this.player_id = player_id;
    }

    public Date getPlayingDate() {
        return playing_date;
    }

    public void setPlayingDate(Date playing_date) {
        this.playing_date = playing_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
