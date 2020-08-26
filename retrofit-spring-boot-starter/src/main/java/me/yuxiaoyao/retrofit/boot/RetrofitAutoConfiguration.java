package me.yuxiaoyao.retrofit.boot;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

/**
 * @author VcKerry on 2020/8/26
 */


@Configuration
@ConditionalOnClass(Retrofit.class)
public class RetrofitAutoConfiguration {


    @ConditionalOnMissingBean
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();



        return builder;
    }

    @ConditionalOnMissingBean
    @Bean
    public Retrofit.Builder retrofitBuilder(OkHttpClient.Builder okHttpClientBuilder) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClientBuilder.build());
        return builder;
    }


    @Configuration
    @ConditionalOnClass({OkhttpProperties.class, HttpLoggingInterceptor.class})
    public static class OkHttpLoggingConfiguration {

        @ConditionalOnMissingBean
        @Bean
        public HttpLoggingInterceptor httpLoggingInterceptor(OkhttpProperties properties) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            String level = properties.getLogging().getLevel();


            for (HttpLoggingInterceptor.Level value : HttpLoggingInterceptor.Level.values()) {
                if (value.name().equalsIgnoreCase(level)) {
                    httpLoggingInterceptor.setLevel(value);
                }
            }

            return httpLoggingInterceptor;
        }


    }


}
