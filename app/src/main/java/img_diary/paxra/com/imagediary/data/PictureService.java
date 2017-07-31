package img_diary.paxra.com.imagediary.data;


import java.util.List;
import java.util.Map;

import img_diary.paxra.com.imagediary.models.Picture;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by iuriegaitur on 12/30/16.
 */

public interface PictureService {
    @GET("file_images.json")
    Observable<List<String>> getAllPictures();
}
