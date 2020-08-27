package me.yuxiaoyao.retrofit.boot.test.retrofit;

import me.yuxiaoyao.retrofit.boot.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author VcKerry on 2020/8/26
 */

@RetrofitClient
public interface TestClient {

    /**
     * call index
     *
     * @return
     */
    @GET("/date")
    Call<ResponseBody> currentDate();

}
