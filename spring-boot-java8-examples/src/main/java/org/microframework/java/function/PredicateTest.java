package org.microframework.java.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 *
 * @author Shaoyu Liu
 * @date 2022/3/24 16:57
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> p = (String s) -> s.startsWith("A"); // 运行test方法，必须返回一个boolean值
        List<String> list = Arrays.asList("A1", "A2", "A3", "B1", "B2", "B4");
        List<String> filter = filter(list, p);
        System.out.println(filter);

    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T item : list) {
            if (p.test(item)) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }
}
