package vlth.brainbreak.Model;

/**
 * Created by Administrator on 10/20/2015.
 */
public class ItemGame {
    public String title;
    public int best_score;
    public int tutorial;
    public int cover;
    public ItemGame(String title,int best_score,int tutorial, int cover){
        this.title=title;
        this.best_score=best_score;
        this.tutorial=tutorial;
        this.cover=cover;
    }

    public int getBest_score() {
        return best_score;
    }

    public String getTitle() {
        return title;
    }

    public int getCover() {
        return cover;
    }

    public int getTutorial() {
        return tutorial;
    }
}
