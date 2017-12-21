package com.nxs.stream;


import com.nxs.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StringApiTest {
    /**
     * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *  给定【1,2,3,4,5】,应该返回【1,4,9,16,25】
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums)
                .map(x -> x * x)
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
     * 用map和reduce方法数一数流中有多少个Employee
     */
    @Test
    public void test2(){
        Optional<Integer> num = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(num.get());
    }

}
