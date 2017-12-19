package com.nxs.lambda;
public class Employee {

    private Integer id;

    private String name;

    private Integer age;

    private Double slary;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, Double slary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.slary = slary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSlary() {
        return slary;
    }

    public void setSlary(Double slary) {
        this.slary = slary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", slary=" + slary +
                '}';
    }
}
