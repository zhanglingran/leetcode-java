package gaoqi.java.encapsulationclass;

/**
 * StringBuffer 和 StringBuilder 类
 * @author ZhangLingRan
 */
public class StringTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        /**
         *  this.ensureCapacityInternal(this.count + 1);  确保数组value的大小是不是可以够用，不够则扩容
         *  this.value[this.count++] = var1;
         *
         *  value数组扩容的方式：
         *      int var2 = (this.value.length << 1) + 2; 在原来的基础上 (len * 2 + 1) 来进行扩容
         */
        sb.append('a');

        /**
         * 删除区间 i 和 i1 之间的元素
         * 1. 先进行边界检查
         * 之后通过 System.arraycopy(this.value, var1 + var3, this.value, var1, this.count - var2); 进行数组移动
         * System.arrayCopy(Object srcArray,int srcPos,Object destArray ,int destPos,int length)
         *      Object srcArray 原数组（要拷贝的数组）
         *      int srcPos 要复制的原数组的起始位置（数组从0位置开始）
         *      Object destArray 目标数组
         *      int destPos 目标数组的起始位置
         *      int length 原数组的长度
         */
        sb.delete(0,1);
    }



}
