package com.nxs.stream;


import com.nxs.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
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
            new Employee(101,"张三",18,9999.99, Employee.Status.FREE),
            new Employee(102,"李四",20,6666.66,Employee.Status.FREE),
            new Employee(102,"李四",20,6666.66,Employee.Status.FREE),
            new Employee(102,"李四",20,6666.66,Employee.Status.FREE),
            new Employee(102,"李四",20,6666.66,Employee.Status.FREE),
            new Employee(103,"王五",17,3333.33,Employee.Status.BUSY),
            new Employee(104,"赵六",22,8888.88,Employee.Status.BUSY),
            new Employee(104,"赵六",22,8888.88,Employee.Status.VOCATION),
            new Employee(104,"赵六",22,8888.88,Employee.Status.VOCATION),
            new Employee(105,"田七",19,7777.7,Employee.Status.FREE)
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

    /**
     * 排序
     * sorted() 自然排序
     * sorted(Comparator com) 定制排序
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("--------------");
        employees.stream()
                .sorted((e1,e2) ->{
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     * 终止操作
     * 查找与匹配
     *  allMatch 检查是否匹配所有元素
     *  anyMatch 检查是否至少匹配一个元素
     *  noneMatch 检查是否没有匹配所有元素
     *  findFirst 返回第一个元素
     *  count 返回流中元素的个数
     *  max 返回流中最大值
     *  min 返回流中最小值
     */
    @Test
    public void test8() {
        boolean allMatch = employees.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(allMatch);
        boolean anyMatch = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(anyMatch);

        boolean noneMatch = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(noneMatch);

        Optional<Employee> optional = employees.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(optional.get());

        Optional<Employee> any = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());

        Long count = employees.stream().count();
        System.out.println(count);
        Optional<Employee> max = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 规约
     * reduce(T identity,BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("-------------------");
        Optional<Double> sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sum.get());
    }

    /**
     * 收集
     * collect 将流装换为其它形式。接收Collect接口的实现，用T给Stream中元素反复结合起来，得到一个值
     */
    @Test
    public void test10() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-----------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("------------");

        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        //总和
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        //平均值
        Double average = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);

        //工资总和
        Double summing = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(summing);

        //工资最大值的员工
        Optional<Employee> maxEmployee = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(maxEmployee.get());
        //工资最小值
        Optional<Double> minSalary = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(minSalary.get());

    }

    @Test
    public void test11() {
        //分组
        Map<Employee.Status, List<Employee>> listMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(listMap);
        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 18) {
                        return "青年";
                    } else if (e.getAge() > 50) {
                        return "老年";
                    } else {
                        return "中年";
                    }
                })));
        System.out.println(map);
        //分区
        Map<Boolean, List<Employee>> booleanListMap = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(booleanListMap);

        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getCount());

        String names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","[","]"));
        System.out.println(names);

    }
}
