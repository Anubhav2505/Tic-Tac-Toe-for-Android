package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2},{3,4,5},{7,6,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView Counter = (ImageView) view;
        System.out.println(Counter.getTag().toString());
        int tappedCounter = Integer.parseInt(Counter.getTag().toString());
        if (gamestate[tappedCounter] == 2) {
            gamestate[tappedCounter] = activeplayer;
            Counter.setTranslationY(-1000);
            if (activeplayer == 0) {
                Counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                Counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            Counter.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winningPosition : winningPosition){
            if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]]&&
                    gamestate[winningPosition[1]]==gamestate[winningPosition[2]]&&
                    gamestate[winningPosition[0]]!=2){
                String winner = "Red" ;
                if(gamestate[winningPosition[0]]==0){
                    winner = "Yellow" ;
                }
                TextView winnermessage =(TextView) findViewById(R.id.winnermessage);
                winnermessage.setText(winner + "has won");
                System.out.println(gamestate[winningPosition[0]]);
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                layout.setVisibility(View.VISIBLE );
            }
        }
    }
    public void  PlayAgain(View view){
        LinearLayout layout =(LinearLayout) findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        for (int i = 0; i< gamestate.length;i++){
            gamestate[i] = 2;}
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}