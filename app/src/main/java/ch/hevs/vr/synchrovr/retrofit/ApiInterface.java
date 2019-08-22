package ch.hevs.vr.synchrovr.retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {


    @Multipart
    @POST("/upload")
    Single<ResponseBody> onFileUpload(@Part("file") RequestBody mFile, @Part MultipartBody.Part file);

}
