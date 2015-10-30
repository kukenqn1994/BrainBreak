package vlth.brainbreak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog;
import com.facebook.share.Sharer;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import vlth.brainbreak.Adapter.ListGameAdapter;
import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class HomeActivity extends AppCompatActivity {
    public static final String[] titles = new String[]{"Higer or Lower",
            "Mix Word", "Freaking Math", "Color or Shape"};
//    public static final String[] titles = new String[] { "Higer or Lower",
//            "Mix Word", "Freaking Math", "Color or Shape", "Find Image"};

//    public static int[] icon = {R.drawable.hl, R.drawable.wm, R.drawable.fm, R.drawable.geo, R.drawable.find};
    public static int[] icon = {R.drawable.hl, R.drawable.wm, R.drawable.fm, R.drawable.geo};
    ListView listView;
    List<ItemGame> rowItems;
    private HighScore highScore;
    private Button btInvite, btLogin;
    private WebDialog dialog = null;
    private String dialogAction = null;
    private Bundle dialogParams = null;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    LinearLayout ll;
    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
//        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//            ...});
        setContentView(R.layout.activity_home);

        btInvite = (Button) findViewById(R.id.btInvite);
        btLogin = (Button) findViewById(R.id.btLogin);
        btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appLinkUrl, previewImageUrl;

                appLinkUrl = "https://fb.me/787851144692434";
                previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";

                AppInviteContent content = new AppInviteContent.Builder()
                        .setApplinkUrl(appLinkUrl)
                        .setPreviewImageUrl(previewImageUrl)
                        .build();
                AppInviteDialog.show(HomeActivity.this, content);

//                startActivity(new Intent(HomeActivity.this,InviteFriendsActivity.class));

//                SharePhoto photo = new SharePhoto.Builder()
//                        .setBitmap(myBitmap)
//                        .build();
//                SharePhotoContent content = new SharePhotoContent.Builder()
//                        .addPhoto(photo)
//                        .build();
//                ShareDialog.show(HomeActivity.this, content);


            }
        });


        highScore = new HighScore(this);
//        int[] best_score = {highScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, 0),
//                highScore.getScore(ID.HIGH_SCORE_MIX_WORD, 0),
//                highScore.getScore(ID.HIGH_SCORE_FREAKING_MATH, 0),
//                highScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE, 0)};
        int[] best_score={highScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER,0),
                highScore.getScore(ID.HIGH_SCORE_MIX_WORD,0),
                highScore.getScore(ID.HIGH_SCORE_FREAKING_MATH,0),
                highScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE,0)};

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
                switch (position) {
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
//                    case 4:
//                        startActivity(new Intent(HomeActivity.this, NumberMemory.class));
                }
            }
        });
        ll=(LinearLayout)findViewById(R.id.home_layout);
        ll.post(new Runnable() {
            @Override
            public void run() {
                myBitmap=captureScreen(ll);
                Toast.makeText(getApplicationContext(), "Screenshot captured..!", Toast.LENGTH_LONG).show();
                try {

                    if(myBitmap!=null){
                        //save image to SD card
                        saveImage(myBitmap);
                        Toast.makeText(getApplicationContext(), "Screenshot saved..!", Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


    }
    public Bitmap captureScreen(View v) {
        Display display = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        Bitmap screenshot = null;
        try {
            if(v!=null) {

                screenshot = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(screenshot);
                v.draw(canvas);
            }

        }catch (Exception e){
            Log.d("ScreenShotActivity", "Failed to capture screenshot because:" + e.getMessage());
        }

        return screenshot;
    }

    public static void saveImage(Bitmap bitmap) throws IOException {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "test.png");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
