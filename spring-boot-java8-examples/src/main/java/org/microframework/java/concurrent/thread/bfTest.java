package org.microframework.java.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class bfTest {
//    static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Object>> list = new ArrayList<>();
        for(int i = 0;i < 10000;i++){
            list.add(() -> {
                System.out.println(System.currentTimeMillis());
                //optimisticRead();
                return null;
            });
        }
        service.invokeAll(list);
    }
}
