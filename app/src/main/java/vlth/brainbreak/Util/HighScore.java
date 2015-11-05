package vlth.brainbreak.Util;

import android.content.Context;
import android.content.SharedPreferences;

import vlth.brainbreak.ColorShape;
import vlth.brainbreak.FreakingMath;
import vlth.brainbreak.HigherOrLower;
import vlth.brainbreak.ImageMemory;
import vlth.brainbreak.MirrorWord;
import vlth.brainbreak.NumberMemory;

public class HighScore {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor edit;
    private static Context context;

    public HighScore(Context context) {
        this.context = context;
    }

    public static void setScore(String key, int val) {
        if (context instanceof MirrorWord) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }
        if (context instanceof HigherOrLower) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }
        if (context instanceof FreakingMath) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }
        if (context instanceof ColorShape) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }

        if (context instanceof ImageMemory) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }

        if (context instanceof NumberMemory) {
            preferences = context.getSharedPreferences(ID.GAME, 0);
        }


        edit = preferences.edit();
        edit = preferences.edit();
        edit.putInt(key, val);
        edit.commit();
    }

    public static int getScore(String key, int defVal) {
        preferences = context.getSharedPreferences(ID.GAME, 0);
        return preferences.getInt(key, defVal);
    }
}
