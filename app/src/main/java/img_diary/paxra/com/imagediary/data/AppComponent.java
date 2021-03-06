package img_diary.paxra.com.imagediary.data;


import javax.inject.Singleton;

import dagger.Component;
import img_diary.paxra.com.imagediary.ui.list.ListPresenterImp;
import img_diary.paxra.com.imagediary.ui.main.MainPresenterImp;
import img_diary.paxra.com.imagediary.ui.splash.SplashPresenter;
import img_diary.paxra.com.imagediary.ui.splash.SplashPresenterImp;

/**
 * Created by iuriegaitur on 12/30/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainPresenterImp presenter);
    void inject(ListPresenterImp presenter);
    void inject(SplashPresenterImp presenter);
}
