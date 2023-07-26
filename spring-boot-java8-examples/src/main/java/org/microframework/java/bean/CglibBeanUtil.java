package org.microframework.java.bean;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 对象、集合拷贝
 *
 * @author Shaoyu Liu
 * @date 2021/11/10 17:20
 * @see BeanCopier
 **/
public class CglibBeanUtil {

    /**
     * 拷贝对象
     *
     * @param source 数据源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanCopier.create(source.getClass(), target.getClass(), false).copy(source, target, null);
    }

//    public static <T> T copyProperties(Object source, Supplier<T> target) {
//        T t = target.get();
//        BeanCopier copier = BeanCopier.create(source.getClass(), t.getClass(), false);
//        copier.copy(source, t, null);
//        return t;
//    }

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


    private CglibBeanUtil() {
    }


    public static void main(String[] args) {
        // fixme 复制object好像不行，必须复制具体的entity
        Object source = new Object();
        Object target = new Object();

        CglibBeanUtil.copyProperties(source, target);
        BeanCopier.create(Object.class, Object.class, false).copy(source, target, null);

        BeanCopier.create(Object.class, Object.class, true).copy(source, target, new Converter() {
            @Override
            public Object convert(Object o, Class aClass, Object o1) {
                return null;
            }
        });
    }
}
