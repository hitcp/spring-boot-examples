package org.microframework.java.bean;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 对象、集合拷贝
 *
 * @author Shaoyu Liu
 * @date 2021/11/10 17:20
 * @see BeanUtils
 **/
public class SpringBeanUtil {


    /**
     * 拷贝对象
     *
     * @param source 数据源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BiConsumer<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.accept(source, t);
            }
        }
        return list;
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类
     * @return
     */
    public static <S, T> List<T> copyListBean(List<S> sources, Class<T> target) {
        List<T> targets = new ArrayList<>();
        if (CollectionUtils.isEmpty(sources)) {
            return targets;
        }
        for (S source : sources) {
            targets.add(copyBean(source, target));
        }
        return targets;
    }

    /**
     * 对象属性拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类
     * @return
     */
    public static <S, T> T copyBean(S sources, Class<T> target) {
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(sources, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private SpringBeanUtil() {
    }
}
