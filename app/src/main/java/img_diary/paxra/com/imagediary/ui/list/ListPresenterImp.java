package img_diary.paxra.com.imagediary.ui.list;

import java.util.ArrayList;
import java.util.List;

import img_diary.paxra.com.imagediary.data.PictureStorage;
import img_diary.paxra.com.imagediary.models.Picture;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class ListPresenterImp implements ListPresenter {

    private ListView listView;

    public ListPresenterImp(ListView listView) {
        this.listView = listView;
    }


    @Override
    public void loadPictures() {
        List<Picture> pictures = new ArrayList<>();
        if (PictureStorage.pictureList != null) {
            for (Picture picture : PictureStorage.pictureList) {
                if (picture.getLabel() != null && !picture.getLabel().isEmpty()) {
                    pictures.add(picture);
                }
            }
        }

        listView.setImagesList(pictures);

        return;
    }
}
