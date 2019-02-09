package com.a.tictactoe;

import android.widget.Button;

public class Move {

  public Button getClickedButton() {
    return clickedButton;
  }

  public void setClickedButton(Button clickedButton) {
    this.clickedButton = clickedButton;
  }

  Button clickedButton;

  public int getPlayer() {
    return player;
  }

  public void setPlayer(int player) {
    this.player = player;
  }

  private int player;


  public Move(Button clickedButton, int player) {
    this.clickedButton = clickedButton;
    this.player = player;
  }
}
