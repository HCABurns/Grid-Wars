package com.codingame.game;
import com.codingame.gameengine.core.AbstractMultiplayerPlayer;

public class Player extends AbstractMultiplayerPlayer {

    private int x;
    private int y;
    private int hp;

    private boolean hasTrail;
    private boolean hasBomb;
    private boolean hasBigBomb;

    private int claimedIdentifier;
    private int unclaimedIdentifier;
    private int player_id;


    public void init(){
        this.hp = Constants.STARTING_HP;
        this.hasTrail = true;
        this.hasBomb = true;
        this.hasBigBomb = false;
    }

    public void setPosition(int y, int x){
        this.y = y;
        this.x = x;
    }

    public void setIdentifiers(int id){
        this.player_id = id;
        this.claimedIdentifier = id*2;
        this.unclaimedIdentifier = id*2+1;
    }



    @Override
    public int getExpectedOutputLines() {
        return 1;
    }
}