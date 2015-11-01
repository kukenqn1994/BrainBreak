package vlth.brainbreak;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;

import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class EndDialog extends Dialog {

    private final ShareDialog shareDialog;
    private final CallbackManager callbackManager;
    private int current_score = 0;
    private int best_score = 0;
    private TextView mTvYourMove, mTvYourBest;
    ImageButton share, replay, home;
    Context context;
    private int mode = -1;

    public EndDialog(final Context context, final Handler handler) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
        FacebookSdk.sdkInitialize(context.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog((Activity) context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.end_dialog);

        LinearLayout layout = (LinearLayout) this.findViewById(R.id.root);

        mTvYourMove = (TextView) this.findViewById(R.id.yourMove);
        mTvYourBest = (TextView) this.findViewById(R.id.yourBest);

        share = (ImageButton) findViewById(R.id.btShare);
        home = (ImageButton) findViewById(R.id.btHome);
        replay = (ImageButton) findViewById(R.id.btReplay);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
                if (mode == 0) {
                    handler.sendEmptyMessage(0);
                }
                if (mode == 1)
                    handler.sendEmptyMessage(1);
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 0;
                EndDialog.this.dismiss();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 1;
                EndDialog.this.dismiss();
            }
        });
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharePhoto photo = new SharePhoto.Builder()
//                        .setBitmap(takeScreenshot())
//                        .build();
//                SharePhotoContent content = new SharePhotoContent.Builder()
//                        .addPhoto(photo)
//                        .build();
//                ShareDialog.show((Activity) context, content);
//            }
//        });

        if (context instanceof HigherOrLower) {
            current_score = HighScore.getScore(ID.NORMAL_SCORE_HIGHER_OR_LOWER, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, current_score);
            }
        }
        if (context instanceof MixWord) {
            current_score = HighScore.getScore(ID.NORMAL_SCORE_MIX_WORD, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_MIX_WORD, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_MIX_WORD, current_score);
            }
        }
        if (context instanceof FreakingMath) {
            current_score = HighScore.getScore(ID.NORMAL_SCORE_FREAKING_MATH, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_FREAKING_MATH, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_FREAKING_MATH, current_score);
            }
        }
        if (context instanceof ColorShape) {
            current_score = HighScore.getScore(ID.NORMAL_SCORE_COLOR_SHAPE, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_COLOR_SHAPE, current_score);
            }
        }
        mTvYourMove.setText("YOUR SCORE: " + current_score);
        mTvYourBest.setText("BEST SCORE: " + best_score);
    }


    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
//
//    public static void store(Bitmap bm, String fileName){
//        final String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
//        File path = new File(dir);
//        if(!path.exists())
//            path.mkdirs();
//        File file = new File(path, fileName);
//        try {
//            FileOutputStream fOut = new FileOutputStream(file);
//            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void shareImage(File file){
//        Uri uri = Uri.fromFile(file);
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("image/*");
//
//        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        getOwnerActivity().startActivity(Intent.createChooser(intent, "Share Screenshot"));
//    }
}
