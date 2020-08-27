package me.yuxiaoyao.retrofit.boot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import retrofit2.Retrofit;

/**
 * @author VcKerry on 2020/8/26
 */

public class RetrofitSupport implements ApplicationContextAware {

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


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.retrofit = applicationContext.getBean(Retrofit.class);
    }
}
