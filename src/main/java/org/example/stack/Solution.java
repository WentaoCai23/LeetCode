package org.example.stack;

import java.util.*;

public class Solution {

    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                stack.push(')');
            }else if (ch == '{') {
                stack.push('}');
            }else if (ch == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                stack.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return stack.isEmpty();
    }

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++){
            if (stack.empty() || stack.peek() != s.charAt(i)){
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        String str = "";
        while (!stack.empty()){
            str = stack.pop() + str;
        }
        return str;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token)){
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)){
                Integer tmp = stack.pop();
                stack.push(stack.pop() - tmp);
            } else if ("*".equals(token)){
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)){
                Integer tmp = stack.pop();
                stack.push(stack.pop() / tmp);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }



}

class MyQueue {

    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if (stackOut.empty()){
            while (!stackIn.empty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        if (stackOut.empty()){
            while (!stackIn.empty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        if (stackIn.empty() && stackOut.empty()){
            return true;
        }
        return false;
    }
}

class MyStack {

    //queue1用于储存数据
    Queue<Integer> queue1;
    //queue2用于备份
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        //先放到一个queue2中
        queue2.add(x);
        //将queue1的所有元素再放到queue2中
        while (!queue1.isEmpty()){
            queue2.add(queue1.remove());
        }
        //交换queue1和queue2，实际上queue2没有用
        Queue<Integer> tmp = new LinkedList<>();
        tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    //remove移除队列头部的元素，如果队列为空则抛异常
    //poll移除队列头部的元素，如果队列为空则返回null
    //当队列长度满时，add会抛出异常，而offer直接返回false
    public int pop() {
        return queue1.remove();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
