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
        //Optional.of()要求传入的 obj 不能是 null 值的, 否则还没开始进入角色就倒在了 NullPointerException 异常上了.
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
        //Optional.ofNullable()以一种智能的, 宽容的方式来构造一个 Optional 实例. 来者不拒, 传 null 进到就得到 Optional.empty() , 非 null 就调用 Optional.of(obj)
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
//        System.out.println(optionalEmployee.get());
    }

    @Test
    public  void test4() throws Exception {
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
        if (optionalEmployee.isPresent()){
            System.out.println(optionalEmployee.get());
        }
        //存在即返回, 无则提供默认值
        Employee employee = optionalEmployee.orElse(new Employee(101, "张三", 18, 9999.99));
            System.out.println(employee);
        //orElseGet 与 orElse 方法的区别在于，orElseGet 方法传入的参数为一个 Supplier 接口的实现 —— 当 Optional 中有值的时候，返回值；
        // 当 Optional 中没有值的时候，返回从该 Supplier 获得的值。
        Employee emp = optionalEmployee.orElseGet(() -> new Employee());
        System.out.println(emp);
        //orElseThrow 与 orElse 方法的区别在于，orElseThrow 方法当 Optional 中有值的时候，返回值；
        // 没有值的时候会抛出异常，抛出的异常由传入的 exceptionSupplier 提供。
        Optional.ofNullable(null).orElseThrow(() -> new Exception("空空空空!!!"));
        /**
         举一个 orElseThrow 的用途：在 SpringMVC 的控制器中，我们可以配置统一处理各种异常。
         查询某个实体时，如果数据库中有对应的记录便返回该记录，否则就可以抛出 EntityNotFoundException ，
         处理 EntityNotFoundException 的方法中我们就给客户端返回Http 状态码 404 和异常对应的信息
        —— orElseThrow 完美的适用于这种场景。

          @RequestMapping("/{id}")
          public User getUser(@PathVariable Integer id) {
              Optional<User> user = userService.getUserById(id);
              return user.orElseThrow(() -> new EntityNotFoundException("id 为 " + id + " 的用户不存在"));
          }

          @ExceptionHandler(EntityNotFoundException.class)
          public ResponseEntity<String> handleException(EntityNotFoundException ex) {
              return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
          }
         */

    }

    @Test
    public  void test5(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(new Employee(101, "张三", 18, 9999.99));
        //flatMap 方法与 map 方法的区别在于，map 方法参数中的函数 mapper 输出的是值，
        // 然后 map 方法会使用 Optional.ofNullable 将其包装为 Optional；
        // 而 flatMap 要求参数中的函数  mapper 输出的就是 Optional
        Optional<String> optional = optionalEmployee.map(Employee::getName);
        System.out.println(optional.get());
        Optional<String> stringOptional = optionalEmployee.flatMap(e -> Optional.of(e.getName()));
        System.out.println(stringOptional.get());
    }


}
