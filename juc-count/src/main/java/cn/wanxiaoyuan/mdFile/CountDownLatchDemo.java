package cn.wanxiaoyuan.mdFile;

import java.util.concurrent.CountDownLatch;

/**
 * @author wanyue3
 * @date 2025/8/3 17:11
 */

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        Increment increment = new Increment(latch);
        Decrement decrement = new Decrement(latch);

        new Thread(increment).start();
        new Thread(decrement).start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Decrement implements Runnable {

    CountDownLatch countDownLatch;

    public Decrement(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {

            for (long i = countDownLatch.getCount(); i > 0; i--) {
                Thread.sleep(1000);
                System.out.println("countdown");
                this.countDownLatch.countDown();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Increment implements Runnable {

    CountDownLatch countDownLatch;

    public Increment(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("await");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}



