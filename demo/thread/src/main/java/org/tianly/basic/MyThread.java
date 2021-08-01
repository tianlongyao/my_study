package org.tianly.basic;

/**
 * @ClassName: MyThread
 * @Description:
 * @author: tianly
 * @date: 2021/8/1 21:44
 */
public class MyThread {
    public static void main(String[] args) {
        Thread t = new MyDefineThread();
        t.start(); // 启动新线程
    }
}
class MyDefineThread extends  Thread{
    @Override
    public void run() {
        System.out.println("MyDefineThread running ....");
    }
}