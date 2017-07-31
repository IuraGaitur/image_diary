package img_diary.paxra.com.imagediary.models;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class Picture {
    private int id;
    private String path;
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Picture(String path) {
        this.path = path;
    }
}
