package com.example.gamerockscissorspaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Users users;

    Button btRock,btScissor,btPaper, btSave;
    TextView tvScore,tvChoiceUser;
    ImageView ivHuman,ivComputer;
    int humanScore, computerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btRock =(Button) findViewById(R.id.bt_rock);
        btPaper =(Button) findViewById(R.id.bt_paper);
        btScissor =(Button) findViewById(R.id.bt_scissors);
        btSave =(Button) findViewById(R.id.bt_save_score);

        tvScore = findViewById(R.id.tv_score);
        tvChoiceUser = findViewById(R.id.tv_title_human);

        ivComputer = findViewById(R.id.iv_comp);
        ivHuman = findViewById(R.id.iv_human);

        try {
            users = new Users(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        tvChoiceUser.setText("choice");

        Intent intent = getIntent();
        String name  = intent.getStringExtra("user");
        User user = users.getUser(name);
        user.setScore(user.getScore()+1);
        try {
            users.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void onClickRock(View view) {

        ivHuman.setImageResource(R.drawable.rock);
        play("rock");
        tvScore.setText("Hiscore: Human- " + Integer.toString(humanScore) + " "
                + "Computer- " + Integer.toString(computerScore));

    }

    public void onClickPaper(View view) {
        ivHuman.setImageResource(R.drawable.paper);
        play("paper");
        tvScore.setText("Hiscore: Human- " + Integer.toString(humanScore) + " "
                + "Computer- " + Integer.toString(computerScore));


    }

    public void onClickScissors(View view) {
        ivHuman.setImageResource(R.drawable.scissors);
        play("scissor");
        tvScore.setText("Hiscore: Human- " + Integer.toString(humanScore) + " "
                + "Computer- " + Integer.toString(computerScore));

    }

    public void onClickSave(View view) {

        Toast.makeText(getApplicationContext(), "inScoreActivity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
        startActivity(intent);

    }


    public String play (String playerChoice){

        String computerChoice = "";
        Random random = new Random();
        int computerChoiceNumber = random.nextInt(3) + 1;
        switch (computerChoiceNumber){
            case 1:
                computerChoice = "rock";
                ivComputer.setImageResource(R.drawable.rock);
                break;
            case 2:
                computerChoice = "paper";
                ivComputer.setImageResource(R.drawable.paper);
                break;
            case 3:
                computerChoice = "scissor";
                ivComputer.setImageResource(R.drawable.scissors);
                break;
        }

        if (computerChoice == playerChoice){
            return getString(R.string.draw);
        }
        else if (playerChoice == "rock" && computerChoice == "scissor"){
            humanScore ++;
            return "Rock crushes Scissor, YOU WIN!!!";
        }
        else if (playerChoice == "rock" && computerChoice == "paper"){
            computerScore++;
            return "Paper covers Rock, YOU LOSE!!!";
        }
        else if (playerChoice == "scissor" && computerChoice == "rock"){
            computerScore++;
            return "Rock crushes Scissor, YOU LOSE!!!";
        }
        else if (playerChoice == "scissor" && computerChoice == "paper"){
            humanScore ++;
            return "Scissor cuts Paper, YOU WIN!!!";
        }
        else if (playerChoice == "paper" && computerChoice == "rock"){
            humanScore ++;
            return "Paper covers Rock, YOU WIN!!!";
        }
        else if (playerChoice == "paper" && computerChoice == "scissor"){
            computerScore++;
            return "Scissor crushes Paper, YOU LOSE!!!";
        }
        return "Not sure";
    }

}
