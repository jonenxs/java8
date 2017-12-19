package com.nxs.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterEmployTest {

    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",37,9999.99),
            new Employee(102,"李四",21,6666.66),
            new Employee(103,"王五",20,3333.33),
            new Employee(104,"赵六",22,8888.88),
            new Employee(105,"田七",19,7777.7)
    );

    @Test
    public void test1(){
//        List<Employee> filterEmployeeByAgeList = this.filterEmployeeByAge(this.employees);
//        for (Employee employee : filterEmployeeByAgeList) {
//            System.out.println(employee);
//        }

        List<Employee> filterEmployeeBySalaryList = this.filterEmployeeBySalary(this.employees);
        for (Employee employee : filterEmployeeBySalaryList) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2(){
//        List<Employee> filterEmployeeList = this.filterEmployee(this.employees, new EmployeeFilterByAgeImpl());
//        for (Employee employee : filterEmployeeList) {
//            System.out.println(employee);
//        }
        List<Employee> filterEmployeeList = this.filterEmployee(this.employees, new EmployeeFilterBySalaryImpl());
        for (Employee employee : filterEmployeeList) {
            System.out.println(employee);
        }
    }

    /**
     * 获取年龄大于35岁的员工
     * @return
     */
    private List<Employee> filterEmployeeByAge(List<Employee> employeeList){
        List<Employee> returnList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getAge() > 35) {
                returnList.add(employee);
            }
        }
        return returnList;
    }



    /**
     * 获取薪资大于5000的员工
     * @return
     */
    private List<Employee> filterEmployeeBySalary(List<Employee> employeeList){
        List<Employee> returnList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getSalary() > 5000) {
                returnList.add(employee);
            }
        }
        return returnList;
    }

    /**
     * 优化方式1 策略器模式
     * @param employeeList
     * @param filterService
     * @return
     */
    private List<Employee> filterEmployee(List<Employee> employeeList, FilterService<Employee> filterService) {
        List<Employee> returnList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (filterService.filter(employee)) {
                returnList.add(employee);
            }
        }
        return returnList;
    }

    /**
     * 优化方式2 匿名内部类
     */
    @Test
    public void test3() {
        List<Employee> employees = filterEmployee(this.employees, new FilterService<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getSalary() < 5000;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式3 lambda表达式
     */
    @Test
    public void test4() {
        List<Employee> employeeList = filterEmployee(this.employees, (e) -> e.getSalary() > 5000);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式4 stream API
     */
    @Test
    public void test5() {
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("--------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

}
