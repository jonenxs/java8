package com.nxs.lambda;

public class EmployeeFilterBySalaryImpl implements FilterService<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
