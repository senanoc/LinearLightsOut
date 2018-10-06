package com.example.socallaghan.linearlightsout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_SQUARES = 7;
    private LightsOutGame mGame;
    private TextView mGameStateTextView;
    private Button mNew_game_button;
    private int number_of_presses;
    private Button[] mButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            // Code to use what it returns to initialize the game object and screen.
            //savedInstanceState.getXX(key)
            //savedInstanceState.getBundle()
            //savedInstanceState.
            super.onCreate(savedInstanceState);

        } else {
            // Normal operation
            super.onCreate(savedInstanceState);
        }


        setContentView(R.layout.activity_main);


        mGame = new LightsOutGame(NUM_SQUARES);
        mGameStateTextView = findViewById(R.id.game_state_text_view);
        mNew_game_button = findViewById(R.id.new_game);
        mButtons = new Button[NUM_SQUARES];
        mButtons[0] = findViewById(R.id.button0);
        mButtons[1] = findViewById(R.id.button1);
        mButtons[2] = findViewById(R.id.button2);
        mButtons[3] = findViewById(R.id.button3);
        mButtons[4] = findViewById(R.id.button4);
        mButtons[5] = findViewById(R.id.button5);
        mButtons[6] = findViewById(R.id.button6);
        updateView();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save off data using outState.putXX(key, value)
        // Hint: you will use the appropriate methods to store int[] and ints,
        // maybe a String.
    }

    private void updateView() {

        if (number_of_presses == 0) {
            //mMessageTextView.setText(getString(R.string.message_color_points, mColorIdentificationScore));
            mGameStateTextView.setText(getString(R.string.instruction));
        } else {
            mGameStateTextView.setText(getString(R.string.number_of_turns, number_of_presses));
        }

        if (mGame.checkForWin()) {
            mGameStateTextView.setText(getString(R.string.you_won));
            mButtons[0].setEnabled(false);
            mButtons[1].setEnabled(false);
            mButtons[2].setEnabled(false);
            mButtons[3].setEnabled(false);
            mButtons[4].setEnabled(false);
            mButtons[5].setEnabled(false);
            mButtons[6].setEnabled(false);

            //mNew_game_button.setLayoutParams(o);
        }
        for (int i = 0; i < NUM_SQUARES; i++) {
            if (mGame.getValueAtIndex(i) == 1) {
                mButtons[i].setText("1");
            } else {
                mButtons[i].setText("0");
            }
        }

    }

    public void pressedSquare(View view) {
        String tagAsStr = view.getTag().toString();
        int tagAsInt = Integer.parseInt(tagAsStr);

        //Debug code
        //Log.d("TTT", "You pressed index " + tagAsInt);
        //Toast.makeText(this, "You pressed index " + tagAsInt, Toast.LENGTH_SHORT).show();

        number_of_presses++;
        mGame.setNumPresses(number_of_presses);
        mGame.pressedButtonAtIndex(tagAsInt);
        updateView();

    }

    public void pressedNewGame(View view) {
        //Debug code
        //Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();

        mButtons[0].setEnabled(true);
        mButtons[1].setEnabled(true);
        mButtons[2].setEnabled(true);
        mButtons[3].setEnabled(true);
        mButtons[4].setEnabled(true);
        mButtons[5].setEnabled(true);
        mButtons[6].setEnabled(true);
        mGame = new LightsOutGame(NUM_SQUARES);
        number_of_presses = 0;
        updateView();

    }
}
