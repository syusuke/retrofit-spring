package me.yuxiaoyao.retrofit.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author VcKerry on 2020/8/26
 */

@ConfigurationProperties(prefix = "retrofit")
public class RetrofitProperties {
    private String baseUrl;


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
