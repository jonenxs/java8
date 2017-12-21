package com.nxs.time;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 57014
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
//                return simpleDateFormat.parse("20171221");
                return LocalDate.parse("20171221", dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results = new ArrayList<>();

        for(int i = 0;i< 10;i++){
            results.add(pool.submit(task));
        }

        for(Future<LocalDate> future : results){
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}

