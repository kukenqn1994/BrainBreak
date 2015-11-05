package vlth.brainbreak.Model;

public class Image {

    private int previousIndex;
    private int currentIndex;
    private int ID;

    public Image(int ID, int index) {
        this.ID = ID;
        this.currentIndex = index;
    }

    public int getPreviousIndex() {
        return previousIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.previousIndex = this.currentIndex;
        this.currentIndex = currentIndex;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
