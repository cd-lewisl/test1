package lewisl.test1.blockingqueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.LockSupport;

public class SynchronousQueueTest {

    public static void main(String[] args) {
        final SynchronousQueue queue = new SynchronousQueue();

        new Thread() {
            public void run() {
                try {
                    Object o = queue.take();
                    System.out.println(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        try {
            queue.put("xxxxxx");
            queue.put("xxxxxx");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // queue.add("xxxxxx");
        // queue.add("xxxxxx");

    }

}
