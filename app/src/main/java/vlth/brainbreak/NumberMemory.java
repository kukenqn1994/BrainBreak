package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class NumberMemory extends AppCompatActivity {

    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    private Button btnstart;
    private TextView pnt;
    private ImageButton fab;
    private int point = 0;
    private String result = "";
    private String ans = "";
    private Toolbar toolbars;
    private TextView info, info1, info2;
    private LinearLayout mainView;
    final Animation animation = new AlphaAnimation(1,0);
    private Random r;
    private ArrayList<Button> listBtn;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "regs");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory);
        mainView = (LinearLayout) findViewById(R.id.NumberMemory);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        listBtn = new ArrayList<>();
        listBtn.add(bt1);
        listBtn.add(bt2);
        listBtn.add(bt3);
        listBtn.add(bt4);
        listBtn.add(bt5);
        listBtn.add(bt6);
        listBtn.add(bt7);
        listBtn.add(bt8);
        listBtn.add(bt9);
        count = 1;
        pnt = (TextView) findViewById(R.id.point);
        fab = (ImageButton) findViewById(R.id.fab);
        btnstart = (Button) findViewById(R.id.btn_start);
        r = new Random();
        toolbars = (Toolbar) findViewById(R.id.toolbar);
        TextView toolsbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbars);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolsbarTitle.setText("Number Memory");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolsbarTitle.setTypeface(fonts,Typeface.BOLD);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NumberMemory.this, HomeActivity.class));
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                info.setVisibility(View.GONE);
                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                mainView.setVisibility(View.VISIBLE);
                setAllbtn(false);
            }
        });
    }

    private void setAllbtn(boolean enable) {
        bt1.setEnabled(enable);
        bt2.setEnabled(enable);
        bt3.setEnabled(enable);
        bt4.setEnabled(enable);
        bt5.setEnabled(enable);
        bt6.setEnabled(enable);
        bt7.setEnabled(enable);
        bt8.setEnabled(enable);
        bt9.setEnabled(enable);
    }


    public void click(View v) {
        Button btn = (Button) v;
        ans = ans + btn.getText();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_memory, menu);
        return true;
    }

    public void setAnimation (Button bt) {
        setAllbtn(false);
        animation.setDuration(1000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        //animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        bt.startAnimation(animation);
        setAllbtn(true);
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

    public void start(View view) {
        for (int i = 0; i < count; i++) {
            int n = r.nextInt(9);
            setAnimation(listBtn.get(n));
            result += listBtn.get(n).getText();
            SystemClock.sleep(1000);
        }
        count ++;
    }
}
