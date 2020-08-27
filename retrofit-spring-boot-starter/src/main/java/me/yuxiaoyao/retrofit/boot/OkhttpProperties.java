package me.yuxiaoyao.retrofit.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author VcKerry on 2020/8/26
 */

@ConfigurationProperties(prefix = "okhttp")
public class OkhttpProperties {

    private Duration callTimeout = Duration.ofMillis(0L);
    private Duration connectTimeout = Duration.ofSeconds(10L);
    private Duration readTimeout = Duration.ofSeconds(10L);
    private Duration writeTimeout = Duration.ofSeconds(10L);

    private OkHttpLogging logging;
    private Pool pool;


    public Duration getCallTimeout() {
        return callTimeout;
    }

    public void setCallTimeout(Duration callTimeout) {
        this.callTimeout = callTimeout;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Duration getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
    }


    public OkHttpLogging getLogging() {
        return logging;
    }

    public void setLogging(OkHttpLogging logging) {
        this.logging = logging;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
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


    @ConfigurationProperties(prefix = "pool")
    public static class Pool {
        private Integer maxIdleConnections;
        private Duration keepAlive;

        public Integer getMaxIdleConnections() {
            return maxIdleConnections;
        }

        public void setMaxIdleConnections(Integer maxIdleConnections) {
            this.maxIdleConnections = maxIdleConnections;
        }

        public Duration getKeepAlive() {
            return keepAlive;
        }

        public void setKeepAlive(Duration keepAlive) {
            this.keepAlive = keepAlive;
        }
    }

}
