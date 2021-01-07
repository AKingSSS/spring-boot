package com.aking.learn.utils;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public abstract  class DataConvert<T1, T2, T3> implements InitializingBean {
    private Class<T1> clazzT1;
    private Class<T2> clazzT2;
    private Class<T3> clazzT3;

    public DataConvert() {
    }

    protected T2 convertToT2(T1 t1) {
                                                                                                                                                 T2 t2 = null;

        try {
            t2 = this.clazzT2.newInstance();
        } catch (IllegalAccessException | InstantiationException var4) {
            var4.printStackTrace();
        }
        BeanUtil.copyProperties(t1, t2);
        return t2;
    }

    protected List<T2> convertToT2(List<T1> t1s) {
        return (List)t1s.stream().map(this::convertToT2).collect(Collectors.toList());
    }

    protected T1 convertToT1(T2 t2) {
        T1 t1 = null;

        try {
            t1 = this.clazzT1.newInstance();
        } catch (IllegalAccessException | InstantiationException var4) {
            var4.printStackTrace();
        }

        BeanUtil.copyProperties(t2, t1);
        return t1;
    }

    protected List<T1> convertToT1(List<T2> t2s) {
        return (List)t2s.stream().map(this::convertToT1).collect(Collectors.toList());
    }

    protected T1 convertT3ToT1(T3 t3) {
        T1 t1 = null;

        try {
            t1 = this.clazzT1.newInstance();
        } catch (IllegalAccessException | InstantiationException var4) {
            var4.printStackTrace();
        }

        BeanUtil.copyProperties(t3, t1);
        return t1;
    }

    @Override
    public void afterPropertiesSet() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)ParameterizedType.class.cast(type);
        this.clazzT1 = (Class)Class.class.cast(pt.getActualTypeArguments()[0]);
        this.clazzT2 = (Class)Class.class.cast(pt.getActualTypeArguments()[1]);
        this.clazzT3 = (Class)Class.class.cast(pt.getActualTypeArguments()[2]);
    }
}
