package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;

public class MirrorWord extends AppCompatActivity {

    Button[] btAnswer;
    TextView txtQuestion, txtScore;
    NumberProgressBar progressBar;
    MyTimer myTimer;

    String[] words_lv1;
    private int ca_position;
    private int myScore = 0;

    private String correct_answer = "";

    private HighScore highScore;
    private ImageButton fab;
    private LinearLayout main_view;

    // Them vao
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_word);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        highScore = new HighScore(this);
        prototype();
        setRandomAnsser();
        myTimer = new MyTimer(2000);
        myTimer.setID(progressBar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_view.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                myTimer.tick();
                myTimer.setOnTickHtmlListener(gameLose);
            }
        });

        // Them vao
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle.setText("Mirror Word");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolbarTitle.setTypeface(fonts);
        txtQuestion.setTypeface(fonts);
        txtScore.setTypeface(fonts);
        btAnswer[0].setTypeface(fonts);
        btAnswer[1].setTypeface(fonts);
        btAnswer[2].setTypeface(fonts);
        btAnswer[3].setTypeface(fonts);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MirrorWord.this, HomeActivity.class));
                finish();
            }
        });

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
        words_lv1 = getResources().getStringArray(R.array.words_lv1);
        fab = (ImageButton) findViewById(R.id.fab);
        main_view = (LinearLayout) findViewById(R.id.main_layout);
    }


    private String getRandomQuestion() {
        Random r = new Random();
        int index = words_lv1.length;
        txtQuestion.setText(words_lv1[r.nextInt(index)]);
        return txtQuestion.getText().toString();
    }


    private void setRandomAnsser() {
        Random r = new Random();
        char[] ch = getRandomQuestion().toCharArray();

        correct_answer = "";
        for (int i = ch.length - 1; i >= 0; i--) {
            correct_answer = correct_answer + ch[i];
        }
        int length = correct_answer.length();

        StringBuilder sb1 = new StringBuilder(correct_answer);
        StringBuilder sb2 = new StringBuilder(correct_answer);
        StringBuilder sb3 = new StringBuilder(correct_answer);
        StringBuilder sb4 = new StringBuilder(correct_answer);

        char c;
        for (int i = 0; i < length; i++) {

            if (i % 3 == 0) {
                c = (char) (r.nextInt(26) + 'a');
                sb1.setCharAt(i, c);
            }

        }
        btAnswer[0].setText(sb1);

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                c = (char) (r.nextInt(26) + 'a');
                sb2.setCharAt(i, c);
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

        ca_position = r.nextInt(4);
        btAnswer[ca_position].setText(correct_answer);


    }

    public void answerClick(View view) {
        String ans = ((Button) view).getText().toString();
        if (ans.equals(correct_answer)) {
            setRandomAnsser();
            myScore++;
            txtScore.setText("" + myScore);
            SoundUtil.play(this, SoundUtil.WIN);
            myTimer.tick();
        } else {
            gameLose.sendEmptyMessage(0);
        }
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btAnswer[0].setEnabled(false);
            btAnswer[1].setEnabled(false);
            btAnswer[2].setEnabled(false);
            btAnswer[3].setEnabled(false);
            myTimer.stop();
            highScore.setScore(ID.NORMAL_SCORE_MIX_WORD, myScore);
            myScore = 0;
            SoundUtil.play(MirrorWord.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(MirrorWord.this, closeDialog);
            endDialog.show();
        }
    };

    private Handler closeDialog = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = getIntent();
            finish();
            if (msg.what == 0)
                startActivity(intent);
            if (msg.what == 1)
                finish();
        }
    };

    @Override
    public void onBackPressed() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        finish();
//        startActivity(new Intent(this, HomeActivity.class));
    }

    protected void onDestroy() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        super.onDestroy();
    }
}
