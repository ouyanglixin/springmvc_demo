package com.oyy.reggie.service.impl;

import com.alibaba.druid.pool.PoolableWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oyy.reggie.common.R;
import com.oyy.reggie.mapper.EmployeeMapper;
import com.oyy.reggie.pojo.Employee;
import com.oyy.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Wrapper;
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Override
    public R<Employee> login(Employee employee) {
        //1,调用employeemapper中的查询方法查根据username 查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee employee1 = employeeMapper.selectOne(lambdaQueryWrapper);
        if(employee1 == null){
            return R.error("登入失败");
        }
        if (employee1.getStatus() == 0 ){
            return R.error("账号已经禁用");
        }
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        log.info("用户输入的密码为：{}，数据库中密码{}",password,employee1.getPassword());
        //判断用户请求的密码和数据库中该用户密码是否想等
        if (password.equals(employee1.getPassword())){
            httpServletRequest.getSession().setAttribute("employee",employee1.getId());
            return R.success(employee1);
        }else {
            return R.error("登入失败");
        }
    }
}
