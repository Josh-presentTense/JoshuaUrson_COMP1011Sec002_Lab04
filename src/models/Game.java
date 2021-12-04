package models;

public class Game {
    private int game_id;
    private String game_title;

    /**
     * Game constructor with 2 parameters
     * @param game_id : int
     * @param game_title : String
     */
    public Game(int game_id, String game_title) {
        setGame_id(game_id);
        setGame_title(game_title);
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    @Override
    public String toString() {
        return String.format("|ID: %d| - %s", getGame_id(), getGame_title());
    }
}
