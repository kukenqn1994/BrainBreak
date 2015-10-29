package vlth.brainbreak;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vlth.brainbreak.Util.HighScore;
import vlth.brainbreak.Util.ID;

public class EndDialog extends Dialog {

    private RelativeLayout root;
    private int current_score = 0;
    private int best_score = 0;
    private TextView mTvYourMove, mTvYourBest;

    public EndDialog(Context context, final Handler handler) {
        super(context);
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.end_dialog);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_dialog);

        mTvYourMove = (TextView) this.findViewById(R.id.yourMove);
        mTvYourBest = (TextView) this.findViewById(R.id.yourBest);

        root = (RelativeLayout) findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EndDialog.this.dismiss();
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub
                handler.sendEmptyMessage(0);
            }
        });

        if(context instanceof HigherOrLower){
            current_score= HighScore.getScore(ID.NORMAL_SCORE_HIGHER_OR_LOWER,0);
            best_score=HighScore.getScore(ID.HIGH_SCORE_HIGHER_OR_LOWER,0);
            if(current_score>best_score){
                HighScore.setScore(ID.HIGH_SCORE_HIGHER_OR_LOWER, current_score);
            }
        }
        if(context instanceof MixWord){
            current_score= HighScore.getScore(ID.NORMAL_SCORE_MIX_WORD,0);
            best_score=HighScore.getScore(ID.HIGH_SCORE_MIX_WORD,0);
            if(current_score>best_score){
                HighScore.setScore(ID.HIGH_SCORE_MIX_WORD, current_score);
            }
        }
        if(context instanceof FreakingMath){
            current_score= HighScore.getScore(ID.NORMAL_SCORE_FREAKING_MATH,0);
            best_score=HighScore.getScore(ID.HIGH_SCORE_FREAKING_MATH,0);
            if(current_score>best_score){
                HighScore.setScore(ID.HIGH_SCORE_FREAKING_MATH, current_score);
            }
        }
        if(context instanceof ColorShape){
            current_score= HighScore.getScore(ID.NORMAL_SCORE_COLOR_SHAPE,0);
            best_score=HighScore.getScore(ID.HIGH_SCORE_COLOR_SHAPE,0);
            if(current_score>best_score){
                HighScore.setScore(ID.HIGH_SCORE_COLOR_SHAPE, current_score);
            }
        }
        mTvYourMove.setText("YOUR SCORE: " + current_score);
        mTvYourBest.setText("BEST SCORE: " + best_score);
    }


}
