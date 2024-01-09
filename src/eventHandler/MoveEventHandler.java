package eventHandler;
import base.Agent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pane.RootPane;
import util.Coordinate;
import base.AgentManager;

public class MoveEventHandler implements EventHandler<MouseEvent> {

    private final Agent agent;
    private final Coordinate from;
    private final Coordinate destination;
    private final AgentManager agentManager;

    MoveEventHandler(Agent agent, Coordinate destination){
        this.agent = agent;
        this.from = agent.getCurrentLocation();
        this.agentManager = AgentManager.getInstance();
        this.destination = destination;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
            movingAgent();
            agentManager.goNextIteration();
    }

    private void movingAgent(){
        agentManager.havingAgentMovement(agent, from, destination);
        RootPane.getInstance().hideBottomPane();
    }

}
