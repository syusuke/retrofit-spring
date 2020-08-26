package me.yuxiaoyao.retrofit.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author VcKerry on 2020/8/26
 */

@ConfigurationProperties(prefix = "okhttp")
public class OkhttpProperties {

    private OkHttpLogging logging;


    public OkHttpLogging getLogging() {
        return logging;
    }

    public void setLogging(OkHttpLogging logging) {
        this.logging = logging;
    }

    @ConfigurationProperties(prefix = "logging")
    public static class OkHttpLogging {
        private String level;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

}
