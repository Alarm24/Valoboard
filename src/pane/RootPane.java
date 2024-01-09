package pane;

import application.SceneController;
import base.AgentManager;
import base.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;


import java.util.Optional;
import java.util.Scanner;

public class RootPane extends BorderPane implements GameEndListener{
    private static RootPane rootPane = null;
    private double lastX, lastY;
    private double scale = 1;
    static private Player attacker = new Player("Attacker",true);
    static private Player defender = new Player("Defender",false);
    private BottomPane bottomPane;
    private SceneController sceneController;


    private RootPane() {
        AgentManager agentManager = AgentManager.getInstance();
        agentManager.setupGame(attacker, defender);


        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(backgroundFill));
        this.setPrefSize(1440, 900);

        TopPane topPane = TopPane.getInstance();
        HBox middleBox = getMiddlePane();
        bottomPane = BottomPane.getInstance();
        bottomPane.setVisible(false);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(topPane, middleBox, bottomPane);

        this.setCenter(vBox);

        setupZoom(middleBox);
        setupPan(middleBox);


        agentManager.startGame();
        agentManager.setGameEndListener(this);

    }

    private HBox getMiddlePane() {
        HBox middleBox = new HBox();
        middleBox.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        BoardPane boardPane = BoardPane.getInstance();
        stackPane.getChildren().add(boardPane);
        middleBox.getChildren().add(stackPane);

        return middleBox;
    }
    private void setupZoom(HBox middleBox) {
        middleBox.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            if (event.getDeltaY() < 0) {
                zoomFactor = 1 / zoomFactor;
            }

            // Calculate new scale
            double newScale = scale * zoomFactor;

            // Apply new scale
            middleBox.setScaleX(newScale);
            middleBox.setScaleY(newScale);

            // Update current scale
            scale = newScale;

            // Check if zoomed out to original scale and reset position
            if (scale <= 1) {
                scale = 1; // Ensure scale does not go below 1
                middleBox.setScaleX(1);
                middleBox.setScaleY(1);
                middleBox.setTranslateX(0);
                middleBox.setTranslateY(0);
            }

            event.consume();
        });
    }


    private void setupPan(HBox middleBox) {
        middleBox.setOnMousePressed((MouseEvent event) -> {
            if (scale > 1) { // Only allow panning if zoomed in
                lastX = event.getSceneX();
                lastY = event.getSceneY();
            }
        });

        middleBox.setOnMouseDragged((MouseEvent event) -> {
            if (scale > 1) { // Only allow panning if zoomed in
                double deltaX = event.getSceneX() - lastX;
                double deltaY = event.getSceneY() - lastY;
                lastX = event.getSceneX();
                lastY = event.getSceneY();
                middleBox.setTranslateX(middleBox.getTranslateX() + deltaX);
                middleBox.setTranslateY(middleBox.getTranslateY() + deltaY);
            }
        });
    }

    public void showEndGameDialog(String message) {
        Alert endGameAlert = new Alert(AlertType.CONFIRMATION);
        endGameAlert.setTitle("Game Over");
        endGameAlert.setHeaderText(message);

        ButtonType exitGameButton = new ButtonType("Exit Game", ButtonData.OK_DONE);

        endGameAlert.getButtonTypes().setAll(exitGameButton);

        Optional<ButtonType> response = endGameAlert.showAndWait();

        if (!response.isPresent() || response.get() == exitGameButton) {
            System.exit(0);
        }
    }


    public void onGameEnd(String message) {
        showEndGameDialog(message);
    }

    public void showBottomPane() {
        bottomPane.setVisible(true);
    }

    public void hideBottomPane() {
        bottomPane.setVisible(false);
    }


    public static synchronized RootPane getInstance() {
        if (rootPane == null) {
            rootPane = new RootPane();
        }
        return rootPane;
    }
}
