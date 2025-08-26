package com.codingame.game;
import java.util.List;

import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;

public class Referee extends AbstractReferee {
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    private Board board;

    private int h;
    private int w;

    @Override
    public void init() {
        //todo: Decide
        gameManager.setFrameDuration(600);
        gameManager.setTurnMaxTime(50);
        gameManager.setMaxTurns(50);

        // Set the board size depending on level and create the board.
        if (gameManager.getLeagueLevel() == 1){
            //todo: Make slightly random?
            h = 10;
            w = 27;
        }
        board = new Board(h,w);

        // Set each players' starting position and their stats.
        int max_players = gameManager.getPlayerCount();
        int[][] starting_position = new int[gameManager.getPlayerCount()][2]; //[x,y]
        if (max_players == 2){
            starting_position[0] = new int[]{0,h/2};
            starting_position[1] = new int[]{w-1,h/2};
        }
        else if (max_players == 3){
            //todo: Place around center
            starting_position[0] = new int[]{0,h/2};
            starting_position[1] = new int[]{0,h/2};
            starting_position[2] = new int[]{0,h/2};
        }else {
            starting_position[0] = new int[]{0,0};
            starting_position[1] = new int[]{w-1,0};
            starting_position[2] = new int[]{0,h-1};
            starting_position[3] = new int[]{w-1,h-1};
        }

        int player_id = 0;
        for (Player player : gameManager.getActivePlayers()){
            player.init();
            player.setPosition(starting_position[player_id][1], starting_position[player_id][0]);
            player.setIdentifiers(player_id);
            player_id += 1;
        }

        // Send all players starting location with id? - Height and width of grid

    }

    @Override
    public void gameTurn(int turn) {

        // Send the players data
        //todo: Decide what data to provide the users - id:position, bomb-pos with id, 
        for (Player player : gameManager.getActivePlayers()) {
            player.sendInputLine("input");
            player.execute();
        }

        for (Player player : gameManager.getActivePlayers()) {
            try {
                List<String> outputs = player.getOutputs();
                // Check validity of the player output and compute the new game state
            } catch (TimeoutException e) {
                player.deactivate(String.format("$%d timeout!", player.getIndex()));
            }
        }
    }
}
