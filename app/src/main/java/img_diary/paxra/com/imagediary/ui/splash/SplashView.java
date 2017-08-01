package img_diary.paxra.com.imagediary.ui.splash;

import android.content.Context;

import img_diary.paxra.com.imagediary.models.Picture;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public interface SplashView {

    void showMainView();

    Context getSplashContext();

    void showError();
}
