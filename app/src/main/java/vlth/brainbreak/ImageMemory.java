package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;
import vlth.brainbreak.Model.Image;

public class ImageMemory extends AppCompatActivity {

    private TextView requestView, scoreView;
    private Toolbar toolbar;
    private int score, currentIndexRequest;
    private ArrayList<Image> listImage;
    private ArrayList<ImageView> listView;
    private ImageView imgView1, imgView2, imgView3, imgView4;
    private Random r;
    private LinearLayout mainView;
    private ImageButton fab;
    private TextView info, info1, info2;
    private MyTimer timer;
    private HighScore highScore;
    private NumberProgressBar progressBar;
    private Image myImage1, myImage2, myImage3, myImage4;
    private final String[] listRequest = {"Tap the first previous Image",
                                          "Tap the second previous Image",
                                          "Tap the thirst previous Image",
                                          "Tap the fourth previous Image"};

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
        myImage1 = new Image(R.drawable.img_gm1, 0);
        myImage2 = new Image(R.drawable.img_gm2, 1);
        myImage3 = new Image(R.drawable.img_gm3, 2);
        myImage4 = new Image(R.drawable.img_gm4, 3);
        listImage = new ArrayList<>();
        listImage.add(myImage1.getCurrentIndex(), myImage1);
        listImage.add(myImage2.getCurrentIndex(), myImage2);
        listImage.add(myImage3.getCurrentIndex(), myImage3);
        listImage.add(myImage4.getCurrentIndex(), myImage4);
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
        toolsbarTitle.setTypeface(fonts,Typeface.BOLD);
        requestView.setTypeface(fonts);
        scoreView.setTypeface(fonts);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageMemory.this, HomeActivity.class));
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

        timer = new MyTimer(7000);
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
                setResRandom();
                new CountDownTimer(2000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        setResRandom();
                        setRandomRequest();
                        timer.tick();
                    }
                }.start();
            }
        });
    }

    public void Click(View v) {
        switch (v.getId()) {
            case R.id.Img1: {
                if (currentIndexRequest == listImage.get(0).getPreviousIndex()) {
                    scoreView.setText("Score: " + String.valueOf(++score));
                    SoundUtil.play(ImageMemory.this, SoundUtil.WIN);
                    setResRandom();
                    setRandomRequest();
                    timer.tick();
                } else {
                    gameLose.sendEmptyMessage(0);
                }
                break;
            }
            case R.id.Img2: {
                if (currentIndexRequest == listImage.get(1).getPreviousIndex()) {
                    scoreView.setText("Score: " + String.valueOf(++score));
                    SoundUtil.play(ImageMemory.this, SoundUtil.WIN);
                    setResRandom();
                    setRandomRequest();
                    timer.tick();
                } else {
                    gameLose.sendEmptyMessage(0);
                }
                break;
            }
            case R.id.Img3: {
                if (currentIndexRequest == listImage.get(2).getPreviousIndex()) {
                    scoreView.setText("Score: " + String.valueOf(++score));
                    SoundUtil.play(ImageMemory.this, SoundUtil.WIN);
                    setResRandom();
                    setRandomRequest();
                    timer.tick();
                } else {
                    gameLose.sendEmptyMessage(0);
                }
                break;
            }
            case R.id.Img4: {
                if (currentIndexRequest == listImage.get(3).getPreviousIndex()) {
                    scoreView.setText("Score: " + String.valueOf(++score));
                    SoundUtil.play(ImageMemory.this, SoundUtil.WIN);
                    setResRandom();
                    setRandomRequest();
                    timer.tick();
                } else {
                    gameLose.sendEmptyMessage(0);
                }
                break;
            }
        }
    }

    private void setResRandom() {
        int n;
        Image currentImage;
        for(int i = 0; i < 4; i++) {
            currentImage = listImage.get(i);
            listImage.remove(i);
            listImage.add(r.nextInt(4), currentImage);
        }
        for(int j = 0; j < 4; j++) {
            currentImage = listImage.get(j);
            currentImage.setCurrentIndex(j);
            listView.get(j).setImageResource(currentImage.getID());
        }
    }

    private void setRandomRequest() {
        int n = r.nextInt(4);
        requestView.setText(listRequest[n]);
        currentIndexRequest = n;
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
            highScore.setScore(ID.NORMAL_SCORE_FIND_IMAGE, score);
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

    @Override
    public void onBackPressed() {
        if (timer.timer != null) {
            timer.timer.cancel();
        }
        finish();
//        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void onDestroy() {
        if (timer.timer != null) {
            timer.timer.cancel();
        }
        super.onDestroy();
    }
}
