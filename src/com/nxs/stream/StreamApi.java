package com.nxs.stream;


import com.nxs.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",18,9999.99),
            new Employee(102,"李四",21,6666.66),
            new Employee(103,"王五",20,3333.33),
            new Employee(104,"赵六",22,8888.88),
            new Employee(104,"赵六",22,8888.88),
            new Employee(104,"赵六",22,8888.88),
            new Employee(105,"田七",19,7777.7)
    );

    /**
     * 中间操作
     * 筛选与切片：
     * filter 接收Lambda，从流中排除某些元素
     * limit 截断流，使其元素不能超过指定数量
     * skip(n) 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不是n个，则返回一个空流。与limit(n)互补
     * distinct 筛选，通过流所生成的hashCode()和equals()去除重复元素
     *
     * 内部迭代：迭代操作又Stream API完成
     */
    @Test
    public void test2(){
        //中间操作 ： 不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) -> {
                    System.out.println("Stream API中间操作");
                    return e.getAge() > 20;
                });
        //终止操作 ：一次性执行全部内容，即"惰性操作"
        employeeStream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test3(){
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test4(){
        employees.stream()
                .filter(e -> {
                    System.out.println("短路！");
                   return e.getSalary() > 5000;
                })
                .limit(2).forEach(System.out::println);
    }

    @Test
    public void test5(){
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map 接收Lambda,将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新的元素
     * flatMap 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-----------");
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-----------");
        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamApi::filterCharacter);
        streamStream.forEach( sm -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-----------");
        Stream<Character> stream = list.stream()
                .flatMap(StreamApi::filterCharacter);
        stream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();
        for (Character ch :
                string.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}
