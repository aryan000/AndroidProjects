package com.a.tictactoe;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

public class Game {

  private Player firstPlayer;
  private Player secondPlayer;
  private int totalTurns;
  private Context context;
  private Button[][] buttons;
  private Stack<Move> moves;

  public Game(Context context, Button[][] buttons) {
    firstPlayer = new Player(true, 1);
    secondPlayer = new Player(false, 2);
    this.context = context;
    this.buttons = buttons;
    totalTurns = 0;
    moves = new Stack<>();
  }


  public void updateNextMove(Button clickedButton) {

    if (clickedButton.getText() != "") {
      Toast.makeText(context, "Please select an empty box. This box is already marked",
          Toast.LENGTH_LONG).show();
      return;
    }

    if (firstPlayer.isPlayerTurn()) {
      clickedButton.setText("X");
      moves.push(new Move(clickedButton,1));
    } else {
      clickedButton.setText("O");
      moves.push(new Move(clickedButton,0));
    }

    firstPlayer.setPlayerTurn(!firstPlayer.isPlayerTurn());
    secondPlayer.setPlayerTurn(!secondPlayer.isPlayerTurn());
    totalTurns++;
  }


  public boolean hasPlayerWon() {
    String buttonValues[][] = new String[3][3];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        buttonValues[i][j] = buttons[i][j].getText().toString();
      }
    }

    // check row win
    for (int i = 0; i < 3; i++) {
      if (buttonValues[i][0].equalsIgnoreCase(buttonValues[i][1]) && buttonValues[i][1]
          .equalsIgnoreCase(buttonValues[i][2]) && !buttonValues[i][0].equalsIgnoreCase("")) {
        return true;
      }
    }

    // check column win
    for (int i = 0; i < 3; i++) {
      if (buttonValues[0][i].equalsIgnoreCase(buttonValues[1][i]) && buttonValues[1][i]
          .equalsIgnoreCase(buttonValues[2][i]) && !buttonValues[0][i].equalsIgnoreCase("")) {
        return true;
      }
    }

    if (buttonValues[0][0].equalsIgnoreCase(buttonValues[1][1]) && buttonValues[1][1]
        .equalsIgnoreCase(buttonValues[2][2]) && !buttonValues[0][0].equalsIgnoreCase("")) {
      return true;
    }

    if (buttonValues[2][0].equalsIgnoreCase(buttonValues[1][1]) && buttonValues[1][1]
        .equalsIgnoreCase(buttonValues[0][2]) && !(buttonValues[2][0].equalsIgnoreCase(""))) {
      return true;
    }

    return false;

  }

  public boolean isDraw() {
    return !hasPlayerWon() && totalTurns == 9;
  }

  public void updateScore(TextView textViewPlayer1, TextView textViewPlayer2) {

    if (firstPlayer.isPlayerTurn()) {
      declareWin(secondPlayer);
      Toast.makeText(context, "Second Player has won", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(context, "First Player has won", Toast.LENGTH_LONG).show();
      declareWin(firstPlayer);
    }

    textViewPlayer1.setText("Player 1  : " + firstPlayer.getScore());
    textViewPlayer2.setText("Player 2  : " + secondPlayer.getScore());
  }

  private void declareWin(Player player) {
    player.setScore(player.getScore() + 1);
  }

  public void resetBoard() {
    totalTurns = 0;
    firstPlayer.setPlayerTurn(true);
    secondPlayer.setPlayerTurn(false);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        buttons[i][j].setText("");
      }
    }
    moves.clear();
  }

  public void undoMove(){
    if(moves.empty()){
      Toast.makeText(context,"Ther are no previous moves",Toast.LENGTH_LONG).show();
    } else {
      Move move = moves.pop();
      move.clickedButton.setText("");
      totalTurns--;
    }
  }

}
