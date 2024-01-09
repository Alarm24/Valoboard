package base;

import constant.GameMap;
import javafx.scene.control.Label;
import pane.BoardPane;
import pane.GameEndListener;
import pane.TopPane;
import util.Coordinate;
import util.Movement;

import java.util.ArrayList;
import java.util.List;

public class AgentManager {
    private static final int ACTIONS_PER_ROUND = 6;
    private GameEndListener gameEndListener;
    private static final AgentManager INSTANCE = new AgentManager();
    private final BoardPane boardPane;
    private Player attacker;
    private Player defender;
    private final GameMap gameMap;
    private int round = 1;
    private int actionCount = -1;
    private AgentManager() {
        boardPane = BoardPane.getInstance();
        this.gameMap = new GameMap();
    }
    public void setupGame(Player attacker, Player defender) {
        this.attacker = attacker;
        this.defender = defender;
    }
    public void startGame() {
        resetAgents();
        goNextIteration();
    }
    private boolean isAttackerTurn() {
        return (actionCount % 2 == 0);
    }
    private void clearAllEventHandler() {
        for (int i = 0; i < BoardPane.height; i++) {
            for (int j = 0; j < BoardPane.width; j++) {
                Label target = boardPane.getOneCell(i, j);
                target.setStyle(BoardPane.defaultGridStyle);
                target.setOnMouseClicked(null);
            }
        }
    }
    public void goNextIteration() {
        actionCount++;
        if (actionCount >= ACTIONS_PER_ROUND) {
            round++;
            actionCount = 0;
            resetAgents();
            TopPane.getInstance().updateTurnNumber(round);
        }
        for (Agent agent:getAllAgents()){
            if(agent.isAttacker){
                TopPane.getInstance().updateHPAtt(agent);
            }else{
                TopPane.getInstance().updateHPDef(agent);
            }
        }
        processPlayer();
        checkWinConditions();
    }
    private void processPlayer() {
        boolean isAttackerTurn = isAttackerTurn();
        clearAllEventHandler();
        if (isAttackerTurn) {
            attacker.processEachRound();
        } else {
            defender.processEachRound();
        }
    }
    private void resetAgents() {
        for (Agent agent : getAllAgents()) {
            agent.setNewRound();
        }
    }

    public boolean haveAgentCheck(int x , int y) {
        List<Agent> attackerAgent=attacker.getOwnedAgent();
        List<Agent> defenderAgent=defender.getOwnedAgent();
        for(Agent oneAgent:attackerAgent){
            if (oneAgent.getCurrentLocation().getRow()==x && oneAgent.getCurrentLocation().getCol()==y){
                return false;
            }
        }
        for(Agent oneAgent:defenderAgent){
            if (oneAgent.getCurrentLocation().getRow()==x && oneAgent.getCurrentLocation().getCol()==y){
                return false;
            }
        }
        return true;
    }
    public boolean haveAgent( Coordinate coord ,boolean isAttacker) {
        List<Agent> targetedAgent = (isAttacker) ? attacker.getOwnedAgent() : defender.getOwnedAgent();
        for (Agent oneAgent : targetedAgent) {
            if (oneAgent.getCurrentLocation().equals(coord)) {
                return true;
            }
        }
        return false;
    }

    public void havingShootAgent(Agent shooter, Coordinate destination){
        boolean isEnemy = haveAgent(destination, !shooter.isAttacker());
        if (isEnemy){
            List<Agent> enemyAgents = shooter.isAttacker() ? defender.getOwnedAgent() : attacker.getOwnedAgent();
            for (Agent enemy : enemyAgents) {
                if (enemy.getCurrentLocation().equals(destination)) {
                    if(shooter.isSmokeState()){
                        int newHp = enemy.getHp() - shooter.getShootingDamage()+2;
                        enemy.setHp(newHp);
                    }else{
                        int newHp = enemy.getHp() - shooter.getShootingDamage();
                        enemy.setHp(newHp);
                    }
                    break;
                }
            }
            for (Agent agent:getAllAgents()){
                if(agent.isAttacker){
                    TopPane.getInstance().updateHPAtt(agent);
                }else{
                    TopPane.getInstance().updateHPDef(agent);
                }
            }
            enemyAgents.removeIf(Agent::isDead);
            enemyAgents.forEach(Agent::removeAgentFromBoard);
        }
    }
    public void havingHealAgent(Agent shooter, Coordinate destination){
        boolean isEnemy = haveAgent(destination, !shooter.isAttacker());
        if (!isEnemy){
            List<Agent> allyAgents = shooter.isAttacker() ? attacker.getOwnedAgent() : defender.getOwnedAgent();
            for (Agent ally : allyAgents) {
                if (ally.getCurrentLocation().equals(destination)) {
                    int newHp = ally.getHp() + shooter.getHealDamage();
                    ally.setHp(newHp);
                    break;
                }
            }
        }
        shooter.setUltimateAvailable(false);
    }

    public void havingAgentMovement( Agent agent,  Coordinate from,  Coordinate to) {
        Player player = (agent.isAttacker()) ? attacker : defender;
        Movement movement = new Movement(player,agent, from, to);
    }

    public List<Agent> getAllAgents() {
        List<Agent> allAgents = new ArrayList<>();

        if (attacker != null && attacker.getOwnedAgent() != null) {
            allAgents.addAll(attacker.getOwnedAgent());
        }

        if (defender != null && defender.getOwnedAgent() != null) {
            allAgents.addAll(defender.getOwnedAgent());
        }


        return allAgents;
    }

    public List<Agent>getAttackerAgents(){
        List<Agent> attackerAgents = new ArrayList<>();
        if (attacker != null && attacker.getOwnedAgent() != null) {
            attackerAgents.addAll(attacker.getOwnedAgent());
        }
        return attackerAgents;
    }
    public List<Agent>getDefenderAgents(){
        List<Agent> defenderAgents = new ArrayList<>();
        if (defender != null && defender.getOwnedAgent() != null) {
            defenderAgents.addAll(defender.getOwnedAgent());
        }

        return defenderAgents;
    }

    private void checkWinConditions() {
        // Condition 1: Defender wins after 15 rounds
        if (round > 15) {
            endGame("Defender wins!");
            return;
        }

        // Condition 2: Attacker wins if 2 or more agents reach specific map coordinates
        if (checkAttackerWinCondition()) {
            endGame("Attacker wins!");
            return;
        }

        // Condition 3: Check if all enemy agents are dead
        if (checkAllAgentsDead()) {
            String winningSide = (attacker.getOwnedAgent().isEmpty()) ? "Defender" : "Attacker";
            endGame(winningSide + " wins!");
        }
    }

    private boolean checkAttackerWinCondition() {
        int attackersOnTarget = 0;
        for (Agent agent : attacker.getOwnedAgent()) {
            if (agent.isAttacker() && getMapCoordinate(agent.getCurrentLocation()) == 2) {
                attackersOnTarget++;
            }
        }
        return attackersOnTarget >= 2;
    }

    private boolean checkAllAgentsDead() {
        return attacker.getOwnedAgent().isEmpty() || defender.getOwnedAgent().isEmpty();
    }

    public void setGameEndListener(GameEndListener listener) {
        this.gameEndListener = listener;
    }

    private void endGame(String message) {
        gameEndListener.onGameEnd(message);
    }

    private int getMapCoordinate(Coordinate coordinate) {
        return gameMap.getCoordinate(coordinate.getCol(), coordinate.getRow());
    }


    public static AgentManager getInstance() {
        return INSTANCE;
    }
}
