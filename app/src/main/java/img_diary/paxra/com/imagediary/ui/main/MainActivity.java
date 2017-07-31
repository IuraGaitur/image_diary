package img_diary.paxra.com.imagediary.ui.main;

import android.app.Dialog;
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
import img_diary.paxra.com.imagediary.models.Picture;
import img_diary.paxra.com.imagediary.ui.list.ListActivity;
import img_diary.paxra.com.imagediary.custom_views.InfoDialog;

public class MainActivity extends AppCompatActivity implements MainView {


    public static final String TAG_ITEM = "item";

    MainPresenter mainPresenter;

    @Bind(R.id.edit_name) EditText mNameEditView;

    @Bind(R.id.img_pic) ImageView mPicImgView;

    @Bind(R.id.btn_random) Button mRandomBtnView;

    @Bind(R.id.btn_select) Button mSelectBtnView;

    @Bind(R.id.main_layout) RelativeLayout mMainRlvView;

    @Bind(R.id.details_layout) RelativeLayout mDetailsRlvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImp(this);
        ButterKnife.bind(this);
        checkIntentForData(getIntent());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_info:
                showPopup();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopup() {
        Dialog dialog = new InfoDialog(this, true);
        dialog.show();
    }



    @OnClick(R.id.btn_random)
    public void actionRandomClick() {
        mainPresenter.loadRandomPicture();
    }

    @OnClick(R.id.btn_select)
    public void actionSelectClick() {
        String pictureName = mNameEditView.getText().toString();
        mainPresenter.saveSelected(pictureName);
    }

    @Override
    public void showPicture(Picture picture) {
        if(picture != null) {
            Glide.with(this).load(picture.getPath())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPicImgView);
            mNameEditView.setText(picture.getLabel());
        }
    }

    @Override
    public void showListView() {
        startActivity(new Intent(this, ListActivity.class));
    }

    @Override
    public void showError(String message) {
        mDetailsRlvView.setVisibility(View.GONE);
        Snackbar.make(mMainRlvView, message, 2000).show();
    }

    private void checkIntentForData(Intent intent) {
        if(intent.getExtras() != null) {
            int position = intent.getExtras().getInt(TAG_ITEM, -1);

            if(position > -1) {
                mainPresenter.loadPictureWithID(position);
            }else {
                mainPresenter.loadPictures();
            }
        }else {
            mainPresenter.loadPictures();
        }
    }


}
