package me.yuxiaoyao.retrofit.boot;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

/**
 * @author VcKerry on 2020/8/26
 */


@Configuration
@ConditionalOnClass(Retrofit.class)
@EnableConfigurationProperties({RetrofitProperties.class, OkhttpProperties.class, OkhttpProperties.OkHttpLogging.class, OkhttpProperties.Pool.class})
public class RetrofitAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public Retrofit.Builder retrofitBuilder(RetrofitProperties properties) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(properties.getBaseUrl());
        return builder;
    }


    @Bean
    BaseUrlInterceptor baseUrlInterceptor() {
        return new BaseUrlInterceptor();
    }


    @Primary
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder(OkhttpProperties okhttpProperties, BaseUrlInterceptor baseUrlInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.callTimeout(okhttpProperties.getCallTimeout())
                .connectTimeout(okhttpProperties.getConnectTimeout())
                .readTimeout(okhttpProperties.getReadTimeout())
                .writeTimeout(okhttpProperties.getWriteTimeout());

        OkhttpProperties.Pool pool = okhttpProperties.getPool();
        if (pool != null) {
            int maxIdleConnections = 5;
            if (pool.getMaxIdleConnections() != null) {
                maxIdleConnections = pool.getMaxIdleConnections();
            }
            long keepAlive = 5 * 60 * 1000;
            if (pool.getKeepAlive() != null) {
                keepAlive = pool.getKeepAlive().toMillis();
            }
            builder.connectionPool(new ConnectionPool(maxIdleConnections, keepAlive, TimeUnit.MILLISECONDS));
        }
        builder.addInterceptor(baseUrlInterceptor);
        return builder;
    }


    @ConditionalOnClass(HttpLoggingInterceptor.class)
    public static class OkHttpLoggingConfiguration {

        @ConditionalOnMissingBean
        @Bean
        public HttpLoggingInterceptor httpLoggingInterceptor(OkHttpClient.Builder okHttpClientBuilder, OkhttpProperties properties) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            final OkhttpProperties.OkHttpLogging logging = properties.getLogging();
            if (logging == null) {
                return httpLoggingInterceptor;
            }
            String level = logging.getLevel();

            for (HttpLoggingInterceptor.Level value : HttpLoggingInterceptor.Level.values()) {
                if (value.name().equalsIgnoreCase(level)) {
                    httpLoggingInterceptor.setLevel(value);
                    break;
                }
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
            return httpLoggingInterceptor;
        }

    }


    @ConditionalOnMissingBean
    @Bean
    public OkHttpClient okHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @ConditionalOnMissingBean
    @Bean
    public Retrofit retrofit(Retrofit.Builder retrofitBuilder, OkHttpClient okHttpClient) {
        retrofitBuilder.client(okHttpClient);
        return retrofitBuilder.build();
    }

}
