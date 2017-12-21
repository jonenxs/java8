package com.nxs.optional;


import com.nxs.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author 57014
 */
public class OptionalTest {
    @Test
    public  void test1(){
        Optional<Employee> employee = Optional.of(new Employee());
        Employee emp = employee.get();
        System.out.println(emp);
    }

    @Test
    public  void test2(){
        Optional<Object> empty = Optional.empty();
//        System.out.println(empty.get());
    }

    @Test
    public  void test3(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
//        System.out.println(optionalEmployee.get());
    }

    @Test
    public  void test4(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
        if (optionalEmployee.isPresent()){
            System.out.println(optionalEmployee.get());
        }
        Employee employee = optionalEmployee.orElse(new Employee(101, "张三", 18, 9999.99));
            System.out.println(employee);

        Employee emp = optionalEmployee.orElseGet(() -> new Employee());
        System.out.println(emp);
    }

    @Test
    public  void test5(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(new Employee(101, "张三", 18, 9999.99));
        Optional<String> optional = optionalEmployee.map(e -> e.getName());
        System.out.println(optional.get());
        Optional<String> stringOptional = optionalEmployee.flatMap(e -> Optional.of(e.getName()));
        System.out.println(stringOptional.get());
    }


}
