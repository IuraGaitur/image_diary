package img_diary.paxra.com.imagediary;

import android.app.Application;
import android.util.Log;

import img_diary.paxra.com.imagediary.data.AppComponent;
import img_diary.paxra.com.imagediary.data.AppModule;
import img_diary.paxra.com.imagediary.data.DaggerAppComponent;
import img_diary.paxra.com.imagediary.data.NetworkModule;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class ImageDiaryApplication extends Application{

    public static AppComponent component;
    private String baseURL = "https://gist.githubusercontent.com/IuraGaitur/bca15362a89d27b133bf4a5a6ed3a874/raw/586085c015ea675de8e3807af288374087ed15a5/";

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).networkModule(new NetworkModule(baseURL)).build();
        Log.d("Application starts","Start");
    }

    public AppComponent getComponent() {
        return component;
    }
}
