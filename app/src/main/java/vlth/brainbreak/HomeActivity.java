package vlth.brainbreak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bolts.AppLinks;
import vlth.brainbreak.Adapter.ListGameAdapter;
import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class HomeActivity extends AppCompatActivity {

    //Games infomation
    private String[] titles = new String[]{"Higer or Lower",
            "Mix Word", "Freaking Math", "Color or Shape", "Find Image"};
    private int[] cover = new int[]{R.drawable.hl, R.drawable.wm, R.drawable.fm, R.drawable.geo, R.drawable.find};
    private String[] tut= new String[]{"1","2","3","4","5"};



    ListView listView;
    List<ItemGame> rowItems;
    private Button btInvite, btLogin;
    private WebDialog dialog = null;
    private String dialogAction = null;
    private Bundle dialogParams = null;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    LinearLayout ll;
    private Bitmap myBitmap;
    //private LoginButton loginButton;
    private final String appLink= "https://fb.me/928178163884252";
    private final String imageLink = "http://ford-life.com/wp-content/uploads/2013/01/ford-sync-applink-ces.jpg";
    private Toolbar toolbar;
    private TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        Uri targetUrl = AppLinks.getTargetUrlFromInboundIntent(HomeActivity.this, getIntent());
        if (targetUrl != null) {
            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
        }
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
//        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//            ...});
        setContentView(R.layout.activity_home);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Comic.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Brain Breaks");

        toolbarTitle.setTypeface(font);

        btInvite = (Button) findViewById(R.id.btInvite);
        btLogin = (Button) findViewById(R.id.btLogin);
        btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String appLinkUrl, previewImageUrl;

//                appLinkUrl = "https://fb.me/787851144692434";
//                previewImageUrl = "http://2.bp.blogspot.com/-99shOruuadw/VQsG2T233sI/AAAAAAAAEi0/noFTxUBh_rg/s1600/appscripts.png";
//
//                AppInviteContent content = new AppInviteContent.Builder()
//                        .setApplinkUrl(appLinkUrl)
//                        .setPreviewImageUrl(previewImageUrl)
//                        .build();
//                AppInviteDialog.show(HomeActivity.this, content);

//                startActivity(new Intent(HomeActivity.this,InviteFriendsActivity.class));

//                SharePhoto photo = new SharePhoto.Builder()
//                        .setBitmap(myBitmap)
//                        .build();
//                SharePhotoContent content = new SharePhotoContent.Builder()
//                        .addPhoto(photo)
//                        .build();
//                ShareDialog.show(HomeActivity.this, content);

//                ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
//                shareButton.setShareContent(content);
//                if (ShareDialog.canShow(ShareLinkContent.class)) {
//                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                            .setContentTitle("Hello Facebook")
//                            .setContentDescription(
//                                    "The 'Hello Facebook' sample  showcases simple Facebook integration")
//                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
//                            .build();
//
//                    shareDialog.show(linkContent);
//                }
            }
        });

        HighScore highScore = new HighScore(this);
        int[] best_score = {
                highScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, 0),
                highScore.getScore(ID.HIGH_SCORE_MIX_WORD, 0),
                highScore.getScore(ID.HIGH_SCORE_FREAKING_MATH, 0),
                highScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE, 0),
                highScore.getScore(ID.HIGH_SCORE_FIND_IMAGE, 0)};


        rowItems = new ArrayList<ItemGame>();
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
                }
            }
        });
//        ll = (LinearLayout) findViewById(R.id.home_layout);
//        ll.post(new Runnable() {
//            @Override
//            public void run() {
//                myBitmap = captureScreen(ll);
//                Toast.makeText(getApplicationContext(), "Screenshot captured..!", Toast.LENGTH_LONG).show();
//                try {
//
//                    if (myBitmap != null) {
//                        //save image to SD card
//                        saveImage(myBitmap);
//                        Toast.makeText(getApplicationContext(), "Screenshot saved..!", Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
/*        loginButton=(LoginButton)findViewById(R.id.btLogin);

        loginButton.setReadPermissions("user_friends","public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()

                {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(HomeActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(HomeActivity.this, "Cancel login", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }

        );
*/
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppInviteDialog.canShow()) {
                    AppInviteContent content = new AppInviteContent.Builder()
                            .setApplinkUrl(appLink)
                            .setPreviewImageUrl(imageLink)
                            .build();
                    AppInviteDialog.show(HomeActivity.this, content);
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
            if (v != null) {

                screenshot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(screenshot);
                v.draw(canvas);
            }

        } catch (Exception e) {
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

    // Them vao

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
