package vlth.brainbreak;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.format.DateUtils;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;
import vlth.brainbreak.Util.RainbowText;

public class EndDialog extends Dialog {

    private ShareDialog shareDialog;
    private CallbackManager callbackManager;
    private int current_score = 0;
    private int best_score = 0;
    private TextView mTvYourMove, mTvYourBest,mTvTitle;
    ImageButton share, replay, home;
    Context context;
    private int mode = -1;
    private Bitmap bmp;
    private TextView newHighscore;
    private ScaleAnimation scaleAnimation;
    private String title="";
    public EndDialog(final Context context, final Handler handler) {
        super(context);
        this.context = context;

        FacebookSdk.sdkInitialize(context.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog((Activity) context);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.end_dialog);


        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Comic.ttf");
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.root);
        newHighscore = (TextView) this.findViewById(R.id.newHighScore);
        scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this.context, R.anim.scale_anim);
        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this.context, R.anim.rotate_anim);
        mTvYourMove = (TextView) this.findViewById(R.id.yourMove);
        mTvYourBest = (TextView) this.findViewById(R.id.yourBest);
        mTvTitle = (TextView) this.findViewById(R.id.gameOver);
        mTvYourBest.setTypeface(font);
        mTvYourMove.setTypeface(font);
        mTvTitle.setTypeface(font);
        newHighscore.setTypeface(font);

        share = (ImageButton) findViewById(R.id.btShare);
        home = (ImageButton) findViewById(R.id.btHome);
        replay = (ImageButton) findViewById(R.id.btReplay);

        RainbowText rainbowText = new RainbowText(context,mTvYourMove);
        rainbowText.startAnimation(mTvYourMove.getText().toString());

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

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

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(takeScreenshot())
                            .build();
                    SharePhotoContent content = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();
                    ShareDialog.show((Activity) context, content);
                }
            }
        });

        if (context instanceof HigherOrLower) {
            title="Higher or Lower";
            current_score = HighScore.getScore(ID.NORMAL_SCORE_HIGHER_OR_LOWER, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, current_score);
                mTvYourMove.setText("New High Score: "+current_score);
                rainbowText.startAnimation(mTvYourMove.getText().toString());

            }
        }
        if (context instanceof MirrorWord) {
            title="Mirror Word";
            current_score = HighScore.getScore(ID.NORMAL_SCORE_MIX_WORD, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_MIX_WORD, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_MIX_WORD, current_score);
                newHighscore.setText("New score: " + String.valueOf(current_score));
                newHighscore.setVisibility(View.VISIBLE);
                newHighscore.startAnimation(scaleAnimation);
                new CountDownTimer(10000, 1000) {

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {
                        newHighscore.startAnimation(scaleAnimation);
                    }
                }.start();
            }
        }
        if (context instanceof FreakingMath) {
            title="Freaking Math";
            current_score = HighScore.getScore(ID.NORMAL_SCORE_FREAKING_MATH, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_FREAKING_MATH, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_FREAKING_MATH, current_score);
                newHighscore.setText("New score: " + String.valueOf(current_score));
                newHighscore.setVisibility(View.VISIBLE);
                new CountDownTimer(10000, 1000) {

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {
                        newHighscore.startAnimation(scaleAnimation);
                    }
                }.start();
            }
        }
        if (context instanceof ColorShape) {
            title="Color and Shape";
            current_score = HighScore.getScore(ID.NORMAL_SCORE_COLOR_SHAPE, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_COLOR_SHAPE, current_score);
                newHighscore.setText("New score: " + String.valueOf(current_score));
                newHighscore.setVisibility(View.VISIBLE);
                new CountDownTimer(10000, 1000) {

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {
                        newHighscore.startAnimation(scaleAnimation);
                    }
                }.start();

            }
        }
        if (context instanceof ImageMemory) {
            title="Find Image";
            current_score = HighScore.getScore(ID.NORMAL_SCORE_FIND_IMAGE, 0);
            best_score = HighScore.getScore(ID.HIGH_SCORE_FIND_IMAGE, 0);
            if (current_score > best_score) {
                HighScore.setScore(ID.HIGH_SCORE_FIND_IMAGE, current_score);
                newHighscore.setText("New score: " + String.valueOf(current_score));
                newHighscore.setVisibility(View.VISIBLE);
                new CountDownTimer(10000, 1000) {

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {
                        newHighscore.startAnimation(scaleAnimation);
                    }
                }.start();
            }
        }
        mTvYourMove.setText("Your Score: " + current_score);
        mTvYourBest.setText("Best Score: " + best_score);
        mTvTitle.setText(title);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mode = 1;
        EndDialog.this.dismiss();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
}

