package org.microframework.java.bean;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CglibBeanUtil {

    public static void copy(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

//    public static <T> T copy(Object source, Supplier<T> type, Class<T> clazz) {
//        BeanCopier copier = BeanCopier.create(source.getClass(), clazz, false);
////        T t = clazz.newInstance();
//        T target = type.get();
//        copier.copy(source, target, null);
//        return target;
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
            copy(source, t);
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
        Object source = new Object();
        Object target = new Object();

        BeanCopier.create(Object.class, Object.class, false).copy(source, target, null);

        BeanCopier.create(Object.class, Object.class, true).copy(source, target, new Converter() {
            @Override
            public Object convert(Object o, Class aClass, Object o1) {
                return null;
            }
        });
    }
}
