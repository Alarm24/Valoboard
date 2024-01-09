package pane;

import base.Agent;
import character.Brimstone;
import character.Jett;
import character.Sage;
import eventHandler.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomPane extends HBox {
    private static BottomPane INSTANCE = null;
    private BottomPane() {
        this.setAlignment(Pos.CENTER);

        Button firstButton = new Button("Move");
        Button secondButton = new Button("Shoot");
        Button thirdButton = new Button("Skill");
        Button fourthButton = new Button("Ultimate");

        firstButton.setPrefSize(150, 40);
        secondButton.setPrefSize(150, 40);
        thirdButton.setPrefSize(150, 40);
        fourthButton.setPrefSize(150, 40);


        String buttonStyle = "-fx-font-size: 16px;";
        firstButton.setStyle(buttonStyle);
        secondButton.setStyle(buttonStyle);
        thirdButton.setStyle(buttonStyle);
        fourthButton.setStyle(buttonStyle);

        this.getChildren().addAll(firstButton, secondButton, thirdButton, fourthButton);

        this.setSpacing(20);
    }

    public void prepareForAgent(Agent agent) {
        Button firstButton = (Button) getChildren().get(0);
        firstButton.setOnAction(event -> {
            agent.setUsingSkill(false); // Regular move
            new SelectMoveHandler(agent).handle(event);
        });

        Button secondButton = (Button) getChildren().get(1);
        secondButton.setOnAction(new SelectShootHandler(agent));

        Button thirdButton = (Button) getChildren().get(2);
        if (agent instanceof Jett) {
            thirdButton.setOnAction(event -> {
                agent.setUsingSkill(true);
                new JettSkillHandler(agent).handle(event);
            });
        } else if(agent instanceof Sage){
            thirdButton.setOnAction(new SelectAoeHandler(agent));
        } else if(agent instanceof Brimstone){
            thirdButton.setOnAction(new SelectAoeHandler(agent));
        }

        Button fourthButton = (Button) getChildren().get(3);
        fourthButton.setDisable(!agent.getUltimateAvailable());
        if (agent instanceof Jett) {
            fourthButton.setOnAction(new JettUltimateHandler(agent));
        } else if(agent instanceof Sage){
            fourthButton.setOnAction(new SageUltimateHandler(agent));
        } else if(agent instanceof Brimstone){
            fourthButton.setOnAction(new BrimstoneUltimateHandler(agent));
        }

    }

    public static BottomPane getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BottomPane();
        }
        return INSTANCE;
    }
}
