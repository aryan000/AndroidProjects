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

  private TextView textViewPlayer1;
  private TextView textViewPlayer2;
  private Game game;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textViewPlayer1 = findViewById(R.id.player1Score);
    textViewPlayer2 = findViewById(R.id.player2Score);
    initialiseButtons();
    game = new Game(this,buttons);
  }


  private void initialiseButtons() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        String buttonID = "button_" + i + j;
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        buttons[i][j] = findViewById(resID);
        buttons[i][j].setText("");
      }
    }
  }

  @Override
  public void onClick(View v) {
    Button clickedButton = (Button) v;
    game.updateNextMove(clickedButton);

    if(game.hasPlayerWon()){
      game.updateScore(textViewPlayer1,textViewPlayer2);
      game.resetBoard();

    } else if(game.isDraw()){
      Toast.makeText(this, "The Game has been Draw", Toast.LENGTH_LONG).show();
      game.resetBoard();
    }
  }


  public void resetButton(View v) {
    initialiseButtons();
  }

  public void undoMove(View v){
     game.undoMove();
  }
}
