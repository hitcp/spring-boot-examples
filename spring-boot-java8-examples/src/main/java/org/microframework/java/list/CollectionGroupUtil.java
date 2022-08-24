package org.microframework.java.list;



import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Shaoyu Liu
 * @date 2022/1/5 17:11
 **/
public class CollectionGroupUtil {

    /**
     * 对集合进行分批
     *
     * @param list     待分组的集合
     * @param quantity 每个分组内的元素数量
     * @param <E>
     * @return
     */
    public static <E> List<List<E>> groupListByQuantity(List<? extends E> list, int quantity) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList(list.subList(count, Math.min((count + quantity), list.size()))));
            count += quantity;
        }

        return wrapList;
    }

    private CollectionGroupUtil() {
    }
}
