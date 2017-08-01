package img_diary.paxra.com.imagediary.ui.splash;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import img_diary.paxra.com.imagediary.R;
import img_diary.paxra.com.imagediary.custom_views.InfoDialog;
import img_diary.paxra.com.imagediary.models.Picture;
import img_diary.paxra.com.imagediary.ui.list.ListActivity;
import img_diary.paxra.com.imagediary.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {


    public static final String TAG_ITEM = "item";

    public static final String TAG_HAS_PICS = "has_pics";

    SplashPresenter splashPresenter;

    @Bind(R.id.main_layout) RelativeLayout mMainRlvView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter = new SplashPresenterImp(this);
        ButterKnife.bind(this);
        splashPresenter.loadPictures();

    }

    @Override
    public void showMainView() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



    @Override
    public Context getSplashContext() {
        return getApplicationContext();
    }

    @Override
    public void showError() {
        Snackbar.make(mMainRlvView, "No internet connection", 2000).show();
    }

}
