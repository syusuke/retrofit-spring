package me.yuxiaoyao.retrofit.boot.test.controller;

import me.yuxiaoyao.retrofit.boot.test.retrofit.TestClient;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author VcKerry on 2020/8/26
 */

@RestController
public class MainController {

    private final TestClient testClient;

    public MainController(TestClient testClient) {
        this.testClient = testClient;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public Object index() throws IOException {
        Call<ResponseBody> call = testClient.index();
        Response<ResponseBody> execute = call.execute();
        return execute.body().string();
    }
}
