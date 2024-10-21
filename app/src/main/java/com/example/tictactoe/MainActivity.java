package com.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView[] images = new ImageView[9];
    private byte[][] cells = new byte[3][3];
    private boolean isPlayerOne;

    private ImageView imgPlayerOne;
    private ImageView imgPlayerTwo;

    private TextView txtPlayerOneWins;
    private TextView txtPlayerTwoWins;
    private boolean mustPlayerOneStart = true;

    private int countPlayerOneWins = 0;
    private int countPlayerTwoWins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        View.OnClickListener imageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView) view;
                int tag = (int) imageView.getTag();
                int row = tag / 3;
                int col = tag % 3;

                if (cells[row][col] != 0) {
                    Toast.makeText(MainActivity.this, "Invalid clicked!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("tag", row + " " + col + "");
                if (isPlayerOne) {
                    imageView.setImageResource(R.drawable.circle);
                    cells[row][col] = 1;
                } else {
                    imageView.setImageResource(R.drawable.cross);
                    cells[row][col] = 2;
                }
                isPlayerOne = !isPlayerOne;
                showCurrentPlayer();
                checkWinner();
            }
        };
        for (int i = 0; i < images.length; i++) {
            images[i].setTag(i);
            images[i].setOnClickListener(imageClickListener);

        }
        resetGame();
    }

    private void showCurrentPlayer() {
        if (isPlayerOne) {
            imgPlayerOne.setVisibility(View.VISIBLE);
            imgPlayerTwo.setVisibility(View.INVISIBLE);
        } else {
            imgPlayerOne.setVisibility(View.INVISIBLE);
            imgPlayerTwo.setVisibility(View.VISIBLE);
        }
    }

    private void sayWinner(int winnerId) {
        Toast.makeText(this, "player" + winnerId + "win", Toast.LENGTH_SHORT).show();
        if (winnerId == 1) {
            countPlayerOneWins++;
            txtPlayerOneWins.setText(countPlayerOneWins + "");
        } else {
            countPlayerTwoWins++;
            txtPlayerTwoWins.setText(countPlayerTwoWins + "");
        }
        resetGame();
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2] && cells[i][0] != 0) {
                sayWinner(cells[i][0]);
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i] && cells[0][i] != 0) {
                sayWinner(cells[0][i]);
                return;
            }
        }
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] && cells[0][0] != 0) {
            sayWinner(cells[0][0]);
            return;
        }
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0] && cells[0][2] != 0) {
            sayWinner(cells[0][2]);
        }
    }

    private void resetGame() {
        if (mustPlayerOneStart) {
            isPlayerOne = true;
        } else {
            isPlayerOne = false;
        }
        mustPlayerOneStart = !mustPlayerOneStart;
        showCurrentPlayer();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = 0;
            }
        }
        for (ImageView image : images) {
            image.setImageResource(0);
        }
    }

    private void bindViews() {
        images[0] = findViewById(R.id.img1);
        images[1] = findViewById(R.id.img2);
        images[2] = findViewById(R.id.img3);
        images[3] = findViewById(R.id.img4);
        images[4] = findViewById(R.id.img5);
        images[5] = findViewById(R.id.img6);
        images[6] = findViewById(R.id.img7);
        images[7] = findViewById(R.id.img8);
        images[8] = findViewById(R.id.img9);

        imgPlayerOne = findViewById(R.id.imgPlayerOne);
        imgPlayerTwo = findViewById(R.id.imgPlayerTwo);

        txtPlayerOneWins = findViewById(R.id.txtPlayerOneWins);
        txtPlayerTwoWins = findViewById(R.id.txtPlayerTwoWins);
    }
}