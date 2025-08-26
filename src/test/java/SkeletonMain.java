import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class SkeletonMain {
    public static void main(String[] args) {

        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
        gameRunner.addAgent(Agent1.class);
        gameRunner.addAgent(Agent2.class);

        // gameRunner.addAgent("python3 /home/user/player.py");

        // The first league
        gameRunner.setLeagueLevel(1);
        // The second league
        // gameRunner.setLeagueLevel(2);

        gameRunner.start();
    }
}