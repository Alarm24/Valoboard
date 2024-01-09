package eventHandler;
import base.Agent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pane.RootPane;
import util.Coordinate;
import base.AgentManager;

public class HealEventHandler implements EventHandler<MouseEvent>{
    private final Agent agent;
    private final Coordinate destination;
    private final AgentManager agentManager;
    HealEventHandler(Agent agent, Coordinate destination){
        this.agent = agent;
        this.agentManager = AgentManager.getInstance();
        this.destination = destination;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        healAgent();
        agentManager.goNextIteration();
    }

    private void healAgent(){
        agentManager.havingHealAgent(agent, destination);
        RootPane.getInstance().hideBottomPane();
    }
}
