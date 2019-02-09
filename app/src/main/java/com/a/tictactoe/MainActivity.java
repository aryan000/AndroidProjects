package com.a.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private Button[][] buttons = new Button[3][3];

  private Player firstPlayer;
  private Player secondPlayer;
  private TextView textViewPlayer1;
  private TextView textViewPlayer2;
  private int totalTurns;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    firstPlayer = new Player(true, 1);
    secondPlayer = new Player(false, 2);
    textViewPlayer1 = findViewById(R.id.player1Score);
    textViewPlayer2 = findViewById(R.id.player2Score);
    totalTurns = 0;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        String buttonID = "button_" + i + j;
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        buttons[i][j] = findViewById(resID);
      }
    }

  }

  @Override
  public void onClick(View v) {

    Button clickedButton = (Button) v;

    if (clickedButton.getText() != "") {
      Toast.makeText(this, "Please select an empty box. This box is already marked",
          Toast.LENGTH_LONG).show();
      return;

    }

    if (firstPlayer.isPlayerTurn()) {
      clickedButton.setText("X");
    } else {
      clickedButton.setText("O");
    }

    if (checkWin()) {
      if (firstPlayer.isPlayerTurn()) {
        wins(firstPlayer);
        Toast.makeText(this, "First Player has won", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "Second Player has won", Toast.LENGTH_LONG).show();
        wins(secondPlayer);
      }
    } else if (totalTurns == 9) {
      draw();
    } else {
      firstPlayer.setPlayerTurn(!firstPlayer.isPlayerTurn());
      secondPlayer.setPlayerTurn(!secondPlayer.isPlayerTurn());
    }
    totalTurns++;
  }

  private void wins(Player player) {
    player.setScore(player.getScore() + 1);
    updatePointsText();
    resetBoard();

  }

  public void resetBoard() {

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        buttons[i][j].setText("");
      }
    }
    totalTurns = 0;
    firstPlayer.setPlayerTurn(true);
    secondPlayer.setPlayerTurn(false);
  }

  public void resetButton(View v){
    resetBoard();
  }

  private void updatePointsText() {

    textViewPlayer1.setText("Player 1  : " + firstPlayer.getScore());
    textViewPlayer2.setText("Player 2  : " + secondPlayer.getScore());
  }


  private void draw() {
    Toast.makeText(this, "Game is Withdraw", Toast.LENGTH_LONG).show();
    updatePointsText();
  }

  private boolean checkWin() {
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

    // check row win
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
}
