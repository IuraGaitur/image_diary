package img_diary.paxra.com.imagediary.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

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
            List<Picture> allPics = new Picture().getAll();
            PictureStorage.pictureList = allPics;
            loadRandomPicture();
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
            //save image label
            new PictureSaving().execute(PictureStorage.pictureList.get(randomPicPosition));
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


    private class PictureSaving extends AsyncTask<Picture, Integer, Picture> {


        @Override
        protected Picture doInBackground(Picture... pictures) {
            Picture picture = pictures[0];

            Picture updatePicture = Picture.load(Picture.class, picture.getId());//1 is the id
            updatePicture.setLabel(picture.getLabel());
            updatePicture.save();
            return picture;
        }
    }
}
