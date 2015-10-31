package vlth.brainbreak.Util;

/**
 * Created by Administrator on 10/16/2015.
 */

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;

import vlth.brainbreak.Library.NumberProgressBar;

public class MyTimer extends Activity {


    private int miliSecond;
    private Handler handler;
    private NumberProgressBar progressBar;
    public CountDownTimer timer;

    public void setOnTickHtmlListener(Handler handler) {
        this.handler = handler;
    }

    public void setID(NumberProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public MyTimer(int ms) {
        this.miliSecond = ms;
    }

    public void tick() {

        progressBar.setMax(miliSecond);
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(miliSecond, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                progressBar.setProgress((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.start();
    }
    public void stop(){
        if (timer != null) {
            timer.cancel();
        }
    }

}
