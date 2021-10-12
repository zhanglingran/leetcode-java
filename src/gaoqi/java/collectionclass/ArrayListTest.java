package gaoqi.java.collectionclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Java中的集合 ArrayList的手动实现
 * @author ZhangLingRan
 */
public class ArrayListTest<T> {

    // 数组的大小
    private int size;

    // 定义元素
    private Object[] elementData;

    // 定义空数组
    private static final Object[] EMPTY_ELEMENTDATA = {};
    // 空参数的构造函数生成的对象
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    // List 的默认大小
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayListTest() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayListTest(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("集合大小不可为负数: "+ initialCapacity);
        }
    }


    /**
     * 在List中添加一个元素
     * @param t
     * @return
     */
    public boolean add(T t) {
        ensureCapacityInternal(size+1);
        elementData[size++] = t;
        return true;
    }


    /**
     * 获取List中下标为index的元素
     * @param index
     * @return
     */
    public T get(int index){
        // 检查index是不是在范围之内
        rangCheck(index);
        return elementData(index);
    }


    /**
     * 删除List中下标为index的元素
     * @param index
     * @return
     */
    public T remove(int index) {

        rangCheck(index);
        T t = elementData(index);

        // 计算需要移动的个数
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index , numMoved);
        }
        // 要记得将最后的一个元素置空
        elementData[--size] = null;
        return t;
    }

    public int size() {
        return this.size;
    }

    private void rangCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("访问下标越界啦~");
        }
    }

    private T elementData(int index) {
        return (T) elementData[index];
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 确保在添加的时候，数组容量是够用的，或者提示爆内存
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {

        // 判断是不是出现了溢出
        if (minCapacity < 0) {
            throw new OutOfMemoryError("爆内存啦~");
        }

        if (minCapacity - this.elementData.length > 0) {
            // 如果容量不够了，那么通过grow来扩容
            grow(minCapacity);
        }
    }

    /**
     * 如果是刚刚创建的List 那么容量设置为10
     * 否则设置为之前容量的1.5倍
     * @param minCapacity
     */
    private void grow(int minCapacity) {

        int oldCapacity = this.elementData.length;

        // 这个情况有问题：先执行oldCapacity + oldCapacity 再执行 >> 1 操作
        // int newCapacity = oldCapacity + oldCapacity >> 1;
        // oldCapacity >> 1 + oldCapacity 结果成了0
        int newCapacity = (oldCapacity >> 1) + oldCapacity;

        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            // 如果 newCapacity 溢出了，那么就换成元素当前所需的容量大小
            if(newCapacity < 0) {
                newCapacity = minCapacity;
            }
            // 如果大于了最大容量，那么设置为最大容量
            if(newCapacity - MAX_ARRAY_SIZE > 0) {
                newCapacity = MAX_ARRAY_SIZE;
            }
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

}
