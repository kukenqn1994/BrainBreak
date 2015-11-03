package vlth.brainbreak;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;


public class NumberMemory extends AppCompatActivity{

    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    private ImageButton fab;
    private final Animation animation = new AlphaAnimation(1,0);
    private Toolbar toolbar;
    private TextView toolsbarTitle;
    private ArrayList<Integer> numList;
    private int id = 0;
    private int idOut = 0;
    private LinearLayout mainView;
    private TextView info, info1, info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        bt4 = (Button)findViewById(R.id.bt4);
        bt5 = (Button)findViewById(R.id.bt5);
        bt6 = (Button)findViewById(R.id.bt6);
        bt7 = (Button)findViewById(R.id.bt7);
        bt8 = (Button)findViewById(R.id.bt8);
        bt9 = (Button)findViewById(R.id.bt9);

        fab = (ImageButton) findViewById(R.id.fab);
        numList = new ArrayList<>();
        // Set toolsbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolsbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolsbarTitle.setText("Number");
        Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolsbarTitle.setTypeface(fonts);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ImageMemory.this, HomeActivity.class));
                finish();
            }
        });

        info = (TextView) findViewById(R.id.info);
        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info.setTypeface(fonts);
        info1.setTypeface(fonts);
        info2.setTypeface(fonts);
        mainView = (LinearLayout) findViewById(R.id.NumberMomory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                info.setVisibility(View.GONE);
                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                mainView.setVisibility(View.VISIBLE);
            }
        });

        Log.d("TAG", "gvgser");
        //level tăng dần từ 1 đến 30
        for (int i = 1; i < 30; i++){
            numList.clear();
            for (int j = 1; j <= i; j++){
                Random random = new Random();           //random để chọn nút nháy
                int num = random.nextInt(9)+1;
                switch (num){
                    case 1:
                        setAnimation(bt1);
                        id = R.id.bt1;                  //lấy id của bt được nháy
                        numList.add(id);                //input id vào array
                        break;
                    case 2:
                        setAnimation(bt2);
                        id = R.id.bt2;
                        numList.add(id);
                        break;
                    case 3:
                        setAnimation(bt3);
                        id = R.id.bt3;
                        numList.add(id);
                        break;
                    case 4:
                        setAnimation(bt4);
                        id = R.id.bt4;
                        numList.add(id);
                        break;
                    case 5:
                        setAnimation(bt5);
                        id = R.id.bt5;
                        numList.add(id);
                        break;
                    case 6:
                        setAnimation(bt6);
                        id = R.id.bt6;
                        numList.add(id);
                        break;
                    case 7:
                        setAnimation(bt7);
                        id = R.id.bt7;
                        numList.add(id);
                        break;
                    case 8:
                        setAnimation(bt8);
                        id = R.id.bt8;
                        numList.add(id);
                        break;
                    case 9:
                        setAnimation(bt9);
                        id = R.id.bt9;
                        numList.add(id);
                        break;
                }
            }
            int len = numList.size();
            do {
                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();                             //lấy id của button được nhấn
                    }
                });
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                bt9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idOut = v.getId();
                    }
                });
                for (int k=0; k<len; k++){
                    if (idOut == numList.get(k)){             //so sánh id button được nhấn
                        len--;
                    }
                    else{
                        Toast.makeText(this,"You are wrong!",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            } while (len !=0);
        }

    }

    public void setAnimation (Button bt) {
        animation.setDuration(2000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        //animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        //animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        bt.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_memory, menu);
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

    public void click(View view) {
        switch (view.getId()){
            case R.id.bt1:
                setAnimation(bt1);
                break;
            case R.id.bt2:
                setAnimation(bt2);
                break;
            case R.id.bt3:
                setAnimation(bt3);
                break;
            case R.id.bt4:
                setAnimation(bt4);
                break;
            case R.id.bt5:
                setAnimation(bt5);
                break;
            case R.id.bt6:
                setAnimation(bt6);
                break;
            case R.id.bt7:
                setAnimation(bt7);
                break;
            case R.id.bt8:
                setAnimation(bt8);
                break;
            case R.id.bt9:
                setAnimation(bt9);
                break;
        }
    }
}

