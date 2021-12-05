package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    private static final Pattern PHONENUM_PATTERN = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");
    private static final Pattern POSTALCODE_PATTERN = Pattern.compile("[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]");
    private static final Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setup toggle group for radio buttons
        this.selectionToggleGroup = new ToggleGroup();
        this.gameRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.playerRadioBtn.setToggleGroup(this.selectionToggleGroup);
        this.recordRadioBtn.setToggleGroup(this.selectionToggleGroup);
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
}
