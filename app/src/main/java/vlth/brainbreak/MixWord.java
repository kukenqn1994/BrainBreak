package vlth.brainbreak;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;

public class MixWord extends AppCompatActivity {

    Button[] btAnswer;
    TextView txtQuestion, txtScore;
    NumberProgressBar progressBar;
    MyTimer myTimer;

    String[] word_array;
    private int ca_position;
    private String strQuestion;
    private int myScore = 0;
    private boolean finish = false;

    private String correct_answer="";

    private HighScore highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_word);
        highScore=new HighScore(this);

        prototype();
        setRandomAnsser();
        myTimer = new MyTimer(4000);
        myTimer.setID(progressBar);

        myTimer.setOnTickHtmlListener(gameLose);
        myTimer.tick();

    }

    private void prototype() {
        btAnswer = new Button[4];
        btAnswer[0] = (Button) findViewById(R.id.ans1);
        btAnswer[1] = (Button) findViewById(R.id.ans2);
        btAnswer[2] = (Button) findViewById(R.id.ans3);
        btAnswer[3] = (Button) findViewById(R.id.ans4);
        txtQuestion = (TextView) findViewById(R.id.text);
        txtScore = (TextView) findViewById(R.id.point);
        progressBar = (NumberProgressBar) findViewById(R.id.proTimer);
        word_array = getResources().getStringArray(R.array.mix_word);
    }


    private String getRandomQuestion() {
        Random r = new Random();
        int index = word_array.length;
        txtQuestion.setText(word_array[r.nextInt(index)]);
        return txtQuestion.getText().toString();
    }


    private void setRandomAnsser() {
        Random r = new Random();
        char[] ch = getRandomQuestion().toCharArray();

        correct_answer="";
        for (int i = ch.length - 1; i >= 0; i--) {
            correct_answer = correct_answer + ch[i];
        }
        int length = correct_answer.length();

        StringBuilder sb1=new StringBuilder(correct_answer);
        StringBuilder sb2=new StringBuilder(correct_answer);
        StringBuilder sb3=new StringBuilder(correct_answer);
        StringBuilder sb4=new StringBuilder(correct_answer);

        char c;
        for (int i = 0; i < length; i++) {

            if(i%3==0){
            c = (char) (r.nextInt(26) + 'a');
            sb1.setCharAt(i,c);
            }

        }
        btAnswer[0].setText(sb1);

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                c = (char) (r.nextInt(26) + 'a');
                sb2.setCharAt(i,c);
            }
        }
        btAnswer[1].setText(sb2);

        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                c = (char) (r.nextInt(26) + 'a');
                sb3.setCharAt(i, c);
            }
        }
        btAnswer[2].setText(sb3);

        for (int i = 0; i < length; i++) {
            if (i % 3 == 1) {
                c = (char) (r.nextInt(26) + 'a');
                sb4.setCharAt(i, c);
            }
        }
        btAnswer[3].setText(sb4);

        ca_position = r.nextInt(3);
        btAnswer[ca_position].setText(correct_answer);


    }

    public void answerClick(View view) {
        String ans=((Button)view).getText().toString();
        if(ans.equals(correct_answer)){
            Toast.makeText(this,"Dung",Toast.LENGTH_SHORT).show();
            setRandomAnsser();
            myScore++;
            myTimer.tick();
        }else {
            Toast.makeText(this,"Sai",Toast.LENGTH_SHORT).show();
            gameLose.sendEmptyMessage(0);
        }
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (finish) {
                return;
            }
            highScore.setScore(ID.NORMAL_SCORE_MIX_WORD,myScore);
            myScore = 0;
            SoundUtil.play(MixWord.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(MixWord.this, closeDialog);
            endDialog.show();
            finish = true;
        }

    };

    private Handler closeDialog = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    };

    protected void onDestroy() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        super.onDestroy();
    }
}
