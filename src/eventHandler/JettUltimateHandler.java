package eventHandler;
import base.Agent;
import base.AgentManager;
import character.Jett;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import pane.BoardPane;
import util.Coordinate;

import java.util.List;

public class JettUltimateHandler implements EventHandler<ActionEvent> {
    private final Agent agent;
    private final BoardPane boardPane;
    private final AgentManager agentManager;


    public JettUltimateHandler(Agent agent) {
        this.agent = agent;
        this.boardPane = BoardPane.getInstance();
        this.agentManager = AgentManager.getInstance();
    }

    @Override
    public void handle(ActionEvent mouseEvent) {
        changeBackToDefaultChessBoard();
        System.out.println("chess clicked " + agent.toString());
        ((Jett) agent).ultimateSkill();
        List<Coordinate> availableShoot = agent.getAvailableSkillRange(agent.getShootingDistance());
        for (Coordinate destination : availableShoot) {
            Label target = boardPane.getOneCell(destination);
            target.setStyle(BoardPane.defaultAvailableShootStyle);
            target.setOnMouseClicked(new ShootEventHandler(agent, destination));
        }
    }

    private void changeBackToDefaultChessBoard() {
        for (int i = 0; i < BoardPane.height; i++) {
            for (int j = 0; j < BoardPane.width; j++) {
                Label target = boardPane.getOneCell(i, j);
                target.setStyle(BoardPane.defaultGridStyle);
                //clear eventHandler of all grid with no same color chess
                if (!agentManager.haveAgent(new Coordinate(i, j), agent.isAttacker())) {
                    target.setOnMouseClicked(null);
                }
            }
        }
    }


}
