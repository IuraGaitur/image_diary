package img_diary.paxra.com.imagediary.ui.main;

import img_diary.paxra.com.imagediary.models.Picture;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public interface MainPresenter {

    void loadPictures();

    void loadRandomPicture();

    void saveSelected(String pictureName);

    void loadPictureWithID(int position);
}

