package img_diary.paxra.com.imagediary.ui.list;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import img_diary.paxra.com.imagediary.R;
import img_diary.paxra.com.imagediary.models.Picture;

/**
 * Created by iuriegaitur on 7/31/17.
 */

public class ListAdapter extends ArrayAdapter<Picture> {
    List<Picture> pictures;

    public ListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Picture> objects) {
        super(context, resource, objects);
        pictures = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_pic, null);
        }

        ((TextView)view.findViewById(R.id.label)).setText(pictures.get(position).getLabel());
        ((TextView)view.findViewById(R.id.number)).setText("Picture Nr. " + (position + 1));
        ImageView mPicImgView = ((ImageView) view.findViewById(R.id.img_pic));
        Glide.with(getContext()).load(pictures.get(position).getPath())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mPicImgView);

        return view;
    }
}
