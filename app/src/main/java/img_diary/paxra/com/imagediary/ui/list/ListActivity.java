package img_diary.paxra.com.imagediary.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import img_diary.paxra.com.imagediary.R;
import img_diary.paxra.com.imagediary.models.Picture;
import img_diary.paxra.com.imagediary.ui.main.MainActivity;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class ListActivity extends AppCompatActivity implements ListView, AdapterView.OnItemClickListener{

    @Bind(R.id.list_photos) android.widget.ListView mListPics;

    ListPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new ListPresenterImp(this);
        ButterKnife.bind(this);
        mListPics.setOnItemClickListener(this);
        presenter.loadPictures();
    }

    public void setImagesList(List<Picture> pictureList) {
        ListAdapter adapter = new ListAdapter(this, R.layout.item_pic, pictureList);
        mListPics.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, MainActivity.class);
        Picture picture = (Picture) mListPics.getAdapter().getItem(position);
        intent.putExtra(MainActivity.TAG_ITEM, picture);
        startActivity(intent);
    }
}
