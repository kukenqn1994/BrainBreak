package vlth.brainbreak;

import android.graphics.Typeface;
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
import vlth.brainbreak.Util.MyTimer;

public class ImageMemory extends AppCompatActivity {

    private TextView requestView;
    private String request;
    private Toolbar toolbar;

    private ArrayList<Integer> listImage;
    private ArrayList<ImageView> listView;
    private ImageView imgView1, imgView2, imgView3, imgView4;
    private Random r;
    LinearLayout mainView;
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
        TextView toolsbarTitle;
        toolsbarTitle = (TextView) findViewById(R.id.toolbar_title);
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
        setResRandom();
    }

    private void setResRandom() {
        int n;
        for (int i = 3; i >= 0; i --) {
            n = r.nextInt(i + 1);
            listView.get(i).setImageResource(listImage.get(n));
            listImage.remove(n);
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

    public void setRequest(String request) {
        this.request = request;
    }
}
