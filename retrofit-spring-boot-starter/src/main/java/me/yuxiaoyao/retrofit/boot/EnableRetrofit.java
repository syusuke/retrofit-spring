package me.yuxiaoyao.retrofit.boot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author VcKerry on 2020/8/25
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {RetrofitScannerRegistrar.class})
public @interface EnableRetrofit {
    /**
     * base package
     *
     * @return
     */
    String[] value() default {};

    /**
     * base package name
     *
     * @return
     */
    String[] basePackages() default {};

    /**
     * class name with those package
     *
     * @return
     */
    Class<?>[] basePackageClasses() default {};
}
