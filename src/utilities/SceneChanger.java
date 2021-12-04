package utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
This java class was taken from Prof. Jaret Wright
He taught us this method to change between scenes in JavaFX during Year 1 Sem 2
 */

public class SceneChanger {
    /**
     * This method will allow us to change scenes
     */
    public static void changeScenes(ActionEvent event, String viewName, String title) throws IOException {


        Parent root = FXMLLoader.load(new SceneChanger().getClass().getResource(viewName));
        Scene scene = new Scene(root);

        //get the stage object from the ActionEvent that is triggered when the button is pushed
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}