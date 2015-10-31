package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;

public class HigherOrLower extends AppCompatActivity {

    private ImageButton btH;
    private ImageButton btL;
    private TextView num, score;
    private int firstNum, lastNum, ranNum = -1, temp = -1, myScore = 0;
    private NumberProgressBar progressBar;
    private MyTimer myTimer;
    private boolean finish = false;
    private FloatingActionButton fab;
    private HighScore highScore;
    private LinearLayout main_view;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // add toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Higher or Lower");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HigherOrLower.this, HomeActivity.class));
                finish();
            }
        });

        highScore = new HighScore(this);
        score = (TextView) findViewById(R.id.point);
        btH = (ImageButton) findViewById(R.id.btHigher);
        btL = (ImageButton) findViewById(R.id.btLower);
        num = (TextView) findViewById(R.id.number);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (NumberProgressBar) findViewById(R.id.proTimer);
        main_view = (LinearLayout) findViewById(R.id.layout_main);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Sketch_Block.ttf");
        num.setTypeface(font);
        score.setTypeface(font);
        myTimer = new MyTimer(1500);
        myTimer.setID(progressBar);
        myTimer.setOnTickHtmlListener(gameLose);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_view.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                new CountDownTimer(1200, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        setRandomNumberLV1();
                        firstNum = Integer.parseInt(num.getText().toString());
                        btH.setEnabled(false);
                        btL.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        setRandomNumberLV1();
                        lastNum = Integer.parseInt(num.getText().toString());
                        myTimer.tick();
                        btH.setEnabled(true);
                        btL.setEnabled(true);
                    }
                }.start();
            }
        });


    }
    private void setRandomNumberLV1() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(100);
        if (temp == ranNum) {
            setRandomNumberLV1();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV2() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(400)+100;
        if (temp == ranNum) {
            setRandomNumberLV2();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV3() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(900)+100;
        if (temp == ranNum) {
            setRandomNumberLV3();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV4() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(4000)+1000;
        if (temp == ranNum) {
            setRandomNumberLV4();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV5() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(9000)+1000;
        if (temp == ranNum) {
            setRandomNumberLV5();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV6() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(5000)+10000;
        if (temp == ranNum) {
            setRandomNumberLV6();
        }
        num.setText("" + ranNum);
    }
    private void setRandomNumberLV7() {
        Random r = new Random();
        temp = ranNum;
        ranNum = r.nextInt(10000)+10000;
        if (temp == ranNum) {
            setRandomNumberLV7();
        }
        num.setText("" + ranNum);
    }
    public void choose(View view) {

        switch (view.getId()) {
            case R.id.btHigher:
                if (lastNum > firstNum) {
                    if(myScore<10)
                        setRandomNumberLV1();
                    else if (myScore>=10&&myScore<20)
                        setRandomNumberLV2();
                    else if (myScore>=10&&myScore<20)
                        setRandomNumberLV3();
                    else if (myScore>=20&&myScore<30)
                        setRandomNumberLV4();
                    else if (myScore>=30&&myScore<60)
                        setRandomNumberLV5();
                    else if (myScore>=60&&myScore<80)
                        setRandomNumberLV6();
                    else if (myScore>=80)
                        setRandomNumberLV7();
                    firstNum = lastNum;
                    lastNum = Integer.parseInt(num.getText().toString());
                    myScore++;
                    score.setText("" + myScore);
                    SoundUtil.play(this, SoundUtil.WIN);
                    myTimer.tick();
                } else {
                    score.setText("" + myScore);
                    gameLose.sendEmptyMessage(0);
                }
                break;
            case R.id.btLower:
                if (lastNum < firstNum) {
                    if(myScore<10)
                        setRandomNumberLV1();
                    else if (myScore>=10&&myScore<20)
                        setRandomNumberLV2();
                    else if (myScore>=10&&myScore<20)
                        setRandomNumberLV3();
                    else if (myScore>=20&&myScore<30)
                        setRandomNumberLV4();
                    else if (myScore>=30&&myScore<60)
                        setRandomNumberLV5();
                    else if (myScore>=60&&myScore<80)
                        setRandomNumberLV6();
                    else if (myScore>=80)
                        setRandomNumberLV7();
                    firstNum = lastNum;
                    lastNum = Integer.parseInt(num.getText().toString());
                    myScore++;
                    score.setText("" + myScore);
                    SoundUtil.play(this, SoundUtil.WIN);
                    myTimer.tick();
                } else {
                    score.setText("" + myScore);
                    gameLose.sendEmptyMessage(0);
                }
                break;
        }
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (finish) {
                return;
            }
            highScore.setScore(ID.NORMAL_SCORE_HIGHER_OR_LOWER, myScore);
            myScore = 0;
            SoundUtil.play(HigherOrLower.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(HigherOrLower.this, closeDialog);
            endDialog.show();
            myTimer.stop();
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

    @Override
    public void onBackPressed() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void onDestroy() {
        if (myTimer.timer != null) {
            myTimer.timer.cancel();
        }
        super.onDestroy();
    }
}
