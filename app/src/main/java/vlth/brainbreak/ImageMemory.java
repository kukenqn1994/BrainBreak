package vlth.brainbreak;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

import vlth.brainbreak.R;

public class ImageMemory extends AppCompatActivity {

    private TextView request;
    private Toolbar toolbar;
    private TextView toolsbarTitle;
    private ArrayList<Integer> listImage;
    private ArrayList<ImageView> listView;
    private ImageView imgView1, imgView2, imgView3, imgView4;
    private Random r;
    LinearLayout mainView;
    private ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_memory);
        request = (TextView) findViewById(R.id.request);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolsbarTitle = (TextView) findViewById(R.id.toolbar_title);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                mainView.setVisibility(View.VISIBLE);
            }
        });
        setResRandom();
    }

    private void setResRandom() {
        Log.d("TAG", "onSetRandom");
        int n;
        for (int i = 3; i >= 0; i --) {
            Log.d("TAG", "sgse");
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
}
