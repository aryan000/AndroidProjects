package com.a.tictactoe;

public class Player {
  private int score;
  private boolean playerTurn;

  public int getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(int playerNumber) {
    this.playerNumber = playerNumber;
  }

  private int playerNumber;

  public Player(boolean playerTurn,int playerNumber) {
    this.playerTurn = playerTurn;
    this.playerNumber = playerNumber;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean isPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(boolean playerTurn) {
    this.playerTurn = playerTurn;
  }
}
