package vlth.brainbreak;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import java.util.ArrayList;
import vlth.brainbreak.Adapter.ListGameAdapter;
import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class HomeActivity extends AppCompatActivity {

    private final String applink = "https://fb.me/930419380326797";
    private final String image = "https://lh3.googleusercontent.com/ZZPdzvlpK9r_Df9C3M7j1rNRi7hhHRvPhlklJ3lfi5jk86Jd1s0Y5wcQ1QgbVaAP5Q=w300";
    //Games infomation
    private String[] titles = new String[]{"Higer or Lower",
            "Mix Word", "Freaking Math", "Color or Shape", "Find Image", "Number"};
    private int[] cover = new int[]{R.drawable.hl, R.drawable.wm, R.drawable.fm, R.drawable.geo, R.drawable.find, R.drawable.number};
    private String[] tut= new String[]{"1", "2", "3", "4", "5", "6"};

    ListView listView;
    ArrayList<ItemGame> rowItems;
    private Toolbar toolbar;
    private Button invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        TextView toolbarTitle;
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Brain Breaks");

        toolbarTitle.setTypeface(font);

        HighScore highScore = new HighScore(this);
        int[] best_score = {
                highScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, 0),
                highScore.getScore(ID.HIGH_SCORE_MIX_WORD, 0),
                highScore.getScore(ID.HIGH_SCORE_FREAKING_MATH, 0),
                highScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE, 0),
                highScore.getScore(ID.HIGH_SCORE_FIND_IMAGE, 0),
                highScore.getScore(ID.HIGH_SCORE_NUMBER, 0)};


        rowItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            ItemGame item = new ItemGame(titles[i], best_score[i],tut[i], cover[i]);
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.list_game);
        ListGameAdapter adapter = new ListGameAdapter(this, R.layout.game_row, rowItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(HomeActivity.this, HigherOrLower.class));

                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this, MirrorWord.class));

                        break;
                    case 2:
                        startActivity(new Intent(HomeActivity.this, FreakingMath.class));

                        break;
                    case 3:
                        startActivity(new Intent(HomeActivity.this, ColorShape.class));

                        break;
                    case 4:
                        startActivity(new Intent(HomeActivity.this, ImageMemory.class));
                        break;
                    case 5:
                        Log.d("tag", "f");
                        startActivity(new Intent(HomeActivity.this, NumberMemory.class));
                        break;
                }
            }
        });

        //test invite
        FacebookSdk.sdkInitialize(getApplicationContext());
        invite = (Button) findViewById(R.id.invite);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppInviteDialog.canShow()) {
                    AppInviteContent content = new AppInviteContent.Builder()
                            .setApplinkUrl(applink)
                            .setPreviewImageUrl(image)
                            .build();
                    AppInviteDialog.show(HomeActivity.this, content);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
