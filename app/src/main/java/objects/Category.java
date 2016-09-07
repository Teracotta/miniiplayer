package objects;

/**
 * Created by gorzkm01 on 07/09/2016.
 */
public class Category {
    private int id;
    private String title;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setCategoryId(int value) {
        this.id = value;
    }

    public int getCategoryId() {
        return this.id;
    }

    public void setCategoryTitle(String value) {
        this.title = value;
    }

    public String getCategoryTitle()
    {
        return this.title;
    }
}
