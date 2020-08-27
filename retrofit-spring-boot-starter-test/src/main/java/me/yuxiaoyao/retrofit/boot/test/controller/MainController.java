package me.yuxiaoyao.retrofit.boot.test.controller;

import me.yuxiaoyao.retrofit.boot.test.retrofit.TestClient;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author VcKerry on 2020/8/26
 */

@RestController
public class MainController {

    @Autowired
    private TestClient testClient;


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public Object requestGetDate() throws IOException {
        Call<ResponseBody> call = testClient.currentDate();
        Response<ResponseBody> execute = call.execute();
        return execute.body().string();
    }

    @RequestMapping(value = "/date", method = {RequestMethod.GET, RequestMethod.POST})
    public Object getDate() {
        Map<String, Object> resp = new HashMap<>();

        resp.put("localDateTime", LocalDateTime.now());
        resp.put("timestamp", System.currentTimeMillis());

        return resp;
    }

}
