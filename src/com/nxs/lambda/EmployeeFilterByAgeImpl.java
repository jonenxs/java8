package com.nxs.lambda;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFilterByAgeImpl implements FilterService<Employee> {

    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() >= 35;
    }
}
