package jittakan58070012.kmitl.lab07.lazyinstagram;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SKpro on 10/6/2017 AD.
 */

public interface Api {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

}
