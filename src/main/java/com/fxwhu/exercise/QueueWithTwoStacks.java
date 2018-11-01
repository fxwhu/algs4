package com.fxwhu.exercise;

import java.util.Stack;

/**
 * @author: fengxuan
 * @create 2018-10-12 上午12:38
 **/
public class QueueWithTwoStacks<T> {

    Stack<T> stack1 = new Stack<T>();
    Stack<T> stack2 = new Stack<T>();


    public void enqueue(T item) {
        stack1.push(item);
    }


    public T dequeue() {
        T result = null;
        if (stack2.size() < 1) {
            while (stack1.size() > 1) {
                T element = stack1.pop();
                stack2.push(element);
            }
            result = stack1.pop();
        } else {
            result = stack2.pop();
        }
        return result;
    }


    public static void main(String[] args) {
        QueueWithTwoStacks twoStacks = new QueueWithTwoStacks();
        twoStacks.enqueue("我的");
        twoStacks.enqueue("名字");
        twoStacks.enqueue("叫");
        twoStacks.enqueue("fx");

        System.out.println(twoStacks.dequeue());
        System.out.println(twoStacks.dequeue());
        System.out.println(twoStacks.dequeue());
        System.out.println(twoStacks.dequeue());

        System.out.println("===");


    }
}
