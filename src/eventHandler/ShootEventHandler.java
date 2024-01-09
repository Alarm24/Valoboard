package eventHandler;
import base.Agent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pane.RootPane;
import util.Coordinate;
import base.AgentManager;

public class ShootEventHandler implements EventHandler<MouseEvent>{
    private final Agent agent;
    private final Coordinate from;
    private final Coordinate destination;
    private final AgentManager agentManager;
    ShootEventHandler(Agent agent, Coordinate destination){
        this.agent = agent;
        this.from = agent.getCurrentLocation();
        this.agentManager = AgentManager.getInstance();
        this.destination = destination;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        shootAgent();
        agentManager.goNextIteration();
    }

    private void shootAgent(){
        agentManager.havingShootAgent(agent, destination);
        RootPane.getInstance().hideBottomPane();
    }
}
