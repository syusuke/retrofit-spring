# Retrofit With Spring

## 实现原理
[参考Mybatis Springboot starter](https://github.com/mybatis/spring-boot-starter)


## Maven
```xml
<dependency>
    <groupId>me.yuxiaoyao.retrofit.boot</groupId>
    <artifactId>retrofit-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
## config
```yaml
retrofit:
  base-url: http://127.0.0.1:8080/
okhttp:
  logging:
    level: BODY
```