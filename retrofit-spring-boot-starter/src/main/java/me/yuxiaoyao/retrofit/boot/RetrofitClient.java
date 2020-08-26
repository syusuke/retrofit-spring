package me.yuxiaoyao.retrofit.boot;

import java.lang.annotation.*;

/**
 * @author VcKerry on 2020/8/25
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RetrofitClient {
    /**
     * group
     *
     * @return
     */
    String value() default "";
}
