package org.microframework.java.list;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 相比较ArrayList添加速度更快，具体见  add(T)方法，remove(Object)
 *
 * @author Shaoyu Liu
 * @date 2022-08-29
 * @see org.microframework.java.list.FastList#add(java.lang.Object)
 * @see com.zaxxer.hikari.util.FastList
 */
public final class FastList<T> implements List<T>, RandomAccess, Serializable {
    private static final long serialVersionUID = -4598088075242913858L;
    /**
     * 元素类型
     */
    private final Class<?> clazz;
    /**
     * 存放元素的数组
     */
    private T[] elementData;
    /**
     * 已有元素的长度
     */
    private int size;

    /**
     * 新建FastList，默认长度32
     *
     * @param clazz
     */
    public FastList(Class<?> clazz) {
        this.elementData = (T[]) Array.newInstance(clazz, 32);
        this.clazz = clazz;
    }

    /**
     * 新建FastList，手动指定长度
     *
     * @param clazz
     * @param capacity
     */
    public FastList(Class<?> clazz, int capacity) {
        this.elementData = (T[]) Array.newInstance(clazz, capacity);
        this.clazz = clazz;
    }

    /**
     * 主要体现在添加元素快上
     * <p>
     * 和ArrayList相比较，主要快在add方法上
     * 1.ArrayList方法层级比FastList多，出入栈更频繁。
     * 2.由于ArrayList使用无参构造时，elementData数组变量是个空数组，需要在首次add时触发数组初始化，多了一些逻辑判断（对于Hikari来说，创建FastList和ArrayList时都传入了初始容量，这些逻辑判断都是无用的）。
     * 3.FastList相比ArrayList去除了modCount的自增操作
     * 4.ArrayList扩容的计算逻辑相对复杂，考虑了很多边界条件。拷贝数组使用Arrays.copyOf方法，其底层也是调用System.arraycopy，但是调用栈很深
     *
     * @param element
     * @return
     * @website <a href="https://juejin.cn/post/6887371883810357255"/>HikariCP源码阅读（二）ConcurrentBag与FastList </a>
     * @see ArrayList#add(java.lang.Object)
     */
    @Override
    public boolean add(T element) {
        // 如果现有元素长度小于数组总长度就直接在最后新增元素
        if (this.size < this.elementData.length) {
            this.elementData[this.size++] = element;
        } else {
            int oldCapacity = this.elementData.length;
            // 扩容为原来容量的2倍
            int newCapacity = oldCapacity << 1;
            // 初始化一个指定类型的数组
            T[] newElementData = (T[]) Array.newInstance(this.clazz, newCapacity);
            // 把老数组移动到新数组上（平滑移动到新数组的前半部分）
            System.arraycopy(this.elementData, 0, newElementData, 0, oldCapacity);
            // 在新数组上添加新元素
            newElementData[this.size++] = element;
            // 将老数组放到新数组上进行扩容
            this.elementData = newElementData;
        }
        return true;
    }

    /**
     * 根据下标获取元素
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        return this.elementData[index];
    }

    public T removeLast() {
        T element = this.elementData[--this.size];
        this.elementData[this.size] = null;
        return element;
    }

    /**
     * 1.从后向前遍历：应该是后面新加的值删除的改路比较高
     * 2.去掉边界校验
     * 3.直接使用==比较内存的值，而arrayList先判断是否为null，null则用==判断，不null则equals判断
     *
     * @param element
     * @return
     * @see ArrayList#remove(int)
     */
    @Override
    public boolean remove(Object element) {
        // 从尾部向前循环
        for (int index = size - 1; index >= 0; --index) {
            // 判断元素是否相等
            if (element == elementData[index]) {
                // 判断元素移动位数
                int numMoved = size - index - 1;
                if (numMoved > 0) {
                    System.arraycopy(elementData, index + 1, elementData, index, numMoved);
                }
                // 元素赋值为null再去掉（注意：--size和size--如果不在表达式中则没有区别）
                elementData[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; ++i) {
            this.elementData[i] = null;
        }

        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T set(int index, T element) {
        T old = this.elementData[index];
        this.elementData[index] = element;
        return old;
    }

    @Override
    public T remove(int index) {
        if (this.size == 0) {
            return null;
        } else {
            T old = this.elementData[index];
            int numMoved = this.size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
            }

            this.elementData[--this.size] = null;
            return old;
        }
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return this.index < FastList.this.size;
            }

            @Override
            public T next() {
                if (this.index < FastList.this.size) {
                    return FastList.this.elementData[this.index++];
                } else {
                    throw new NoSuchElementException("No more elements in FastList");
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
