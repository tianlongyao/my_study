package org.tianly.basic;

/**
 * @ClassName: MyRunnable
 * @Description:
 * @author: tianly
 * @date: 2021/8/1 21:49
 */
public class MyRunnable {
    public static void main(String[] args) {
        Thread t = new Thread(new MyDefineRunable());
        t.start(); // 启动新线程
    }
}
class  MyDefineRunable implements Runnable{

    @Override
    public void run() {
        System.out.println("MyDefineRunable running......");
    }
}
