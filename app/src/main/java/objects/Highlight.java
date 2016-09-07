package objects;

/**
 * Created by gorzkm01 on 07/09/2016.
 */
public class Highlight {
    private int id;
    private String title, subtitle, masterBrand;

    public Highlight(int id, String title, String subtitle, String masterBrand) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.masterBrand = masterBrand;
    }

    public void setHighlightId(int value) {
        this.id = value;
    }

    public int getHighlightId() {
        return this.id;
    }

    public void setHighlightTitle(String value) {
        this.title = value;
    }

    public String getHighlightTitle() {
        return this.title;
    }

    public void setHighlightSubtitle(String value) {
        this.subtitle = value;
    }

    public String getHighlightSubtitle() {
        return this.subtitle;
    }

    public void setHighlightMasterBrand(String value) {
        this.masterBrand = value;
    }

    public String getHighlightMasterBrand() {
        return this.masterBrand;
    }
}
