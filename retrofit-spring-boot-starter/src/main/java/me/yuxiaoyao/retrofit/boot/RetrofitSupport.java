package me.yuxiaoyao.retrofit.boot;

import retrofit2.Retrofit;

/**
 * @author VcKerry on 2020/8/26
 */

public class RetrofitSupport {

    private Retrofit retrofit;


    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    /**
     * get retrofit
     *
     * @return not null
     */
    protected Retrofit getRetrofit() {
        return retrofit;
    }

}
