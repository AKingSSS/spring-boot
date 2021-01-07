package com.aking.learn.utils;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public class PageUtils {
    public PageUtils() {
    }

    public static <P, V> PageInfo<V> convert(PageInfo<P> pageInfoPo, Function<P, V> convert) {
        return parallelConvert(pageInfoPo, convert, false);
    }

    public static <P, V> PageInfo<V> parallelConvert(PageInfo<P> pageInfoPo, Function<P, V> convert) {
        return parallelConvert(pageInfoPo, convert, true);
    }

    public static <P, V> PageInfo<V> parallelConvert(PageInfo<P> pageInfoPo, List<V> list) {
        return parallelConvert(pageInfoPo, list, false);
    }

    private static <P, V> PageInfo<V> parallelConvert(PageInfo<P> pageInfoPo, List<V> list, boolean paralle) {
        PageInfo<V> vPageInfo = new PageInfo();
        if (pageInfoPo != null && !CollectionUtils.isEmpty(pageInfoPo.getList())) {
            BeanUtils.copyProperties(pageInfoPo, vPageInfo);
            vPageInfo.setList(list);
            vPageInfo.setTotal(pageInfoPo.getTotal());
            return vPageInfo;
        } else {
            vPageInfo.setList(Collections.EMPTY_LIST);
            return vPageInfo;
        }
    }

    private static <P, V> PageInfo<V> parallelConvert(PageInfo<P> pageInfoPo, Function<P, V> convert, boolean paralle) {
        PageInfo<V> vPageInfo = new PageInfo();
        if (pageInfoPo != null && !CollectionUtils.isEmpty(pageInfoPo.getList())) {
            BeanUtils.copyProperties(pageInfoPo, vPageInfo);
            List<V> vList = null;
            if (paralle) {
                vList = (List)pageInfoPo.getList().parallelStream().map(convert).filter(Objects::nonNull).collect(Collectors.toList());
            } else {
                vList = (List)pageInfoPo.getList().stream().map(convert).filter(Objects::nonNull).collect(Collectors.toList());
            }

            vPageInfo.setList(vList);
            vPageInfo.setTotal(pageInfoPo.getTotal());
            return vPageInfo;
        } else {
            vPageInfo.setList(Collections.EMPTY_LIST);
            return vPageInfo;
        }
    }
}
