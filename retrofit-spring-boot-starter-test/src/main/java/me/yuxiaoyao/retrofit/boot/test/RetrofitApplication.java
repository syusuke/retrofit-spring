package me.yuxiaoyao.retrofit.boot.test;

import me.yuxiaoyao.retrofit.boot.EnableRetrofit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author VcKerry on 2020/8/26
 */


@EnableRetrofit
@SpringBootApplication
public class RetrofitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetrofitApplication.class, args);
    }
}
