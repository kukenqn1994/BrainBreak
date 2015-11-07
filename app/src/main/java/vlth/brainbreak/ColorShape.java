package vlth.brainbreak;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

import java.util.Random;

import vlth.brainbreak.Library.NumberProgressBar;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.MyTimer;
import vlth.brainbreak.Util.SoundUtil;

public class ColorShape extends AppCompatActivity {

    private NumberProgressBar prog;
    private TextView tw1, tw2;
    private TextView point;
    private ImageView im1, im2;
    private TextView info, info1, info2;

    public static int[] sqr = {R.drawable.square_green, R.drawable.square_yellow, R.drawable.square_red};
    public static int[] tri = {R.drawable.triangle_green, R.drawable.triangle_yellow, R.drawable.triangle_red};
    public static int[] cir = {R.drawable.circle_green, R.drawable.circle_yellow, R.drawable.circle_red};

    String[] type;
    String[] shape;

    String choose1, choose2, result;
    int rd;

    private boolean finish = false;
    private HighScore highScore;
    private int myScore = 0;
    // Them vao
    private ImageButton fabtn;
    private LinearLayout mainView;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private MyTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shape);

        prog = (NumberProgressBar) findViewById(R.id.proTimer);
        tw1 = (TextView) findViewById(R.id.tw1);
        tw2 = (TextView) findViewById(R.id.tw2);

        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);

        point = (TextView) findViewById(R.id.point);

        type = getResources().getStringArray(R.array.cs);
        shape = getResources().getStringArray(R.array.shape);

        // Them vao
        fabtn = (ImageButton) findViewById(R.id.fab);
        mainView = (LinearLayout) findViewById(R.id.ColorShape);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Color Shape");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolbarTitle.setTypeface(fonts, Typeface.BOLD);
        timer = new MyTimer(1500);
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.setVisibility(View.VISIBLE);
                fabtn.setVisibility(View.GONE);
                info.setVisibility(View.GONE);
                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);

                im1.setClickable(true);
                im2.setClickable(true);

                point.setText("");
                play(timer, myScore);
            }
        });


        info = (TextView) findViewById(R.id.info);
        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info.setTypeface(fonts);
        info1.setTypeface(fonts);
        info2.setTypeface(fonts);

        info1.setText(R.string.info_cs1);
        info2.setText(R.string.info_cs2);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ColorShape.this, HomeActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_shape, menu);
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

    public void play(final MyTimer timer, final int pnt) {
        int num1, num2, num3;
        num1 = randomtype();
        num2 = random();
        num3 = random();

        timer.setOnTickHtmlListener(gameLose);
        timer.setID(prog);
        timer.tick();

        rd = random2();
        switch (rd) {
            case 0:
                rdim(im1, im2, num1, num2, num3);
                break;
            case 1:
                rdim(im2, im1, num1, num2, num3);
                break;
        }

        if (num1 % 2 == 0) {
            tw1.setText(type[0] + " :");
            switch (num3) {
                case 0:
                    result = "green";
                    break;
                case 1:
                    result = "yellow";
                    break;
                case 2:
                    result = "red";
                    break;
            }
        } else {
            tw1.setText(type[1] + " :");
            switch (num2) {
                case 0:
                    result = "square";
                    break;
                case 1:
                    result = "triangle";
                    break;
                case 2:
                    result = "circle";
                    break;
            }
        }

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rd) {
                    case 0:
                        if (choose1.contains(result)) {
                            myScore = pnt + 1;
                            point.setText(Integer.toString(myScore));
                            play(timer, myScore);
                        } else {
                            gameLose.sendEmptyMessage(0);
                        }
                        break;
                    case 1:
                        if (choose2.contains(result)) {
                            myScore = pnt + 1;
                            point.setText(Integer.toString(myScore));
                            play(timer, myScore);
                        } else {
                            gameLose.sendEmptyMessage(0);
                        }
                        break;
                }
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rd) {
                    case 0:
                        if (choose2.contains(result)) {
                            myScore = pnt + 1;
                            point.setText(Integer.toString(myScore));
                            play(timer, myScore);
                        } else {
                            gameLose.sendEmptyMessage(0);
                        }
                        break;
                    case 1:
                        if (choose1.contains(result)) {
                            myScore = pnt + 1;
                            point.setText(Integer.toString(myScore));
                            play(timer, myScore);
                        } else {
                            gameLose.sendEmptyMessage(0);
                        }
                        break;
                }
            }
        });

    }
    private Handler gameLose = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            im1.setClickable(false);
            im2.setClickable(false);
            if (finish) {
                return;
            }
            timer.stop();
            highScore.setScore(ID.NORMAL_SCORE_COLOR_SHAPE, myScore);
            myScore = 0;
            SoundUtil.play(ColorShape.this, SoundUtil.DIE);
            EndDialog endDialog = new EndDialog(ColorShape.this, closeDialog);
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
            if (msg.what == 1)
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    };

    public int randomtype() {
        Random rd = new Random();
        int num = rd.nextInt(100);
        return num;
    }

    public int random() {
        Random rd = new Random();
        int num = rd.nextInt(3);
        return num;
    }

    public int random2() {
        Random rd = new Random();
        int num = rd.nextInt(2);
        return num;
    }

    public void btnback(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        finish();;
        startActivity(intent);
    }

    public void rdim (ImageView im1, ImageView im2, int num1, int num2, int num3) {
        int type = sqr[random()];
        int ck;

        switch (num2) {
            case 0:
                tw2.setText(shape[0]);
                type = sqr[random()];

                switch (num3) {
                    case 0:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                    case 1:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                    case 2:
                        ck = sqr[num3];
                        while (type == ck) {
                            type = sqr[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (sqr[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "square_green";
                                break;
                            case 1:
                                choose1 = "square_yellow";
                                break;
                            case 2:
                                choose1 = "square_red";
                                break;
                        }
                    }
                }
                break;
            case 1:
                tw2.setText(shape[1]);
                type = tri[random()];

                switch (num3) {
                    case 0:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                    case 1:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                    case 2:
                        ck = tri[num3];
                        while (type == ck) {
                            type = tri[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (tri[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "triangle_green";
                                break;
                            case 1:
                                choose1 = "triangle_yellow";
                                break;
                            case 2:
                                choose1 = "triangle_red";
                                break;
                        }
                    }
                }
                break;
            case 2:
                tw2.setText(shape[2]);
                type = cir[random()];

                switch (num3) {
                    case 0:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                    case 1:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                    case 2:
                        ck = cir[num3];
                        while (type == ck) {
                            type = cir[random()];
                        }
                        break;
                }

                im1.setImageResource(type);
                for (int i = 0; i<3; i++) {
                    if (cir[i] == type) {
                        switch (i) {
                            case 0:
                                choose1 = "circle_green";
                                break;
                            case 1:
                                choose1 = "circle_yellow";
                                break;
                            case 2:
                                choose1 = "circle_red";
                                break;
                        }
                    }
                }
                break;
        }

        int rd = random2();
        switch (num3) {
            case 0:
                tw2.setTextColor(Color.parseColor("#16832f"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_green";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_green";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_green";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_green";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_green";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_green";
                                break;
                        }
                        break;
                }
                break;
            case 1:
                tw2.setTextColor(Color.parseColor("#f8e100"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle__yellow";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_yellow";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_yellow";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_yellow";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_yellow";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_yellow";
                                break;
                        }
                        break;
                }
                break;
            case 2:
                tw2.setTextColor(Color.parseColor("#FF0000"));
                switch (num2) {
                    case 0:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_red";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_red";
                                break;
                        }
                        break;
                    case 1:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_red";
                                break;
                            case 1:
                                im2.setImageResource(cir[num3]);
                                choose2 = "circle_red";
                                break;
                        }
                        break;
                    case 2:
                        switch (rd) {
                            case 0:
                                im2.setImageResource(tri[num3]);
                                choose2 = "triangle_red";
                                break;
                            case 1:
                                im2.setImageResource(sqr[num3]);
                                choose2 = "square_red";
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (timer.timer != null) {
            timer.timer.cancel();
        }
//        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    protected void onDestroy() {
        if (timer.timer != null) {
            timer.timer.cancel();
        }
        super.onDestroy();
    }
}
