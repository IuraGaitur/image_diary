package img_diary.paxra.com.imagediary.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iuriegaitur on 7/31/17.
 */
@Table(name = "picture")
public class Picture extends Model implements Serializable{
    @Column(name = "path")
    private String path;
    @Column(name = "label")
    private String label;

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

    public Picture() {}

    public Picture(String path) {
        this.path = path;
    }

    public void add() {
        this.save();
    }

    public List<Picture> getAll() {
        List<Picture> pictureList = new Select().from(Picture.class).execute();
        return pictureList;
    }

    public Picture getByID(long id) {
        Picture picture = new Select().from(Picture.class).where("id = " + id).executeSingle();
        return picture;
    }

}
