package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class FreakingMath extends AppCompatActivity {

    private TextView bt1, bt2, bt3, res;
    private TextView info, info1, info2;
    private ImageButton ans1, ans2;
    private TextView tw;
    private NumberProgressBar pr;
    String result;
    String ans;
    private ImageButton fabtn;
    private boolean finish = false;
    private HighScore highScore;
    private int myScore = 0;
    private Toolbar toolbar;
    private LinearLayout mainView;
    private TextView toolbarTitle;
    private MyTimer mytimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freaking_math);
        highScore = new HighScore(this);

        bt1 = (TextView) findViewById(R.id.num1);
        bt2 = (TextView) findViewById(R.id.num2);
        bt3 = (TextView) findViewById(R.id.math);

        ans1 = (ImageButton) findViewById(R.id.btn1);
        ans2 = (ImageButton) findViewById(R.id.btn2);
        res = (TextView) findViewById(R.id.ans);

        tw = (TextView) findViewById(R.id.point);

        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/BradBun.ttf");
        bt1.setTypeface(custom_fonts);
        bt2.setTypeface(custom_fonts);
        bt3.setTypeface(custom_fonts);
        res.setTypeface(custom_fonts);

        // Them vao
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Freaking Math");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolbarTitle.setTypeface(fonts,Typeface.BOLD);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FreakingMath.this, HomeActivity.class));
                finish();
            }
        });
        mytimer = new MyTimer(1500);
        mainView = (LinearLayout) findViewById(R.id.FreakingMath);
        fabtn = (ImageButton) findViewById(R.id.fab);
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.setVisibility(View.VISIBLE);
                fabtn.setVisibility(View.GONE);
//                info.setVisibility(View.GONE);
//                info1.setVisibility(View.GONE);
//                info2.setVisibility(View.GONE);

                pr = (NumberProgressBar) findViewById(R.id.proTimer);

                tw.setText(Integer.toString(myScore));
                play(myScore);
            }
        });

//        info = (TextView) findViewById(R.id.info);
//        info1 = (TextView) findViewById(R.id.info1);
//        info2 = (TextView) findViewById(R.id.info2);
//        info.setTypeface(fonts);
//        info1.setTypeface(fonts);
//        info2.setTypeface(fonts);
//
//        info1.setText(R.string.info_fm1);
//        info2.setText(R.string.info_fm2);
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_freaking_math, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void play (final int point) {
        int num1, num2, num3;
        num1 = random20();
        num2 = random20();
        num3 = randommath();

        int rd = random2();
        int wr;

        mytimer.setOnTickHtmlListener(gameLose);
        mytimer.setID(pr);
        mytimer.tick();

        String math;

        switch (num3) {
            case 0:
                math = "+";
                bt3.setText(math);
                bt1.setText(Integer.toString(num1));
                bt2.setText(Integer.toString(num2));
                result = Integer.toString(num1 + num2);
                wr = (num1+num2) + 4;
                switch (rd) {
                    case 0:
                        ans = result;
                        res.setText("= " + result);
                        break;
                    case 1:
                        ans = Integer.toString(wr);
                        res.setText("= " + Integer.toString(wr));
                        break;
                }
                break;
            case 1:
                math = "-";
                while (num1 < 10) {
                    num1 = random30();
                }
                while ((num2 > 10) || (num2 > num1) || (num1==0)) {
                    num2 = random30();
                }
                bt3.setText(math);
                bt1.setText(Integer.toString(num1));
                bt2.setText(Integer.toString(num2));
                result = Integer.toString(num1 - num2);
                wr = (num1+num2) + 4;
                switch (rd) {
                    case 0:
                        ans = result;
                        res.setText("= " + result);
                        break;
                    case 1:
                        ans = Integer.toString(wr);
                        res.setText("= " + Integer.toString(wr));
                        break;
                }
                break;
            case 2:
                math = "*";
                while (num1 > 10) {
                    num1 = random30();
                }
                while (num2 > 10) {
                    num2 = random30();
                }
                bt3.setText(math);
                bt1.setText(Integer.toString(num1));
                bt2.setText(Integer.toString(num2));
                result = Integer.toString(num1 * num2);
                wr = (num1+num2) + 4;
                switch (rd) {
                    case 0:
                        ans = result;
                        res.setText("= " + result);
                        break;
                    case 1:
                        ans = Integer.toString(wr);
                        res.setText("= " + Integer.toString(wr));
                        break;
                }
                break;
            case 3:
                math = "/";
                while (num1 < 10) {
                    num1 = random30();
                }
                while ((num2 > 10) || (num2 > num1) || (num1 == 0) || (num2 == 0) || ((num1/num2) % 2) != 0 && ((num1/num2) % 3) != 0) {
                    num2 = random30();
                }

                bt3.setText(math);
                bt1.setText(Integer.toString(num1));
                bt2.setText(Integer.toString(num2));
                result = Float.toString(num1/num2);
                wr = (num1+num2) + 4;
                switch (rd) {
                    case 0:
                        ans = result;
                        res.setText("= " + result);
                        break;
                    case 1:
                        ans = Integer.toString(wr);
                        res.setText("= " + Integer.toString(wr));
                        break;
                }
                break;
        }

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.equalsIgnoreCase(ans)) {
                    myScore = point + 1;
                    tw.setText(Integer.toString(myScore));
                    SoundUtil.play(FreakingMath.this, SoundUtil.WIN);
                    play(myScore);
                } else {
                    gameLose.sendEmptyMessage(0);
                }
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!result.equalsIgnoreCase(ans)) {
                    myScore = point + 1;
                    tw.setText(Integer.toString(myScore));
                    SoundUtil.play(FreakingMath.this, SoundUtil.WIN);
                    play(myScore);
                } else {
                    gameLose.sendEmptyMessage(0);
                }
            }
        });
    }

    public int random30() {
        Random rd = new Random();
        int num = rd.nextInt(29)+1;
        return num;
    }

    public int randommath(){
        Random rd = new Random();
        int num = rd.nextInt(3);
        return num;
    }

    public int random2() {
        Random rd = new Random();
        int num = rd.nextInt(2);
        return num;
    }

    public int random20() {
        Random rd = new Random();
        int num = rd.nextInt(20);
        return num;
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ans1.setClickable(false);
            ans2.setClickable(false);
            if (finish) {
                return;
            }
            mytimer.stop();
            highScore.setScore(ID.NORMAL_SCORE_FREAKING_MATH, myScore);
            myScore = 0;
            SoundUtil.play(FreakingMath.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(FreakingMath.this, closeDialog);
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
            if (msg.what == 0)
                startActivity(intent);
            if (msg.what == 1){
                startActivity(new Intent(FreakingMath.this,HomeActivity.class));
                finish();
            }

        }
    };

    @Override
    public void onBackPressed() {
        if (mytimer.timer != null) {
            mytimer.timer.cancel();
        }
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }

    protected void onDestroy() {
        if (mytimer.timer != null) {
            mytimer.timer.cancel();
        }
        super.onDestroy();
    }
}
