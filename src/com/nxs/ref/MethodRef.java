package com.nxs.ref;

import com.nxs.lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 * (可以理解为方法引用是Lambda表达式的另一种表现形式)
 * 主要有三种语法格式
 * 对象：：实例方法名
 * 类：：静态方法名
 * 类：：实例方法名
 * 二、构造器引用：
 * 格式：
 * ClassName::new
 * 三、数组引用
 * Type::new
 */
public class MethodRef {

    /**
     * 对象：：实例方法名
     */
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

    /**
     * 类：：静态方法名
     */
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> comparator1 = Integer::compare;
        int compare = comparator1.compare(1, 2);
        System.out.println(compare);
    }

    /**
     * 类：：实例方法名
     * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致
     * 2、若Lambda参数列表中的第一参数是 实例方法 的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
     */
    @Test
    public void test4(){
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        boolean test = biPredicate.test("aaa", "aaa");
        System.out.println(test);

        BiPredicate<String, String> biPredicate2 = String::equals;
        boolean test1 = biPredicate2.test("aaa", "bbb");
        System.out.println(test1);
    }

    /**
     * 构造器引用
     */
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();

        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier1.get();
    }

    /**
     * 数组引用
     */
    @Test
    public void test6(){
        Function<Integer,String[]> function = (x) -> new String[x];
        String[] strings = function.apply(10);
        System.out.println(strings.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] apply = function1.apply(20);
        System.out.println(apply.length);

    }
}
