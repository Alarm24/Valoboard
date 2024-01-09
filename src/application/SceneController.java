package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.RootPane;


public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGame(ActionEvent event) throws IOException {
        RootPane rootPane = RootPane.getInstance();
        scene = new Scene(rootPane, 1200, 800);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ValoBoard");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRules(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Rules.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitProgram() {
        System.exit(0);
    }

}