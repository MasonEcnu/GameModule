package com.mason.practice;

import java.lang.reflect.Array;

/**
 * Created by mwu on 2019/12/30
 * 试着用数组实现个栈
 */
public class StackDemo<T> {

    private int DEFAULT_SIZE = 10;

    private int SCALE_RAGE = 2;

    private int topIndex = 0;
    private int bottomIndex = 0;

    private T[] data;

    @SuppressWarnings("unchecked")
    StackDemo(Class<T> type) {
        this.data = (T[]) Array.newInstance(type, DEFAULT_SIZE);   // 创建泛型数组
    }

    StackDemo(T[] data) {
        this.data = data;
    }

    public static void main(String[] args) {
        StackDemo<Integer> stackDemo = new StackDemo<>(Integer.class);
        stackDemo.put(1);
        stackDemo.put(2);
        stackDemo.put(3);
        stackDemo.put(4);
        stackDemo.put(5);
        stackDemo.put(6);
        stackDemo.put(7);
        stackDemo.put(8);
        stackDemo.put(9);
        stackDemo.put(10);
        stackDemo.show();
        System.out.println(stackDemo.size());
        System.out.println(stackDemo.innerSize());
        stackDemo.pop();
        stackDemo.show();
        System.out.println(stackDemo.size());
        System.out.println(stackDemo.innerSize());
    }

    public int size() {
        return bottomIndex - topIndex;
    }

    public int innerSize() {
        return data.length;
    }

    public boolean put(T ele) {
        try {
            if (isFull()) {
                // 先扩容
                scale();
            }
            System.arraycopy(data, topIndex, data, topIndex + 1, bottomIndex - topIndex);
            data[topIndex] = ele;
            bottomIndex++;
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    // 栈--只能删除栈底的元素
    public T pop() {
        try {
            T temp = data[topIndex];
            topIndex++;
            if (needShrink()) {
                shrink();
            }
            return temp;
        } catch (NullPointerException e) {
            return null;
        }
    }

    // 扩容
    @SuppressWarnings("unchecked")
    private void scale() {
        int newSize = data.length * SCALE_RAGE;
        if (size() != 0) {
            T[] newArray = (T[]) Array.newInstance(data[0].getClass(), newSize);
            System.arraycopy(data, 0, newArray, 0, data.length);
            data = newArray;
        }
    }

    // 缩小容量
    @SuppressWarnings("unchecked")
    private void shrink() {
        int newSize = data.length / SCALE_RAGE;
        if (size() != 0) {
            T[] newArray = (T[]) Array.newInstance(data[0].getClass(), newSize);
            int oldSize = size();
            System.arraycopy(data, topIndex, newArray, 0, oldSize);
            data = newArray;
            topIndex = 0;
            bottomIndex = oldSize;
        }
    }

    private boolean needShrink() {
        return size() < data.length / SCALE_RAGE;
    }

    public boolean isEmpty() {
        return topIndex == bottomIndex;
    }

    public boolean isFull() {
        return data.length != 0 && topIndex + bottomIndex == data.length - 1;
    }

    public void show() {
        for (int i = topIndex; i < bottomIndex; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
