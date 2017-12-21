package com.nxs.time;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 57014
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//                return simpleDateFormat.parse("20171221");
                return DateFormatThreadLocal.convert("20171221");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
