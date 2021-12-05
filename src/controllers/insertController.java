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

    private static final Pattern PHONENUM_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern POSTALCODE_PATTERN = Pattern.compile("^[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setup toggle group for radio buttons
        this.selectionToggleGroup = new ToggleGroup();
        this.gameRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.playerRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.recordRadioBtn.setToggleGroup(this.selectionToggleGroup);

        // Disable user input into the insert form (until a radio button is selected)
        disableGameInsertForm();
        disablePlayerInsertForm();
        disableRecordInsertForm();

        // Populate the combo boxes
        populateComboBoxes();
    }

    /**
     * This method will fill the playerID and gameID combo boxes with the needed IDs from the DB
     */
    private void populateComboBoxes() {
        try {
            // Store data from DB
            gamesList = DBConn.getGamesFromDB();
            playersList = DBConn.getPlayersFromDB();

            // Delete previous content from combo boxes
            playerIdComboBox.getItems().clear();
            gameIdComboBox.getItems().clear();

            // Populate the playerID and gameID combo box
            for(Player p : playersList)
                playerIdComboBox.getItems().add(p.getPlayer_id());
            for(Game g : gamesList)
                gameIdComboBox.getItems().add(g.getGame_id());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks what radio button has been selected to allow the correct insert form to be edited
     */
    public void insertSelectorRadioButtonChanged() {
        if (selectionToggleGroup.getSelectedToggle().equals(gameRadioBtn)) {
            enableGameInsertForm();
            disablePlayerInsertForm();
            disableRecordInsertForm();
        }

        if (selectionToggleGroup.getSelectedToggle().equals(playerRadioBtn)) {
            enablePlayerInsertForm();
            disableGameInsertForm();
            disableRecordInsertForm();
        }

        if (selectionToggleGroup.getSelectedToggle().equals(recordRadioBtn)) {
            enableRecordInsertForm();
            disableGameInsertForm();
            disablePlayerInsertForm();
        }
    }

    /*
    These methods are used to allow the user to add input for the selected insert
     */
    private void enableGameInsertForm() {
        gameNameTxtField.setDisable(false);
    }
    private void enablePlayerInsertForm() {
        firstNameTxtField.setDisable(false);
        lastNameTxtField.setDisable(false);
        addressTxtField.setDisable(false);
        postalCodeTxtField.setDisable(false);
        provinceTxtField.setDisable(false);
        phoneNumberTxtField.setDisable(false);

    }
    private void enableRecordInsertForm() {
        gameIdComboBox.setDisable(false);
        playerIdComboBox.setDisable(false);
        dateTxtField.setDisable(false);
        scoreTxtField.setDisable(false);
    }

    /*
    These methods are used to NOT allow the user to add input for the selected insert
     */
    private void disableGameInsertForm() {
        gameNameTxtField.setDisable(true);
    }
    private void disablePlayerInsertForm() {
        firstNameTxtField.setDisable(true);
        lastNameTxtField.setDisable(true);
        addressTxtField.setDisable(true);
        postalCodeTxtField.setDisable(true);
        provinceTxtField.setDisable(true);
        phoneNumberTxtField.setDisable(true);
    }
    private void disableRecordInsertForm() {
        gameIdComboBox.setDisable(true);
        playerIdComboBox.setDisable(true);
        dateTxtField.setDisable(true);
        scoreTxtField.setDisable(true);
    }

    @FXML
    public void submitButton() throws SQLException {
        if (gameRadioBtn.isSelected()) { // ----- IF SUBMITTING A GAME
            // Get user input from text fields
            String gameName = gameNameTxtField.getText();

            if (gameName.trim().isBlank()) {
                msgTxtArea.setText("Fill in all fields of the Game insert.");
            } else {
                // Insert into DB's games table
                DBConn.insertGameIntoDB(gameName);

                msgTxtArea.setText("Game added to DB.");

                populateComboBoxes(); // Populate combo boxes to allow for selection of new game by id
            }
        } else if (playerRadioBtn.isSelected()) { // ----- IF SUBMITTING A PLAYER
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

            if (firstName.trim().isBlank() || lastName.trim().isBlank() || address.trim().isBlank() || postalCode.trim().isBlank() || province.trim().isBlank() || phoneNumber.trim().isBlank()) {
                msgTxtArea.setText("Fill in all fields of the Player insert.");
            } else if (!pCodeMatchFound)
                msgTxtArea.setText("Postal code not valid. Follow pattern: A1B2C3");
            else if (!pNumMatchFound)
                msgTxtArea.setText("Phone number not valid. Follow pattern: 1112223344");
            else {
                // Insert into DB's games table
                DBConn.insertPlayerIntoDB(firstName, lastName, address, postalCode, province, phoneNumber);

                msgTxtArea.setText("Player added to DB.");

                populateComboBoxes(); // Populate combo boxes to allow for selection of new game by id
            }

        } else if (recordRadioBtn.isSelected()) { // ----- IF SUBMITTING A RECORD
            // Get user input from text fields
            int gameID = gameIdComboBox.getValue();
            int playerID = playerIdComboBox.getValue();
            String playerDate = dateTxtField.getText();
            String score = scoreTxtField.getText();

            // Setup regex
            Matcher pDateMatcher = DATE_PATTERN.matcher(playerDate);
            boolean pDateMatchFound = pDateMatcher.find();

            if (gameIdComboBox.getSelectionModel().isEmpty() || playerIdComboBox.getSelectionModel().isEmpty() || playerDate.trim().isBlank() || score.trim().isBlank()) {
                msgTxtArea.setText("Fill in all fields of the Record insert.");
            } else if (!pDateMatchFound)
                msgTxtArea.setText("Playing date not valid. Follow pattern: 2021-01-31");
            else {
                // Insert into DB's games table
                DBConn.insertRecordIntoDB(gameID, playerID, playerDate, score);

                msgTxtArea.setText("Record added to DB.");

                populateComboBoxes(); // Populate combo boxes to allow for selection of new game by id
            }

        } else {
            msgTxtArea.setText("Please select what type of data you would like to insert into DB. (Select a radio button)");
        }
    }

    @FXML
    public void backButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/displayInfoView.fxml", "Display of Players, Games, and Records");
    }
}
