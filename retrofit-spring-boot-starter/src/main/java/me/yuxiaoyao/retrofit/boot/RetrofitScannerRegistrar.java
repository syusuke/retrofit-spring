package me.yuxiaoyao.retrofit.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author VcKerry on 2020/8/25
 */

public class RetrofitScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitScannerRegistrar.class);

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRetrofit.class.getName()));
        if (attrs == null) {
            logger.debug("spring retrofit scanner is disable. enable retrofit client @EnableRetrofit(@RetrofitScan)");
            return;
        }
        registerBeanDefinitions(importingClassMetadata, attrs, registry);
    }

    void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, AnnotationAttributes attrs, BeanDefinitionRegistry registry) {
        List<String> basePackages = new ArrayList<>();

        basePackages.addAll(Arrays.stream(attrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll(Arrays.stream(attrs.getStringArray("basePackages")).filter(StringUtils::hasText).collect(Collectors.toList()));
        basePackages.addAll(Arrays.stream(attrs.getClassArray("basePackageClasses")).map(ClassUtils::getPackageName).collect(Collectors.toList()));


        if (basePackages.isEmpty()) {
            // make default
            basePackages.add(getDefaultBasePackage(importingClassMetadata));
        }

        RetrofitClassPathScanner scanner = new RetrofitClassPathScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        scanner.setAnnotationClass(RetrofitClient.class);
        scanner.registerFilters();
        scanner.doScan(basePackages.toArray(new String[0]));
    }


    private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        return ClassUtils.getPackageName(importingClassMetadata.getClassName());
    }


}
