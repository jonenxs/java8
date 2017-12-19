package com.nxs.ref;

import com.nxs.lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 * (可以理解为方法引用是Lambda表达式的另一种表现形式)
 * 主要有三种语法格式
 * 对象：：实例方法名
 * 类：：静态方法名
 * 类：：实例方法名
 */
public class MethodRef {

    @Test
    public void test1(){
        Consumer<String> consumer = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;
        consumer1.accept("123");
    }

    @Test
    public void test2(){
        Employee employee = new Employee();
        Supplier<String> sup = () -> employee.getName();
        String string = sup.get();
        System.out.println(string);

        Supplier<Integer> supplier = employee::getAge;
        Integer num = supplier.get();
        System.out.println(num);
    }

    //类：：静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> comparator1 = Integer::compare;
    }
}
