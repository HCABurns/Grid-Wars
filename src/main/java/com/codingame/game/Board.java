package com.codingame.game;

public class Board {

    private int h;
    private int w;
    private int[][] board;

    // Constructor
    public Board(int h, int w){
        this.h = h;
        this.w = w;
        board = new int[h][w];
    }

}
