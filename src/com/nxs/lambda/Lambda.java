package com.nxs.lambda;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Function;

/**
 * 一、Lambda 表达式的基础语法：java8引入一个新的操作符“->”该操作符称为箭头操作符或Lambda操作符，
 * 箭头操作符将Lambda表达式拆为两部分：
 * 左侧：lambda 表达式的参数列表
 * 右侧：lambda表达式所需执行的功能，即lambda体
 *
 * 二、Lambda表达式需要“函数是接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface修饰
 *             可以检查是否是函数式接口
 * 三、Java8内置的四大函数式接口
 * Consumer<T>:消费性接口
 *       void accept(T t);
 * Supplier<T>:供给型接口
 *        T get();
 * Function<T,R>:函数型接口
 *        R apply(T t);
 * Predicate:断言型接口
 *        boolean test(T t);
 */
public class Lambda {

    /**
     * 语法格式一：无参数，无返回值
     * （） ->  System.out.println("Hello Lambda!");
     */
    @Test
    public void test1(){
        int num = 0;//jdk1.7前，必须是final,但是1.8之后默认为final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello word!" + num);
            }
        };
        r.run();

        System.out.println("------------");

        Runnable r1 = () -> System.out.println("Hello lambda!"+num);
        r1.run();
    }

    /**
     * 语法格式二：有一个参数，无返回值
     * x -> System.out.println(x);
     */
    @Test
    public void test2(){
        //若只有一个参数，则()可以不写
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Hello lambda!");
    }

    /**
     * 语法格式三：有两个及以上的参数，有返回值，并且Lambda体中有多条语句
     */
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }
    /**
     * 语法格式四：有两个及以上的参数，有返回值，并且Lambda体中只有一条语句
     * {}和return 都可以省略
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
    }

    /**
     * 语法格式五：lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出
     * 数据类型，即“类型推断”；
     */
    @Test
    public void test5(){
//        String[] strs = {"aaa","bbb","ccc"};
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map){

    }

    //需求：对一个数进行计算
    @Test
    public void test6(){
        Integer num = operation(100,(x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200,y -> y+200));
    }

    public  Integer operation(Integer num,MyFun mf){
        return mf.getValue(num);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101,"张三",18,9999.99),
            new Employee(102,"李四",21,6666.66),
            new Employee(103,"王五",20,3333.33),
            new Employee(104,"赵六",22,8888.88),
            new Employee(105,"田七",19,7777.7)
            );

    @Test
    public void test7(){
        Collections.sort(emps,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    //处理字符串
    @Test
    public void test8(){
        String trimStr = strHandler("\t\t\t 辐射4 ",(str) -> str.trim());
        System.out.println(trimStr);
        String upperStr = strHandler("abcdef ", (str) -> str.toUpperCase());
        System.out.println(upperStr);
        String subStr = strHandler("abcdef ", (str) -> str.substring(2,5));
        System.out.println(subStr);
    }

    //处理字符串
    public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }

    @Test
    public void test9(){
        op(100L, 200L, (x, y) -> x + y);
        op(100L, 200L, (x, y) -> x * y);
    }

    //对于两个Long型进行处理
    public void op(Long l1,Long l2,MyFunction2<Long,Long> f){
        System.out.println(f.getValue(l1,l2));
    }

    //Consumer<T> 消费型接口
    @Test
    public void test10(){
        happy(10000, (m) -> System.out.println("消费了" + m));
    }

    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    //Supplier<T> 供给型接口
    @Test
    public void test11(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        for (Integer num: numList
             ) {
            System.out.println(num);
        }
    }

    //产生指定个数的数，并放入接口中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            Integer n = supplier.get();
            list.add(n);
        }
        return  list;
    }

    //Function<T,R>:函数型接口
    @Test
    public void test12(){
        String newStr = stringHandler("\t\t\ttasdfsgsd   ", (str) -> str.trim());
        System.out.println(newStr);

        String trimStr = stringHandler("嘎查时代光华", (str) -> str.substring(2,5));
        System.out.println(trimStr);
    }

    //处理字符串
    public String stringHandler(String str , Function<String,String> fun){
        return  fun.apply(str);
    }

    //Predicate<T> 断言型接口
    @Test
    public void test13(){
        List<String> list = Arrays.asList("Hello","asdasd","lambda","www","ok");
        List<String> strList = filterStr(list,(s) -> s.length() > 3);
        for (String str : strList){
            System.out.println(str);
        }
    }

    //将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();

        for (String str:list) {
            if(predicate.test(str)){
                stringList.add(str);
            }
        }
        return  stringList;
    }
}
