package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;

public class ImageMemory extends AppCompatActivity {

    private TextView requestView, scoreView;
    private Toolbar toolbar;
    private int score;
    private ArrayList<Integer> listImage;
    private ArrayList<ImageView> listView;
    private ImageView imgView1, imgView2, imgView3, imgView4;
    private Random r;
    private LinearLayout mainView;
    private ImageButton fab;
    private TextView info, info1, info2;
    private MyTimer timer;
    private HighScore highScore;
    private NumberProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_memory);
        requestView = (TextView) findViewById(R.id.request);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolsbarTitle = (TextView) findViewById(R.id.toolbar_title);
        scoreView = (TextView) findViewById(R.id.score);
        progressBar = (NumberProgressBar) findViewById(R.id.proTimer);
        highScore = new HighScore(this);
        imgView1 = (ImageView) findViewById(R.id.Img1);
        imgView2 = (ImageView) findViewById(R.id.Img2);
        imgView3 = (ImageView) findViewById(R.id.Img3);
        imgView4 = (ImageView) findViewById(R.id.Img4);
        listImage = new ArrayList<>();
        listImage.add(R.drawable.bg_dialog);
        listImage.add(R.drawable.black_play);
        listImage.add(R.drawable.circle_red);
        listImage.add(R.drawable.btnplay);
        r = new Random();
        listView = new ArrayList<>();
        listView.add(imgView1);
        listView.add(imgView2);
        listView.add(imgView3);
        listView.add(imgView4);
        mainView = (LinearLayout) findViewById(R.id.ImageMemmory);
        fab = (ImageButton) findViewById(R.id.fab);
        score = 0;
        // add toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolsbarTitle.setText("Find Image");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolsbarTitle.setTypeface(fonts);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ImageMemory.this, HomeActivity.class));
                finish();
            }
        });

        // Set info
        info = (TextView) findViewById(R.id.info);
        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info.setTypeface(fonts);
        info1.setTypeface(fonts);
        info2.setTypeface(fonts);

        info1.setText(R.string.info_fi1);
        info2.setText(R.string.info_fi2);

        timer = new MyTimer(2000);
        timer.setID(progressBar);
        timer.setOnTickHtmlListener(gameLose);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                info.setVisibility(View.GONE);
                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                mainView.setVisibility(View.VISIBLE);
                requestView.setText("...............");
                Log.d("TAG", "vbgerg");
                new CountDownTimer(800, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        setResRandom();
                    }

                    @Override
                    public void onFinish() {
                        setResRandom();
                        Log.d("TAG", "vbgerg");
                        timer.tick();
                    }


                }.start();
            }
        });
    }

    public void CLick(View v) {
        switch (v.getId()) {
            case R.id.Img1: {

            }
            case R.id.Img2: {

            }
            case R.id.Img3: {

            }
            case R.id.Img4: {

            }

        }
    }

    private void Play() {

    }

    private void setResRandom() {
        int n, id;
        for (int i = 3; i >= 0; i --) {
            n = r.nextInt(i + 1);
            id = listImage.get(n);
            listView.get(i).setImageResource(id);
            listImage.remove(n);
            listImage.add(id);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // stop progress bar
            timer.stop();
            highScore.setScore(ID.NORMAL_SCORE_HIGHER_OR_LOWER, score);
            score = 0;
            SoundUtil.play(ImageMemory.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(ImageMemory.this, closeDialog);
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

}
