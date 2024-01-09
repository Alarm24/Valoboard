package eventHandler;

import base.Agent;
import base.AgentManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import pane.BoardPane;
import util.Coordinate;

import java.util.List;

public class SelectAoeHandler implements EventHandler<ActionEvent> {
    private final Agent agent;
    private final BoardPane boardPane;
    private final AgentManager agentManager;

    public SelectAoeHandler(Agent agent) {
        this.agent = agent;
        this.boardPane = BoardPane.getInstance();
        this.agentManager = AgentManager.getInstance();
    }
    @Override
    public void handle(ActionEvent mouseEvent) {
        changeBackToDefaultChessBoard();
            List<Coordinate> availableEffect = agent.getSkillEffectRange(agent.getCurrentLocation(),5);
            for (Coordinate destination : availableEffect) {
                Label target = boardPane.getOneCell(destination);
                target.setStyle(BoardPane.defaultAvailableShootStyle);
                target.setOnMouseClicked(new AoeHandler(agent, destination));
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
