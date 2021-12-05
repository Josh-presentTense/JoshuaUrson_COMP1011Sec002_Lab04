package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Game;
import models.Player;
import models.PlayerAndGame;
import utilities.DBConn;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class displayInfoController implements Initializable {

    @FXML
    private ListView<Game> gamesListView;

    @FXML
    private ListView<Player> playersListView;

    @FXML
    private ListView<String> recordsListView;

    @FXML
    private ComboBox<Integer> playerIdComboBox;

    @FXML
    private ComboBox<Integer> gameIdComboBox;

    @FXML
    private TextArea msgTextArea;

    ArrayList<Game> gamesList;
    ArrayList<Player> playersList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            // Store data from DB
            gamesList = DBConn.getGamesFromDB();
            playersList = DBConn.getPlayersFromDB();

            // Populate the Games and Players listviews
            gamesListView.getItems().addAll(gamesList);
            playersListView.getItems().addAll(playersList);

            // Populate the playerID and gameID combo box
            for(Player p : playersList)
                playerIdComboBox.getItems().add(p.getPlayer_id());
            for(Game g : gamesList)
                gameIdComboBox.getItems().add(g.getGame_id());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchByGameIdButton() throws SQLException {
        recordsListView.getItems().clear();

        if (gameIdComboBox.getSelectionModel().isEmpty())
            msgTextArea.setText("A gameID must be selected to begin a search using gameID");
        else {
            ArrayList<String> recordsByGameId = new ArrayList<>();

            recordsByGameId = DBConn.getRecordsBySpecifiedIdFromDB("game_id", gameIdComboBox.getValue());

            recordsListView.getItems().addAll(recordsByGameId);
        }
    }

    @FXML
    private void searchByPlayerIdButton() throws SQLException {
        recordsListView.getItems().clear();

        if (playerIdComboBox.getSelectionModel().isEmpty())
            msgTextArea.setText("A playerID must be selected to begin a search using playerID");
        else {
            ArrayList<String> recordsByPlayerId = new ArrayList<>();

            recordsByPlayerId = DBConn.getRecordsBySpecifiedIdFromDB("player_id", playerIdComboBox.getValue());

            recordsListView.getItems().addAll(recordsByPlayerId);
        }
    }

    @FXML
    private void newEntryButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/insertView.fxml", "Insert into Database");
    }

}
