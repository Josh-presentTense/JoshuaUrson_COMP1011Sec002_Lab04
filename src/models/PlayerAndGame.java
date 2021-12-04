package models;

public class PlayerAndGame {
    private int player_game_id;
    private int game_id;
    private int player_id;
    private String playing_date;
    private String score;

    /**
     * PlayerAndGame constructor with 5 parameters
     * @param player_game_id : int
     * @param game_id : int
     * @param player_id : int
     * @param playing_date : String
     * @param score : String
     */
    public PlayerAndGame(int player_game_id, int game_id, int player_id, String playing_date, String score) {
        setPlayer_game_id(player_game_id);
        setGame_id(game_id);
        setPlayer_id(player_id);
        setPlaying_date(playing_date);
        setScore(score);
    }

    public int getPlayer_game_id() {
        return player_game_id;
    }

    public void setPlayer_game_id(int player_game_id) {
        this.player_game_id = player_game_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getPlaying_date() {
        return playing_date;
    }

    public void setPlaying_date(String playing_date) {
        this.playing_date = playing_date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String toStringGameRecord() {
        return String.format("|Game ID: %d| - Score: %s, achieved on: %s - By: |Player ID: %d|",
                getGame_id(), getScore(), getPlaying_date(), getPlayer_id());
    }

    public String toStringPlayerRecord() {
        return String.format("|Player ID: %d| - Score: %s, from |Game ID: %d| - achieved on: %s",
                getPlayer_id(), getScore(), getGame_id(), getPlaying_date());
    }

    @Override
    public String toString() {
        return String.format("|Record ID: %d| - |Player ID: %d| - |Game ID: %d| - Date: %s - Score: %s",
                getPlayer_game_id(), getPlayer_id(), getGame_id(), getPlaying_date(), getScore());
    }
}
