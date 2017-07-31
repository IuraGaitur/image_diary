package img_diary.paxra.com.imagediary.data;

import android.app.Application;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import img_diary.paxra.com.imagediary.ImageDiaryApplication;

/**
 * Created by iuriegaitur on 1/3/17.
 */

@Module
public class AppModule {

    private ImageDiaryApplication app;

    public AppModule(ImageDiaryApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplicationContext() {
        return app;
    }
}
