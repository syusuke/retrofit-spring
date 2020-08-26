package me.yuxiaoyao.retrofit.boot;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author VcKerry on 2020/8/26
 */

public class RetrofitFactoryBean<T> extends RetrofitSupport implements FactoryBean<T> {

    private Class<T> retrofitInterface;

    public RetrofitFactoryBean(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    @Override
    public T getObject() throws Exception {
        return getRetrofit().create(this.retrofitInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.retrofitInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setRetrofitInterface(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public Class<T> getRetrofitInterface() {
        return retrofitInterface;
    }
}
