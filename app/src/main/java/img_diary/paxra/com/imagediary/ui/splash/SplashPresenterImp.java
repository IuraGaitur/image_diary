package img_diary.paxra.com.imagediary.ui.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import img_diary.paxra.com.imagediary.ImageDiaryApplication;
import img_diary.paxra.com.imagediary.data.PictureService;
import img_diary.paxra.com.imagediary.data.PictureStorage;
import img_diary.paxra.com.imagediary.models.Picture;
import img_diary.paxra.com.imagediary.utils.FileUtils;
import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static img_diary.paxra.com.imagediary.ui.splash.SplashActivity.TAG_HAS_PICS;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class SplashPresenterImp implements SplashPresenter {


    private final SplashView splashView;

    @Inject
    Retrofit retrofit;

    @Inject
    SharedPreferences preferences;

    Subscription subsription;

    private int totalPics = 0;

    private int downloadedImages = 0;

    public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
        ImageDiaryApplication.component.inject(this);
    }


    @Override
    public void loadPictures() {

        boolean hasPicturesLoaded = preferences.getBoolean(TAG_HAS_PICS, false);

        if (!hasPicturesLoaded) {

            subsription = retrofit.create(PictureService.class).getAllPictures().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(err -> Log.d("App", "Error no internet connection"))
                    .doOnSubscribe(() -> Log.d("TAG", "Subscribed"))
                    .subscribe(pictures -> savePictures(pictures), err -> Log.d("App", "Error no internet connection"));
        }else {
            splashView.showMainView();
        }
    }


    public void savePictures(List<String> pictures) {
        this.totalPics = pictures.size();

        List<Picture> pictureList = new ArrayList<>();
        for (int pos = 0; pos < pictures.size(); pos++) {
            String url = pictures.get(pos);
            String name = (pos + 1 + ".jpg");
            Picture pic = new Picture(url);
            pictureList.add(pic);
            //save image local
            saveImageLocally(splashView.getSplashContext(), url, name, pic);
        }
    }


    private void saveImageLocally(Context context, String url, String name, Picture picture) {
        Glide.with(context).load(url)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        GlideBitmapDrawable glideBitmapDrawable = (GlideBitmapDrawable) glideDrawable;
                        Bitmap bitmap = glideBitmapDrawable.getBitmap();
                        String filePath = FileUtils.saveToInternalStorage(context, bitmap, name);

                        //Save pictures in database
                        picture.setPath(filePath);
                        picture.save();
                        downloadedImages++;

                        Log.d("Picture", "Loaded time : " + downloadedImages);

                        if (downloadedImages == totalPics) {

                            preferences.edit().putBoolean(TAG_HAS_PICS, true).commit();
                            splashView.showMainView();

                        }

                    }
                });
    }


}
