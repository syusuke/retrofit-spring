package me.yuxiaoyao.retrofit.boot;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author VcKerry on 2020/8/27
 */

class BaseUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
