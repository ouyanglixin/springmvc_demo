package com.oyy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oyy.reggie.common.R;
import com.oyy.reggie.pojo.Employee;

public interface EmployeeService extends IService<Employee> {
    R<Employee> login(Employee employee);
}
