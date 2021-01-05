package com.aking.learn.anno;


import java.lang.annotation.*;

/**
 * @author Y10003453
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    /**
     * 是否必须打印返回参数，默认是
     * 语法是方法，但是使用上叫属性----注解的属性
     *
     */
    boolean resultRequired() default true;

    /**
     * 描述
     */
    String description() default "";
}
