package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Game;
import models.Player;
import models.PlayerAndGame;
import utilities.DBConn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class displayInfoController implements Initializable {

    @FXML
    private ListView<Game> gamesListView;

    @FXML
    private ListView<Player> playersListView;

    @FXML
    private ListView<PlayerAndGame> recordsListView;

    @FXML
    private ComboBox<Integer> playerIdComboBox;

    @FXML
    private ComboBox<Integer> gameIdComboBox;

    @FXML
    private TextArea msgTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            gamesListView.getItems().addAll(DBConn.getGamesFromDB());
            playersListView.getItems().addAll(DBConn.getPlayersFromDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
