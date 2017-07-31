package img_diary.paxra.com.imagediary.ui.main;

import img_diary.paxra.com.imagediary.models.Picture;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public interface MainView {

    void showPicture(Picture picture);
    void showListView();
    void showError(String message);

}
