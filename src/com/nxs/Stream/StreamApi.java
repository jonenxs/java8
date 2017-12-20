package com.nxs.Stream;


import com.nxs.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、 StreamApi 的三个操作步骤：
 * 1. 创建Stream
 * 2. 中间操作
 * 3. 终止操作
 */
public class StreamApi {
    /**
     * 创建Stream
     */
    @Test
    public void test1(){
        //1.可以通过Collection 系列集合提供的stream()或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stringStream = list.stream();
        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(employees);
        //3.通过Stream类中的静态方法of()
        Stream<String> stream = Stream.of("aa", "bb", "cc");
        //4.创建无限流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);


    }
}
