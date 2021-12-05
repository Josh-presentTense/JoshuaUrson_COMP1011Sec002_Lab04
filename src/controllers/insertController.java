package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Game;
import models.Player;
import utilities.DBConn;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class insertController implements Initializable {

    @FXML
    private RadioButton gameRadioBtn;

    @FXML
    private RadioButton playerRadioBtn;

    @FXML
    private RadioButton recordRadioBtn;

    private ToggleGroup selectionToggleGroup;

    @FXML
    private TextField gameNameTxtField;

    @FXML
    private TextField firstNameTxtField;

    @FXML
    private TextField lastNameTxtField;

    @FXML
    private TextField addressTxtField;

    @FXML
    private TextField postalCodeTxtField;

    @FXML
    private TextField provinceTxtField;

    @FXML
    private TextField phoneNumberTxtField;

    @FXML
    private ComboBox<Integer> gameIdComboBox;

    @FXML
    private ComboBox<Integer> playerIdComboBox;

    @FXML
    private TextField dateTxtField;

    @FXML
    private TextField scoreTxtField;

    @FXML
    private TextArea msgTxtArea;

    ArrayList<Game> gamesList;
    ArrayList<Player> playersList;

    private static final Pattern PHONENUM_PATTERN = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
    private static final Pattern POSTALCODE_PATTERN = Pattern.compile("^[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setup toggle group for radio buttons
        this.selectionToggleGroup = new ToggleGroup();
        this.gameRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.playerRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.recordRadioBtn.setToggleGroup(this.selectionToggleGroup);

        try {
            // Store data from DB
            gamesList = DBConn.getGamesFromDB();
            playersList = DBConn.getPlayersFromDB();

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
    public void submitButton() {
        if (gameRadioBtn.isSelected()) {
            // Get user input from text fields
            String gameName = gameNameTxtField.getText();

            // Insert into DB's games table

        } else if (playerRadioBtn.isSelected()) {
            // Get user input from text fields
            String firstName = firstNameTxtField.getText();
            String lastName = lastNameTxtField.getText();
            String address = addressTxtField.getText();
            String postalCode = postalCodeTxtField.getText();
            String province = provinceTxtField.getText();
            String phoneNumber = phoneNumberTxtField.getText();

            // Setup regex
            Matcher pCodeMatcher = POSTALCODE_PATTERN.matcher(postalCode);
            boolean pCodeMatchFound = pCodeMatcher.find();
            Matcher pNumMatcher = PHONENUM_PATTERN.matcher(phoneNumber);
            boolean pNumMatchFound = pNumMatcher.find();

            if (!pCodeMatchFound)
                msgTxtArea.setText("Postal code not valid. Follow pattern: A1B2C3");
            else if (!pNumMatchFound)
                msgTxtArea.setText("Phone number not valid. Follow pattern: 111-222-3344");
            else {
                // Insert into DB's players table
            }

        } else if (recordRadioBtn.isSelected()) {
            // Get user input from text fields
            int gameID = gameIdComboBox.getValue();
            int playerID = playerIdComboBox.getValue();
            String playerDate = dateTxtField.getText();
            String score = scoreTxtField.getText();

            // Setup regex
            Matcher pDateMatcher = DATE_PATTERN.matcher(playerDate);
            boolean pDateMatchFound = pDateMatcher.find();

            if (!pDateMatchFound)
                msgTxtArea.setText("Playing date not valid. Follow pattern: 2021-01-31");
            else {
                // Insert into DB's players_and_games table
            }

        } else {

        }
    }

    @FXML
    public void backButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/displayInfoView.fxml", "Display of Players, Games, and Records");
    }
}
