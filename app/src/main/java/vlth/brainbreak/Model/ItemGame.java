package vlth.brainbreak.Model;

/**
 * Created by Administrator on 10/20/2015.
 */
public class ItemGame {
    public String title;
    public int best_score;
    public ItemGame(String title,int best_score){
        this.title=title;
        this.best_score=best_score;
    }

    public int getBest_score() {
        return best_score;
    }

    public String getTitle() {
        return title;
    }

}
