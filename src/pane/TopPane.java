package pane;

import base.Agent;
import base.AgentManager;
import character.Brimstone;
import character.Jett;
import character.Sage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Resources;
public class TopPane extends HBox{
    private static TopPane INSTANCE = null;
    private Label turnNumberLabel;
    private Label JettAttRank;
    private Label JettDefRank;
    private Label SageAttRank;
    private Label SageDefRank;
    private Label BrimAttRank;
    private Label BrimDefRank;

    private TopPane() {

        HBox attackerPane = new HBox();

        for (Agent agent : AgentManager.getInstance().getAttackerAgents()) {
            VBox agentPanel= new VBox();
            agentPanel.setStyle("-fx-background-color: #202226;"); // Set background color

            ImageView logo = Resources.loadAgentIcon(agent.getAgentIconPath());
            logo.setFitHeight(40);
            logo.setFitWidth(40);

            Label playerName = new Label(agent.toString());
            playerName.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 14;");
            if (agent instanceof Jett){
                JettAttRank = new Label(agent.getHp()+"HP");
                JettAttRank.setStyle("-fx-text-fill: #6C757D;");
            }

            if (agent instanceof Sage){
                SageAttRank = new Label(agent.getHp()+"HP");
                SageAttRank.setStyle("-fx-text-fill: #6C757D;");
            }

            if (agent instanceof Brimstone){
                BrimAttRank = new Label(agent.getHp()+"HP");
                BrimAttRank.setStyle("-fx-text-fill: #6C757D;");
            }

            HBox.setMargin(playerName, new javafx.geometry.Insets(0, 10, 2, 10));
            if(agent instanceof Jett){
                HBox.setMargin(JettAttRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, JettAttRank);
            }
            if(agent instanceof Sage){
                HBox.setMargin(SageAttRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, SageAttRank);
            }
            if(agent instanceof Brimstone){
                HBox.setMargin(BrimAttRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, BrimAttRank);
            }

            agentPanel.setAlignment(Pos.CENTER);

            agentPanel.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

            attackerPane.getChildren().add(agentPanel);
        }

        attackerPane.setSpacing(10);

        VBox roundPane = new VBox();

        Label turnLabel = new Label("Round");
        turnNumberLabel = new Label("1");
        turnLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");
        turnNumberLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: white;");

        roundPane.getChildren().addAll(turnLabel, turnNumberLabel);
        roundPane.setAlignment(Pos.CENTER);


        HBox defenderPane = new HBox();

        for (Agent agent : AgentManager.getInstance().getDefenderAgents()) {
            VBox agentPanel= new VBox();
            agentPanel.setStyle("-fx-background-color: #202226;"); // Set background color

            ImageView logo = Resources.loadAgentIcon(agent.getAgentIconPath());
            logo.setFitHeight(40);
            logo.setFitWidth(40);

            Label playerName = new Label(agent.toString());
            playerName.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 14;");

            if (agent instanceof Jett){
                JettDefRank = new Label(agent.getHp()+"HP");
                JettDefRank.setStyle("-fx-text-fill: #6C757D;");
            }

            if (agent instanceof Sage){
                SageDefRank = new Label(agent.getHp()+"HP");
                SageDefRank.setStyle("-fx-text-fill: #6C757D;");
            }

            if (agent instanceof Brimstone){
                BrimDefRank = new Label(agent.getHp()+"HP");
                BrimDefRank.setStyle("-fx-text-fill: #6C757D;");
            }

            HBox.setMargin(playerName, new javafx.geometry.Insets(0, 10, 2, 10));
            if(agent instanceof Jett){
                HBox.setMargin(JettDefRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, JettDefRank);
            }
            if(agent instanceof Sage){
                HBox.setMargin(SageDefRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, SageDefRank);
            }
            if(agent instanceof Brimstone){
                HBox.setMargin(BrimDefRank, new javafx.geometry.Insets(0, 10, 0, 10));
                agentPanel.getChildren().addAll(logo, playerName, BrimDefRank);
            }

            agentPanel.setAlignment(Pos.CENTER);

            agentPanel.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));


            defenderPane.getChildren().add(agentPanel);
        }

        defenderPane.setSpacing(10);

        this.getChildren().addAll(attackerPane, roundPane, defenderPane);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }

    public void updateHPAtt(Agent agent){
        if(agent instanceof Jett){
            JettAttRank.setText(String.valueOf(agent.getHp())+" HP");
        }
        if(agent instanceof Sage){
            SageAttRank.setText(String.valueOf(agent.getHp())+" HP");
        }
        if(agent instanceof Brimstone){
            BrimAttRank.setText(String.valueOf(agent.getHp())+" HP");
        }
    }

    public void updateHPDef(Agent agent){
        if(agent instanceof Jett){
            JettDefRank.setText(String.valueOf(agent.getHp())+" HP");
        }
        if(agent instanceof Sage){
            SageDefRank.setText(String.valueOf(agent.getHp())+" HP");
        }
        if(agent instanceof Brimstone){
            BrimDefRank.setText(String.valueOf(agent.getHp())+" HP");
        }
    }


    public void updateTurnNumber(int turnNumber) {
        turnNumberLabel.setText(String.valueOf(turnNumber));
    }

    public static TopPane getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TopPane();
        }
        return INSTANCE;
    }
}
