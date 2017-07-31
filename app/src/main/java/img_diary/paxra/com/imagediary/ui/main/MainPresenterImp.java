package img_diary.paxra.com.imagediary.ui.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import img_diary.paxra.com.imagediary.ImageDiaryApplication;
import img_diary.paxra.com.imagediary.data.PictureService;
import img_diary.paxra.com.imagediary.data.PictureStorage;
import img_diary.paxra.com.imagediary.models.Picture;
import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class MainPresenterImp implements MainPresenter {


    private final MainView mainView;

    @Inject Retrofit retrofit;

    Subscription subsription;

    int randomPicPosition;

    public MainPresenterImp(MainView mainView) {
        this.mainView = mainView;
        ImageDiaryApplication.component.inject(this);
    }


    @Override
    public void loadPictures() {
        if (PictureStorage.pictureList == null) {

            subsription = retrofit.create(PictureService.class).getAllPictures().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(err -> mainView.showError("No internet connection. Please turn on internet"))
                    .doOnSubscribe(() -> Log.d("TAG", "Subscribed"))
                    .subscribe(pictures -> {
                                List<Picture> pictureList = new ArrayList<>();
                                for (int pos = 0 ;pos < pictures.size(); pos++) {
                                    Picture pic = new Picture(pictures.get(pos));
                                    pic.setId(pos);
                                    pictureList.add(pic);
                                }
                                PictureStorage.pictureList = pictureList;
                                this.loadRandomPicture();
                            }, err -> {
                                mainView.showError("No internet connection. Please turn on internet");
                            }
                    );

        }
    }

    @Override
    public void loadRandomPicture() {
        Picture picture = null;

        if (PictureStorage.pictureList != null) {
            Random r = new Random();
            int size = PictureStorage.pictureList.size();
            int randomIndex = r.nextInt(size);
            picture = PictureStorage.pictureList.get(randomIndex);
            //save random pic position in array
            this.randomPicPosition = randomIndex;
        }


        mainView.showPicture(picture);
    }

    @Override
    public void saveSelected(String label) {
        if(PictureStorage.pictureList != null && PictureStorage.pictureList.size() > 0) {
            PictureStorage.pictureList.get(randomPicPosition).setLabel(label);
            mainView.showListView();
        }
    }

    @Override
    public void loadPictureWithID(int position) {
        if (PictureStorage.pictureList != null) {
            Picture picture = PictureStorage.pictureList.get(position);
            mainView.showPicture(picture);
        }
    }
}
