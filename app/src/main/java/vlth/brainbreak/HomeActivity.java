package vlth.brainbreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vlth.brainbreak.Adapter.ListGameAdapter;
import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class HomeActivity extends AppCompatActivity {
    public static final String[] titles = new String[] { "Higer or Lower",
            "Mix Word", "Freaking Math", "Color or Shape" };

    public static int[] icon = {R.drawable.hl, R.drawable.wm, R.drawable.fm, R.drawable.geo};

    ListView listView;
    List<ItemGame> rowItems;
    private HighScore highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        highScore=new HighScore(this);


        int[] best_score={highScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER,0),
                highScore.getScore(ID.HIGH_SCORE_MIX_WORD,0),
                highScore.getScore(ID.HIGH_SCORE_FREAKING_MATH,0),
                highScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE,0) };

        rowItems = new ArrayList<ItemGame>();
        for (int i = 0; i < titles.length; i++) {
            ItemGame item = new ItemGame(titles[i], best_score[i]);
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.list_game);
        ListGameAdapter adapter = new ListGameAdapter(this, R.layout.game_row, rowItems, icon);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(HomeActivity.this, HigherOrLower.class));
                        finish();
                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this, MixWord.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(HomeActivity.this, FreakingMath.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(HomeActivity.this, ColorShape.class));
                        finish();
                        break;
                }
            }
        });

    }

    private void score(){


    }
}
