package ca.centennialcollege.lab5;

public class Game {
    private int game_id;
    private String game_title;

    public Game(int game_id, String game_title) {
        this.game_id = game_id;
        this.game_title = game_title;
    }

    public int getGameId() {
        return game_id;
    }

    public void setGameId(int game_id) {
        this.game_id = game_id;
    }

    public String getGameTitle() {
        return game_title;
    }

    public void setGameTitle(String game_title) {
        this.game_title = game_title;
    }

    @Override
    public String toString() {
        return game_id + " - " + game_title;
    }
}
